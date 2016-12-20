package com.github.notyy

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString
import com.github.notyy.service.{ProductService, TempProduct}

import scala.io.StdIn
import scala.util.Random

object WebServer {
  def main(args: Array[String]) {

    val configs = args.map{ paramStr =>
      val param = paramStr.split("=")
      (param(0), param(1))
    }.toMap[String,String]
    val mode = configs.getOrElse("mode", "production")

    if(mode == "test"){
      loadTestData()
    }

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val numbers = Source.fromIterator(() =>
      Iterator.continually(Random.nextInt()))

    val route: Route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      } ~
        path("random") {
          get {
            complete(
              HttpEntity(
                ContentTypes.`text/plain(UTF-8)`,
                // transform each number to a chunk of bytes
                numbers.map(n => ByteString(s"$n\n"))
              )
            )
          }
        } ~ ProductResource.route


    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

  def loadTestData(): Unit ={
    ProductService.create(TempProduct("router"))
  }
}

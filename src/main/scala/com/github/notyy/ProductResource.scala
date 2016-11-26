package com.github.notyy

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import com.github.notyy.domain.Product
import com.github.notyy.service.{ProductService, TempProduct}
import spray.json.DefaultJsonProtocol


trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val productFormat = jsonFormat2(Product)
  implicit val tempProductFormat = jsonFormat1(TempProduct)
}

trait ProductResource extends Directives with JsonSupport {

  val route: Route =
    path("product") {
      get {
        complete(Product("1234", "roboto")) // will render as JSON
      } ~
        post {
          entity(as[TempProduct]) { tempProduct => // will unmarshal JSON to Order
            val optProduct = ProductService.create(tempProduct)
            if (optProduct.isEmpty) {
              complete(StatusCodes.BadRequest)
            } else {
              complete(StatusCodes.Created, s"Product created with id: ${optProduct.get.id}")
            }
            complete(s"Ordered  items: ")
          }
        }
    }
}

package com.github.notyy.util

import com.github.notyy.JsonSupport
import com.github.notyy.service.TempProduct
import spray.json._

object JsonShower extends App with JsonSupport{
  println(TempProduct("router").toJson)
}

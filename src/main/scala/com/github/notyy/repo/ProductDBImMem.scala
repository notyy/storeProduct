package com.github.notyy.repo

import com.github.notyy.domain.Product

import scala.collection.mutable

trait ProductDBImMem extends ProductRepo {
  private var products: mutable.Map[String, Product] = mutable.Map[String, Product]()
  private var latestId = 0

  protected override def insert(name: String): Option[String] = {
    if (selectByName(name).isEmpty) {
      val newId = latestId + 1
      val strNewId = newId.toString
      products += (strNewId -> Product(strNewId, name))
      latestId = newId
      Some(strNewId)
    } else None
  }

  protected override def selectByName(name: String): Option[Product] = {
    products.values.filter(_.name == name).toList.headOption
  }

  protected override def get(id: String): Option[Product] = products.get(id)
}

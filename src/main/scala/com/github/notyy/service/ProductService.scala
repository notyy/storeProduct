package com.github.notyy.service

import com.github.notyy.domain
import com.github.notyy.domain.Product
import com.github.notyy.repo.{ProductDBImMem, ProductRepo}

case class TempProduct(name: String)

trait ProductService {
  this: ProductRepo =>

  def create(tempProduct: TempProduct): Option[domain.Product] = {
    val id = insert(tempProduct.name)
    Some(Product(id, tempProduct.name))
  }

  def findByName(name: String): List[domain.Product] = ???

  def findById(id: String): Option[domain.Product] = ???
}

object ProductService extends ProductService with ProductDBImMem
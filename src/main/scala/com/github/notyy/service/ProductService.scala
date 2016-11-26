package com.github.notyy.service

import com.github.notyy.domain
import com.github.notyy.domain.Product
import com.github.notyy.repo.{ProductDBImMem, ProductRepo}

case class TempProduct(name: String)

trait ProductService {
  this: ProductRepo =>

  def create(tempProduct: TempProduct): Option[domain.Product] = {
    insert(tempProduct.name).map(id => Product(id, tempProduct.name))
  }

  def findByName(name: String): Option[domain.Product] = ???

  def findById(id: String): Option[domain.Product] = ???
}

object ProductService extends ProductService with ProductDBImMem
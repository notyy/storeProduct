package com.github.notyy.repo
import com.github.notyy.domain.Product

trait ProductRepo {

  protected def insert(name: String): Option[String]

  protected def selectByName(name: String): Option[Product]

  protected def get(id: String): Option[Product]
}

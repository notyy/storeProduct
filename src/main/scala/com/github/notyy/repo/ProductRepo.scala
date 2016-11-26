package com.github.notyy.repo

trait ProductRepo {

  def insert(name: String): Option[String]

  def selectByName(name: String): Option[Product]

  def get(id: String): Option[Product]
}

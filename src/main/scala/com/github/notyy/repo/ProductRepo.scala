package com.github.notyy.repo

trait ProductRepo {

  def insert(name: String): String

  def selectByName(name: String): List[Product]

  def get(id: String): Option[Product]
}

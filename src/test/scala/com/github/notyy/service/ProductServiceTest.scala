package com.github.notyy.service

import com.github.notyy.repo.ProductDBImMem
import org.scalatest.{FunSpec, ShouldMatchers}

class ProductServiceTest extends FunSpec with ShouldMatchers {

  val productService = new ProductService with ProductDBImMem

  describe("ProductService") {
    it("can create new product record") {
      val optProduct = productService.create(TempProduct("router"))
      optProduct.isEmpty shouldBe false
      optProduct.get.id should not be empty
      optProduct.get.name shouldBe "router"
    }
  }
}

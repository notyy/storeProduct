package com.github.notyy.service

import com.github.notyy.repo.ProductDBImMem
import org.scalatest.{FunSpec, ShouldMatchers}

class ProductServiceTest extends FunSpec with ShouldMatchers {


  describe("ProductService") {
    it("can create new product record") {
      val productService = new ProductService with ProductDBImMem

      val optProduct = productService.create(TempProduct("router"))
      optProduct should not be empty
      optProduct.get.id should not be empty
      optProduct.get.name shouldBe "router"
    }
    it("duplicated product name is not allowed"){
      val productService = new ProductService with ProductDBImMem
      val firstProduct = productService.create(TempProduct("router"))
      firstProduct should not be empty
      firstProduct.get.id should not be empty

      val secondProduct = productService.create(TempProduct("router"))
      secondProduct shouldBe empty
    }
  }
}

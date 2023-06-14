package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable Long id) {
    log.info("ProductController - getProduct");
    Product product = productService.getProduct(id);
    return product;
  }

  @PostMapping("")
  public Product createProduct(@RequestBody Product product)
      throws InterruptedException {
    log.info("ProductController - createProduct");
    Product createdProduct = productService.createProduct(product);
    return createdProduct;
  }
}

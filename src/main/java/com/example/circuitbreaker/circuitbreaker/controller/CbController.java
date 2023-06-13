package com.example.circuitbreaker.circuitbreaker.controller;

import com.example.circuitbreaker.circuitbreaker.entity.Product;
import com.example.circuitbreaker.circuitbreaker.repository.ProductRepository;
import com.example.circuitbreaker.response.CommonResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class CbController {

  private final ProductRepository productRepository;

  @GetMapping("/create")
  public ResponseEntity<CommonResponse> createProduct() {
    productRepository.saveAll(
        List.of(
            Product.builder().productName("product1").price(1000).stock(10).build(),
            Product.builder().productName("product2").price(2000).stock(20).build(),
            Product.builder().productName("product3").price(3000).stock(30).build(),
            Product.builder().productName("product4").price(4000).stock(40).build(),
            Product.builder().productName("product5").price(5000).stock(50).build()
        ).stream().toList()
    );
    return ResponseEntity.ok().body(
        new CommonResponse(true, "success", null)
    );
  }

  @GetMapping("/list")
  @CircuitBreaker(name = "productList", fallbackMethod = "getProductListFallback")
  public ResponseEntity<CommonResponse> getProductList(
      @RequestParam(defaultValue = "false") Boolean exception
  ) {
    List<Product> productList = productRepository.findAll();
    if (productList.isEmpty() || exception ) {
      throw new RuntimeException("product list is null");
    }
    return ResponseEntity.ok().body(
        new CommonResponse(true, "success", productList)
    );
  }

  public ResponseEntity<?> getProductListFallback(Exception e) {
    return ResponseEntity.ok().body(
        new CommonResponse(false, e.getMessage(), e.getClass())
    );
  }
}

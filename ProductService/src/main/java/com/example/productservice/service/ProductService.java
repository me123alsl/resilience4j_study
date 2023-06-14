package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.NotFoundProductException;
import com.example.productservice.repository.ProductRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Retry(name = "getProduct", fallbackMethod = "fallbackGetProduct")
  public Product getProduct(Long id) {
    log.info("ProductService - getProduct: id={}", id);
    return productRepository.findById(id)
        .orElseThrow(
            () -> new NotFoundProductException("Not Found Product....")
        );
  }

  public Product createProduct(Product product) throws InterruptedException{
    log.info("ProductService - createProduct: product={}", product);
    Thread.sleep(3000);
    return productRepository.save(product);
  }

  private Product fallbackGetProduct(Long id, NotFoundProductException e) {
    log.info("ProductService - fallbackGetProduct: id={}, message={}", id, e.getMessage());
    return null;
  }
}
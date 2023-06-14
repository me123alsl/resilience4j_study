package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product save(Product product);
  Optional<Product> findById(Long id);

}

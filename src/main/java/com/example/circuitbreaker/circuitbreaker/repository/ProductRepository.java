package com.example.circuitbreaker.circuitbreaker.repository;

import com.example.circuitbreaker.circuitbreaker.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> saveAll(Iterable products);
  List<Product> findAll();

}

package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Order save(Order order);

  List<Order> findAll();

}

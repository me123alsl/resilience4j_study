package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
class OrderController
{

  private final OrderService orderService;

  @GetMapping("")
  public List<Order> getAllOrders()
  {
    List<Order> allOrders = orderService.getAllOrders();
    return allOrders;
  }

  @PostMapping("")
  public CommonResponse<?> createOrder(@RequestBody CreateOrderDto createOrderDto )
  {
    return orderService.createOrder(createOrderDto);
  }
}

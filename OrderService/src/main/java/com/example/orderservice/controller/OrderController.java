package com.example.orderservice.controller;

import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public CommonResponse<?> getAllOrders()
  {
    return orderService.getAllOrders();
  }

  @GetMapping("/{id}")
  public CommonResponse<?> getOrder(@PathVariable Long id)
  {
    return orderService.getOrder(id);
  }

  @PostMapping("")
  public CommonResponse<?> createOrder(@RequestBody CreateOrderDto createOrderDto )
  {
    return orderService.createOrder(createOrderDto);
  }
}

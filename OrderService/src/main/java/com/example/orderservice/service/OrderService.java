package com.example.orderservice.service;

import com.example.orderservice.controller.CommonResponse;
import com.example.orderservice.controller.CreateOrderDto;
import com.example.orderservice.exception.NotFoundOrderException;
import com.example.orderservice.exception.NotFoundProductException;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.entity.Product;
import com.example.orderservice.entity.Order;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  @Lazy
  private final RestTemplate restTemplate;
  private static final String PRODUCT_SERVICE_URL = "http://localhost:8082/product/";

  @CircuitBreaker(name = "createProduct", fallbackMethod = "createOrderFallback")
  public CommonResponse<?> createOrder(CreateOrderDto createOrderDto) {
    log.info("OrderService - createOrder: order={}", createOrderDto);
    Product product = restTemplate.getForObject(
        PRODUCT_SERVICE_URL + createOrderDto.getProductId(), Product.class);
    if (product == null) {
      throw new NotFoundProductException(createOrderDto.getProductId());
    }
    Order order = Order.builder()
        .productId(product.getId())
        .quantity(createOrderDto.getQuantity())
        .totalPrice(product.getPrice() * createOrderDto.getQuantity())
        .build();
    return new CommonResponse<>(200, "success",  orderRepository.save(order));
  }

  public Order getOrder(Long id) {
    log.info("OrderService - getOrder: id={}", id);
    return orderRepository.findById(id)
        .orElseThrow(() -> new NotFoundOrderException(id));
  }

  public List<Order> getAllOrders() {
    log.info("OrderService - getAllOrders");
    return orderRepository.findAll();
  }

  private CommonResponse<?> createOrderFallback(CreateOrderDto createOrderDto, NotFoundProductException e) {
    log.info("OrderService - createOrderFallback: order={}", createOrderDto);
    return new CommonResponse<>(400, "상품을 찾을 수 없습니다.", e.getClass());
  }

  private CommonResponse<?> createOrderFallback(CreateOrderDto createOrderDto, CallNotPermittedException e) {
    log.info("OrderService - createOrderFallback(CallNotPermitted): order={}", createOrderDto);
    return new CommonResponse<>(400, "잠시 후 이용해주세요.", e.getClass());
  }
}

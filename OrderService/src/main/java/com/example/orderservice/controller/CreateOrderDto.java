package com.example.orderservice.controller;

import lombok.Getter;

@Getter
public class CreateOrderDto {

  private Long productId;
  private Integer quantity;

}

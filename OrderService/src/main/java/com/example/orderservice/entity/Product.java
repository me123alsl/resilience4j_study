package com.example.orderservice.entity;

import lombok.Data;

@Data
public class Product {
  private Long id;
  private String name;
  private Integer price;
}

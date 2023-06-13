package com.example.circuitbreaker.circuitbreaker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long productId;

  private String productName;

  private int stock;

  private int price;


}

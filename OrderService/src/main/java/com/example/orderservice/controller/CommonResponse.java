package com.example.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.Response;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
  private int code;
  private String message;
  private T data;
}

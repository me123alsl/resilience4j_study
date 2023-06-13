package com.example.circuitbreaker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommonResponse {

  private boolean result;
  private String message;
  private Object data;
}

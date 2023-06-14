package com.example.orderservice.exception;

public class NotFoundOrderException extends RuntimeException{

  public NotFoundOrderException(Long id) {
    super("Could not find order " + id);
  }

}

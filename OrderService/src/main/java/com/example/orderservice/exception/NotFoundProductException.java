package com.example.orderservice.exception;

public class NotFoundProductException extends RuntimeException{

  public NotFoundProductException(Long id) {
    super("Could not find product " + id);
  }

}

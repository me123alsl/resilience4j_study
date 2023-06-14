package com.example.productservice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundProductException extends RuntimeException{

    public NotFoundProductException(String message) {
      super(message);
      log.error(message);
    }

}

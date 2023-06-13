package com.example.circuitbreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CircuitbreakerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CircuitbreakerApplication.class, args);
  }

}

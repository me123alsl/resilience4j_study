package com.example.circuitbreaker.rateLimiter.controller;

import com.example.circuitbreaker.response.CommonResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RlController {

  @GetMapping("/rateLimiter")
  @RateLimiter(name = "default", fallbackMethod = "rateLimiterFallback")
  public CommonResponse rateLimiter(){
    return new CommonResponse(true, "Success", "Success");
  }

  public CommonResponse rateLimiterFallback(Exception e){
    log.info("RateLimiter Fallback Call");
    return new CommonResponse(false, e.getMessage(), e.getClass());
  }

}

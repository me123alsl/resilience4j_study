package com.example.circuitbreaker.timeLimiter.controller;

import com.example.circuitbreaker.response.CommonResponse;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TmController {

  // TimeLimiter 사용 시, 반환타입은 CompletableFuture로 선언해야 한다.
  @GetMapping("/timeLimiter/true")
  @TimeLimiter(name = "default", fallbackMethod = "timeLimiterFallback")
  public CompletableFuture<CommonResponse> timeLimiterTrue(
      @RequestParam(defaultValue = "0") int processingTime) {
    log.info("processingTime : {}", processingTime);
    return timeLimiter_false(processingTime);
  }

  @GetMapping("/timeLimiter/false")
  public CompletableFuture<CommonResponse> timeLimiterFalse(
      @RequestParam(defaultValue = "0") int processingTime) {
    log.info("processingTime : {}", processingTime);
    return timeLimiter_false(processingTime);
  }

  @TimeLimiter(name = "false", fallbackMethod = "timeLimiterFallback")
  private CompletableFuture<CommonResponse> timeLimiter_false(
      int processingTime) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(processingTime);
        return new CommonResponse(true, "Success", "Success");
      } catch (InterruptedException e) {
        return new CommonResponse(false, e.getMessage(), e.getClass());
      }
    });
  }

  @TimeLimiter(name = "true", fallbackMethod = "timeLimiterFallback")
  private static CompletableFuture<CommonResponse> timeLimiter_true(
      int processingTime) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(processingTime);
        return new CommonResponse(true, "Success", "Success");
      } catch (InterruptedException e) {
        return new CommonResponse(false, e.getMessage(), e.getClass());
      }
    });
  }

  private CompletableFuture<CommonResponse> timeLimiterFallback(int processingTime,
      TimeoutException e) {
    log.info("Fallback Call processingTime is : {}", processingTime);
    log.info(e.toString());
    return CompletableFuture.supplyAsync(
        () -> new CommonResponse(false, e.getMessage(), e.getClass()));
  }

}

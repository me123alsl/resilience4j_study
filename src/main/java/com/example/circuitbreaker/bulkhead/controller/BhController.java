package com.example.circuitbreaker.bulkhead.controller;

import com.example.circuitbreaker.bulkhead.service.BhService;
import com.example.circuitbreaker.response.CommonResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BhController {

  private final BhService bhService;

  @GetMapping("/bulkhead/semaphore")
  public CommonResponse bulkhead_Semaphore(
      @RequestParam(defaultValue = "0") int processingTime) {
    return bhService.bulkhead_Semaphore(processingTime);
  }

  @GetMapping("/bulkhead/thread-pool")
  @Bulkhead(name = "threadPool", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.THREADPOOL)
  public CommonResponse bulkhead_ThreadPool(@RequestParam(defaultValue = "0") int processingTime
  ) {
    log.info("processingTime : {}", processingTime);
    try {
      Thread.sleep(processingTime);
    } catch (InterruptedException e) {
      return new CommonResponse(false, e.getMessage(), e.getClass());
    }
    return new CommonResponse(true, "Success", "Success");
  }

  public CommonResponse bulkheadFallback(int processingTime, Exception e) {
    log.info("Bulkhead Fallback Call processingTime is : {}", processingTime);
    return new CommonResponse(false, e.getMessage(), e.getClass());
  }
}

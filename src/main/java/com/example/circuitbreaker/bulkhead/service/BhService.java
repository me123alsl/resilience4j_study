package com.example.circuitbreaker.bulkhead.service;

import com.example.circuitbreaker.response.CommonResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BhService {

  @Bulkhead(name = "semaphore", fallbackMethod = "bulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
  public CommonResponse bulkhead_Semaphore(int processingTime) {
    log.info("processingTime : {}", processingTime);
    try {
      Thread.sleep(processingTime);
    } catch (InterruptedException e) {
      return new CommonResponse(false, e.getMessage(), e.getClass());
    }
    return new CommonResponse(true, "Success", "Success");
  }
}

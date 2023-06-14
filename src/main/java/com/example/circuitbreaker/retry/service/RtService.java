package com.example.circuitbreaker.retry.service;

import com.example.circuitbreaker.response.CommonResponse;
import com.example.circuitbreaker.retry.exception.CustomException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RtService {

  static private int count = 0;
  static private int validCount = 3;

  @Retry(name = "retryService", fallbackMethod = "retryFallback")
  public CommonResponse doAnything(boolean isFail, int exceptionCount) {
    var localCount = 0;
    count++;
    localCount++;
    log.info("Call Count : {}, localCount : {}", count, localCount);
    if (isFail && validCount < exceptionCount ) {
      throw new CustomException("Error : " + count + ", localCount : " + localCount);
    }
    return new CommonResponse(true,  "Success", "Success");
  }

  public CommonResponse retryFallback(boolean isFail, int exceptionCount, CustomException e) {
    log.info("Fallback Call Count is : {}", count);
    count = 0;
    return new CommonResponse(false, e.getMessage(), e.getClass());
  }

}

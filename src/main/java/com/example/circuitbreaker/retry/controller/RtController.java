package com.example.circuitbreaker.retry.controller;

import com.example.circuitbreaker.response.CommonResponse;
import com.example.circuitbreaker.retry.service.RtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RtController {

  private final RtService rtService;

  @GetMapping("/retry")
  public CommonResponse retry(
      @RequestParam(defaultValue = "false") boolean fail,
      @RequestParam(defaultValue = "1") int count){
    return rtService.doAnything(fail,count);
  }
}

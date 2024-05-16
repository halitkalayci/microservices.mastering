package com.kodlama.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="exampleservice")
public interface ExampleServiceClient {
    @GetMapping("api/v1/example")
    String get();
}

package com.kodlama.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="productservice")
public interface ProductClient
{
    @GetMapping("api/v1/products")
    String get();
}

package com.kodlama.orderservice.service.impl;

import com.kodlama.orderservice.client.ProductClient;
import com.kodlama.orderservice.service.OrderService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductClient productClient;

    @Override
    //@Retry(name="retry1", fallbackMethod = "fallbackMethod")
    //@Cacheable(cacheNames = "callProductClientCache")
    public String get() {
        return productClient.get();
        //throw new RuntimeException();
    }


    public String fallbackMethod(Throwable throwable)
    {
        return "This service is temporarily unavaliable.";
    }
}

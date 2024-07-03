package com.kodlama.orderservice.handler;

import org.springframework.stereotype.Component;

@Component
public class FallbackHandler {

    public Object fallbackMethod(Throwable throwable) {
        System.out.println("Fallback triggered due to: " + throwable.getMessage());
        return null;
    }
}
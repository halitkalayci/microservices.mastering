package com.kodlama.orderservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class FailureConfiguration {
    public CircuitBreakerRegistry circuitBreakerRegistry()
    {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .permittedNumberOfCallsInHalfOpenState(10)
                .waitDurationInOpenState(Duration.of(5000, ChronoUnit.MINUTES))
                .ignoreExceptions(MethodArgumentNotValidException.class) // Bu exceptionlar dikkate alınmayacak.
                //.recordExceptions({}) // Hata olarak sayılacaklar.
                .build();

        return CircuitBreakerRegistry.of(config);
    }
}

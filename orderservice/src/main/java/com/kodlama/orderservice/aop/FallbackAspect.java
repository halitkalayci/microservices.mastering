package com.kodlama.orderservice.aop;

import com.kodlama.orderservice.handler.FallbackHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FallbackAspect {

    @Autowired
    private FallbackHandler fallbackHandler;

    @Around("@annotation(io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker) && execution(* *(..))")
    public Object handleFallback(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return fallbackHandler.fallbackMethod(throwable);
        }
    }
}

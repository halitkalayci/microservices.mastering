package com.kodlama.orderservice.controller;

import com.kodlamaio.core.model.AccessToken;
import com.kodlamaio.core.model.LoginRequest;
import com.kodlamaio.core.service.TokenHelper;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final TokenHelper tokenHelper;
    @PostMapping
    public AccessToken login(@RequestBody LoginRequest request)
    {
        return tokenHelper.getToken(request.getEmail(), request.getPassword());
    }

    @GetMapping
    @Retry(name="retry1", fallbackMethod = "fallbackMethod")
    public String get() {
        throw new RuntimeException("");
    }
}

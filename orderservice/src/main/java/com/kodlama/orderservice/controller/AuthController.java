package com.kodlama.orderservice.controller;

import com.kodlamaio.core.model.AccessToken;
import com.kodlamaio.core.model.LoginRequest;
import com.kodlamaio.core.service.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenHelper tokenHelper;
    @PostMapping
    public AccessToken login(@RequestBody LoginRequest request)
    {
        return tokenHelper.getToken(request.getEmail(), request.getPassword());
    }

}

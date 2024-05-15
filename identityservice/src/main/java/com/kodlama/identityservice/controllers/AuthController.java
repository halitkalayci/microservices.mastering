package com.kodlama.identityservice.controllers;

import com.kodlama.identityservice.dtos.requests.LoginRequest;
import com.kodlama.identityservice.dtos.requests.RegisterRequest;
import com.kodlama.identityservice.services.AuthService;
import com.kodlamaio.core.model.AccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;

    @PostMapping("login")
    public AccessToken login(@RequestBody LoginRequest loginRequest)
    {
        return authService.login(loginRequest);
    }

    @PostMapping("register")
    public String register(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }
}

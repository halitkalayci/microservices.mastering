package com.kodlama.identityservice.services;

import com.kodlama.identityservice.dtos.requests.LoginRequest;
import com.kodlama.identityservice.dtos.requests.RegisterRequest;
import com.kodlamaio.core.model.AccessToken;

public interface AuthService
{
    AccessToken login(LoginRequest loginRequest);
    String register(RegisterRequest registerRequest);
}

// CQRS
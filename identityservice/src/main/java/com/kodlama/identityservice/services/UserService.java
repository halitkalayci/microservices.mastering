package com.kodlama.identityservice.services;

import com.kodlama.identityservice.dtos.requests.RegisterRequest;
import com.kodlama.identityservice.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(RegisterRequest request);
}

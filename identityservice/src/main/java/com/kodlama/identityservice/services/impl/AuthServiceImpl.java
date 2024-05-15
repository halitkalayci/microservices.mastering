package com.kodlama.identityservice.services.impl;

import com.kodlama.identityservice.core.exceptions.BusinessException;
import com.kodlama.identityservice.dtos.requests.LoginRequest;
import com.kodlama.identityservice.dtos.requests.RegisterRequest;
import com.kodlama.identityservice.services.AuthService;
import com.kodlama.identityservice.services.UserService;
import com.kodlamaio.core.model.AccessToken;
import com.kodlamaio.core.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AccessToken login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return jwtService.generateToken(loginRequest.getEmail());
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        UserDetails user = userService.loadUserByUsername(registerRequest.getEmail());

        if(user!=null)
            throw new BusinessException("Bu e-posta ile kayıt var.");

        userService.register(registerRequest);
        return "";
    }
}

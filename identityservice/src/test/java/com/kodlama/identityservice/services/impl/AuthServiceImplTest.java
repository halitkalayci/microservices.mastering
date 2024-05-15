package com.kodlama.identityservice.services.impl;

import com.kodlama.identityservice.core.exceptions.BusinessException;
import com.kodlama.identityservice.dtos.requests.RegisterRequest;
import com.kodlama.identityservice.entities.User;
import com.kodlama.identityservice.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    private AuthServiceImpl authService;
    private  UserService userService;

    @BeforeEach
    void setup(){
        userService = Mockito.mock(UserService.class);
        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        JwtService jwtService = Mockito.mock(JwtService.class);
        authService = new AuthServiceImpl(userService, authenticationManager, jwtService);
    }
    @AfterEach
    void cleanup()
    {

    }

    @Test
    void registerWithExistingEmailShouldThrowException()
    {
        // 3A Prensipi

        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("deneme@deneme.com","123456","deneme");
        Mockito.when(userService.loadUserByUsername(Mockito.any())).thenReturn(new User());
        // .....


        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            authService.register(registerRequest);
        });
    }

    void registerSuccess()
    {

    }

}
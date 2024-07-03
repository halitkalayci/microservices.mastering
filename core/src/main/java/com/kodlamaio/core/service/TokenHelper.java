package com.kodlamaio.core.service;

import com.kodlamaio.core.client.IdentityServiceClient;
import com.kodlamaio.core.model.AccessToken;
import com.kodlamaio.core.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenHelper {
    private final IdentityServiceClient identityServiceClient;

    public AccessToken getToken(String email, String password)
    {
        LoginRequest request = new LoginRequest(email,password);
        return identityServiceClient.login(request);
    }
}

package com.kodlamaio.core.client;

import com.kodlamaio.core.model.AccessToken;
import com.kodlamaio.core.model.LoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="identity-service", url = "${external.client.url:http://localhost:8081/api/v1}")
public interface IdentityServiceClient {
    @PostMapping("auth/login")
    AccessToken login(@RequestBody LoginRequest loginRequest);
}

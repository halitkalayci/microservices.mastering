package com.kodlamaio.categoryservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.categoryservice.annotation.Encrypted;
import com.kodlamaio.categoryservice.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class EncryptionResponseResolver implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper;
    private final EncryptionService encryptionService;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypted.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            String json = objectMapper.writeValueAsString(body);
            return encryptionService.encrypt(json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt data.");
        }
    }
}

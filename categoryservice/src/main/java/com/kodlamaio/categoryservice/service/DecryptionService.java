package com.kodlamaio.categoryservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.categoryservice.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DecryptionService {
    private final ObjectMapper objectMapper;
    private final EncryptionService encryptionService;

    public <T> T convert(String encryptedData, Class<T> classType) throws Exception {
        String plainData = encryptionService.decrypt(encryptedData);
        return objectMapper.readValue(plainData, classType);
    }
}

package com.kodlamaio.categoryservice.service;

import com.kodlamaio.categoryservice.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;

@Service
@RequiredArgsConstructor
public class EncryptionService {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public String encrypt(String plainData) throws Exception
    {
        return EncryptionUtil.encrypt(plainData, publicKey);
    }

    public String decrypt(String encryptedData) throws Exception
    {
        return EncryptionUtil.decrypt(encryptedData, privateKey);
    }
}

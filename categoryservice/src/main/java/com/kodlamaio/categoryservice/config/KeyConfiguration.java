package com.kodlamaio.categoryservice.config;

import com.kodlamaio.categoryservice.util.KeyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class KeyConfiguration {
    @Bean
    public KeyPair keyPair() throws Exception {
        return KeyUtil.loadKeyPair("keys");
    }

    @Bean
    public PublicKey publicKey(KeyPair keyPair)
    {
        return keyPair.getPublic();
    }
    @Bean
    public PrivateKey privateKey(KeyPair keyPair)
    {
        return keyPair.getPrivate();
    }
}

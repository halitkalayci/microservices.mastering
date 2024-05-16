package com.kodlamaio.categoryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.categoryservice.annotation.Decrypted;
import com.kodlamaio.categoryservice.annotation.Encrypted;
import com.kodlamaio.categoryservice.entity.Category;
import com.kodlamaio.categoryservice.service.DecryptionService;
import com.kodlamaio.categoryservice.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.runtime.ObjectMethods;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final DecryptionService decryptionService;

    @PostMapping("encrypt")
    @Encrypted
    public Category encrypt(@RequestBody Category category) throws Exception
    {
        category.setName(category.getName() + " deneme 123");
        return category;
    }

    @PostMapping("decrypt")
    public Category decrypt(@RequestBody String encryptedData) throws Exception
    {
        Category category = decryptionService.convert(encryptedData, Category.class);
        return category;
    }
}

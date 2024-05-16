package com.kodlama.productservice.controller;

import com.kodlama.productservice.client.ExampleServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ExampleServiceClient exampleServiceClient;
    @GetMapping
    public String get()
    {
        System.out.println("Bir istek geldi.");
        return "Products";
    }
    @GetMapping("example")
    public String getExample()
    {
        return this.exampleServiceClient.get();
    }
}

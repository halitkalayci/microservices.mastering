package com.kodlama.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {
    @GetMapping
    public String get()
    {
        System.out.println("Bir istek geldi.");
        return "Products";
    }
}

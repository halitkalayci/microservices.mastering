package com.kodlamaio.exampleservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/example")
@RestController
public class ExampleController {

    @GetMapping
    public String get()
    {
        return "Example";
    }
}

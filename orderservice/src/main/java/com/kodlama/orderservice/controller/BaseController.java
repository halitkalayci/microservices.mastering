package com.kodlama.orderservice.controller;

public class BaseController {
    public String fallbackMethod(Throwable throwable)
    {
        return "This service is temporarily unavaliable.";
    }
}

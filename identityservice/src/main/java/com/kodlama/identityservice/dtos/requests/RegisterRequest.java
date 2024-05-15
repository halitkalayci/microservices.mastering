package com.kodlama.identityservice.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest
{
    private String email;
    private String password;
    private String name;
}

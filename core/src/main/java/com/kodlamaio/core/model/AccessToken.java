package com.kodlamaio.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AccessToken {
    private String token;
    private Date expiration;
}

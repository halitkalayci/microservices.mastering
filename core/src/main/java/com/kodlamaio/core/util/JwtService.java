package com.kodlamaio.core.util;

import com.kodlamaio.core.model.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    // TODO: Add file reading.
    private long EXPIRATION = 600000;
    private String SECURITY_KEY = "VMceQdsw58pyF0vl6QMPjIt7k5FkKH6voD7aDWV/ljRVXQuCOx9Qv+bJki0EbmgEcP3f0CroEX54wrCjAANtr5S5Rz7q/FYIeE1gTPnLeudRbneeiLyMx5VthhL3HD8LfFyGWYdX4S96QorJIaLrfSa2rpIL1Rj9XTxOLO1XB1h2keuJWETB9Pt4gVX8+sxvsaptbegYOH8AX+J4uV+XS0a2BSfqBxtSJRXrBIKeelmsmxk1gblh/fLyQS0YHNYfRmW5wLyaMNfQzQWHD36O5ZVthOZaNF3EKFyy0GY1gIb6vQLupAHZLTza2Ozx23FYPtNxJbrvUgE/dsak2hJtOsNNGrGDeCTqeHUv3US1ShM=";

    public AccessToken generateToken(String username)
    {
        return generateToken(username, new HashMap<>());
    }
    public AccessToken generateToken(String username, Map<String,Object> extraClaims)
    {
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION);
        String token = Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiration)
                .claims(extraClaims)
                .signWith(generateKey())
                .compact();

        return new AccessToken(token, expiration);
    }

    public Claims getTokenClaims(String token)
    {
        SecretKey key = (SecretKey) generateKey();
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean verifyJwt(String token)
    {
        return getTokenClaims(token).getExpiration().after(new Date());
    }


    private Key generateKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.botconstructor.security.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class PropertyJwtConfigurer implements JwtConfigurer {
    private final Integer expiration;

    private final Key secret;

    @Autowired
    public PropertyJwtConfigurer(
            @Value("${jwt.config.secret}") String encodedSecret,
            @Value("${jwt.config.expiration}") Integer expiration) {
        this.secret = Keys.hmacShaKeyFor(Base64.getDecoder().decode(encodedSecret));
        this.expiration = expiration;
    }

    @Override
    public JwtBuilder configure(JwtBuilder builder) {
        return builder
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * expiration))
                .signWith(secret);
    }

    @Override
    public Key secret() {
        return secret;
    }
}

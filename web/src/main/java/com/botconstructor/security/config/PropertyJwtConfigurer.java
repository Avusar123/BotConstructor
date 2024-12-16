package com.botconstructor.security.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PropertyJwtConfigurer implements JwtConfigurer {
    @Value("${jwt.config.expiration}")
    private Integer expiration;

    @Value("${jwt.config.secret}")
    private String secret;

    @Override
    public JwtBuilder configure(JwtBuilder builder) {
        return builder
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * expiration))
                .signWith(SignatureAlgorithm.HS256, secret);
    }

    @Override
    public String secret() {
        return secret;
    }
}

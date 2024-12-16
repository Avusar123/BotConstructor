package com.botconstructor.security.config;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    private JwtConfigurer jwtConfigurer;

    public String generateToken(String username) {
        return jwtConfigurer.configure(Jwts.builder()).setSubject(username).compact();
    }

    public JwtExtractor extractor(String token) {
        return new JwtExtractor(token, jwtConfigurer.secret());
    }
}

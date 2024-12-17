package com.botconstructor.security.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class JwtService implements Cloneable {
    @Autowired
    private JwtConfigurer jwtConfigurer;

    private Function<JwtBuilder, JwtBuilder> additionalConfigurer = (builder) -> builder;

    public JwtService withAdditionalConfigurer(Function<JwtBuilder, JwtBuilder> additionalConfigurer)
            throws CloneNotSupportedException {
        var service = (JwtService) this.clone();

        service.additionalConfigurer = additionalConfigurer;

        return service;
    }

    public String generateToken(String username) {
        return additionalConfigurer.apply(jwtConfigurer
                .configure(Jwts.builder())
                .subject(username))
                .compact();
    }

    public JwtExtractor extractor(String token) {
        return new JwtExtractor(token, jwtConfigurer.secret());
    }
}

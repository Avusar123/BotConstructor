package com.botconstructor.security.config;

import io.jsonwebtoken.JwtBuilder;

import java.security.Key;
import java.time.Duration;

public interface JwtConfigurer {
    JwtBuilder configure(JwtBuilder builder);

    Key secret();
}

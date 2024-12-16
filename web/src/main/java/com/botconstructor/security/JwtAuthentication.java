package com.botconstructor.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

    public JwtAuthentication(String token) {
        super(token, null);
    }
}

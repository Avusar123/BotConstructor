package com.botconstructor.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.function.Function;

public class JwtExtractor {
    private final String token;

    private final String signingKey;

    private Claims claims;

    protected JwtExtractor(String token, String signingKey) {
        this.token = token;
        this.signingKey = signingKey;
    }

    public <T> T claim(Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims());
    }

    public String username() {
        return claim(Claims::getSubject);
    }

    public Date expiration() {
        return claim(Claims::getExpiration);
    }

    public boolean isTokenExpired() {
        return expiration().before(new Date());
    }

    private Claims extractAllClaims() {
        if (claims != null) {
            return claims;
        }

        var claims = Jwts
            .parser()
            .setSigningKey(signingKey)
            .parseClaimsJws(token)
            .getBody();

        this.claims = claims;

        return claims;
    }
}

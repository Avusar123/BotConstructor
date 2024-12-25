package com.botconstructor.security;

import com.botconstructor.security.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            var jwtAuth = (JwtAuthentication)authentication;

            var extractor = jwtService.extractor((String) jwtAuth.getPrincipal());

            if (!extractor.isTokenExpired()) {
                var userDetails = userDetailsService.loadUserByUsername(extractor.username());

                UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(result);

                return result;
            }
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }

        throw new BadCredentialsException("Internal authentication error");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthentication.class);
    }
}

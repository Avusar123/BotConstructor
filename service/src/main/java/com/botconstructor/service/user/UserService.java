package com.botconstructor.service.user;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
        void register(@NotBlank String username, @NotBlank String password);
}

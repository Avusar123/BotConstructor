package com.botconstructor.service.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.checkerframework.common.value.qual.MinLen;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
        void register(@Email @NotBlank String username, @MinLen(7) @NotBlank String password);
}

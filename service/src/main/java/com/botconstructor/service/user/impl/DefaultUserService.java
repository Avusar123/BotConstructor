package com.botconstructor.service.user.impl;

import com.botconstructor.model.user.UserModel;
import com.botconstructor.persistence.repos.UserRepo;
import com.botconstructor.service.user.UserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUserName(username).orElseThrow();
    }

    @Override
    public void register(@NotBlank String username, @NotBlank String password) {
        var user = new UserModel(username, passwordEncoder.encode(password));

        userRepo.save(user);
    }
}

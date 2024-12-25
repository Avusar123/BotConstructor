package com.botconstructor.seed;

import com.botconstructor.model.user.UserModel;
import com.botconstructor.persistence.repos.UserRepo;
import com.botconstructor.security.config.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
@Profile("dev")
public class UserSeed implements CommandLineRunner {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public void run(String... args) throws Exception {

        var devUser = new UserModel("dev@example.com", encoder.encode("password"));

        userRepo.save(devUser);

        System.out.printf((
                """
                Created dev user with email: dev@example.com and password: password
                token \t %s
                """), jwtService
                .withAdditionalConfigurer(builder ->
                        builder.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)))
                .generateToken(devUser.getUsername()));
    }
}

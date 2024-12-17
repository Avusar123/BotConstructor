package com.botconstructor.controller;

import com.botconstructor.security.config.JwtService;
import com.botconstructor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager manager) {
        this.userService = userService;
        this.jwtService = jwtService;
        authenticationManager = manager;
    }


    @PostMapping("/api/user/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> body) {
        try {
            userService.register((String) body.get("username"), (String) body.get("password"));

            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping("/api/user/login")
    public String login(@RequestBody Map<String, Object> body) {
        var username = (String) body.get("username");
        var password = (String) body.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return jwtService.generateToken(username);
    }
}

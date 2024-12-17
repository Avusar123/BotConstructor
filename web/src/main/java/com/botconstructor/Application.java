package com.botconstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.botconstructor.persistence",
        "com.botconstructor.controller",
        "com.botconstructor.hosting",
        "com.botconstructor.service",
        "com.botconstructor.aop",
        "com.botconstructor.entrypoint",
        "com.botconstructor.security",
        "com.botconstructor.dto",
        "com.botconstructor.seed"
})
@EntityScan(basePackages = {
        "com.botconstructor.model"
})
@EnableAspectJAutoProxy
@EnableMethodSecurity
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
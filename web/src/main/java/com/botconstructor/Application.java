package com.botconstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
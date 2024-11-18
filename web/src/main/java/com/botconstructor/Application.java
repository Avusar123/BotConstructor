package com.botconstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.botconstructor.repository",
        "com.botconstructor.controller",
        "com.botconstructor.hosting",
        "com.botconstructor.service",
        "com.botconstructor.dto"
})
@EntityScan(basePackages = {
        "com.botconstructor.model"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
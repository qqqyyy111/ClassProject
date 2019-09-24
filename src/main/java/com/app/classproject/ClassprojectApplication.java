package com.app.classproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClassprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassprojectApplication.class, args);
    }

}

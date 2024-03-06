package com.sparta.back_office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackOfficeApplication.class, args);
    }

}


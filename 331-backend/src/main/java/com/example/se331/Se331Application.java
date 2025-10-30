package com.example.se331;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"se331.lab"})
public class Se331Application {
    public static void main(String[] args) {
        SpringApplication.run(Se331Application.class, args);
    }
}
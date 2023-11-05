package com.example.ex1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ex1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex1Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Spring Boot Application is running.");
            // Your custom logic here
        };
    }
}

package com.example.RunningApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@EntityScan(basePackages = {"com.example.RunningApp.models"})
@EnableJpaRepositories(basePackages = {"com.example.RunningApp.repositories"})
@SpringBootApplication
@RestController
public class RunningAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningAppApplication.class, args);
    }

}

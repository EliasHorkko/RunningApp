package com.example.RunningApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.RunningApp.models")
@EnableJpaRepositories("com.example.RunningApp.repositories")
public class RunningAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningAppApplication.class, args);
    }

}

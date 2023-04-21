package com.example.RunningApp;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import com.example.RunningApp.services.EventService;
import com.example.RunningApp.services.ParticipantService;
import com.example.RunningApp.services.UserService;
import com.example.RunningApp.models.User;
import com.example.RunningApp.models.Event;
import com.example.RunningApp.models.Participant;


@EntityScan(basePackages = {"com.example.RunningApp.models"})
@ComponentScan(basePackages = {"com.example.RunningApp.services"})
@EnableJpaRepositories(basePackages = {"com.example.RunningApp.repositories"})
@ComponentScan(basePackages = {"com.example.RunningApp", "com.example.RunningApp.config"})
@SpringBootApplication
@RestController
public class RunningAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningAppApplication.class, args);
    }

    // Sample Data to database
    
    @Bean
    CommandLineRunner init(UserService userService, EventService eventService, ParticipantService participantService) {
        return args -> {
            User user = new User("John", "Doe", "johndoe@example.com", "password");
            userService.saveUser(user);

            Event event = new Event("Spring 10k", LocalDate.of(2022, 5, 1), "A 10k race in the spring", user);
            eventService.saveEvent(event);

            Participant participant = new Participant("Alice", "alice@example.com", event, user);
            participantService.saveParticipant(participant);
        };
    }
}

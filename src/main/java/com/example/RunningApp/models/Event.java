package com.example.RunningApp.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name is required")
    @Size(max = 50, message = "Event name must be at most 50 characters long")
    private String name;

    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @Size(max = 255, message = "Description must be at most 255 characters long")
    private String description;

    // One event can have multiple participants, so use a List
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();
    
    // Many events can be associated with one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    // Default constructor
    public Event() {
    }

    // Constructor with parameters
    public Event(String name, LocalDate date, String description, User user) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.user = user;
        this.participants = new ArrayList<>();
    }
    
    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    // Add a participant to the event
    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.setEvent(this);
    }

    // Remove a participant from the event
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
        participant.setEvent(null);
    }
}

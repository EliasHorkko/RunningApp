package com.example.RunningApp.services;

import com.example.RunningApp.models.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAllEvents();
    Optional<Event> getEventById(Long id);
    Event saveEvent(Event event);
    void deleteEvent(Long id);
}

package com.example.RunningApp.services;

import java.util.List;

import org.springframework.lang.NonNull;

import com.example.RunningApp.models.Event;
import com.example.RunningApp.models.Participant;

public interface EventService {
    Event createEvent(@NonNull Event event);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
	List<Participant> getParticipantsByEventId(Long eventId);
	void saveEvent(Event event);
}

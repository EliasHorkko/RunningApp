package com.example.RunningApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.RunningApp.models.Event;
import com.example.RunningApp.models.Participant;
import com.example.RunningApp.repositories.EventRepository;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    // Create a new event
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Retrieve an event by ID
    @Override
    public Event getEventById(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return optionalEvent.orElse(null);
    }

    // Retrieve a list of all events
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Update an existing event
    @Override
    public Event updateEvent(Long id, Event event) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setName(event.getName());
            existingEvent.setDate(event.getDate());
            existingEvent.setDescription(event.getDescription());
            return eventRepository.save(existingEvent);
        } else {
            return null;
        }
    }

    // Delete an event by ID
    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // Retrieve a list of participants for a given event
    @Override
    public List<Participant> getParticipantsByEventId(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            return event.getParticipants();
        } else {
            return null;
        }
    }

	// Save an event
	@Override
	public void saveEvent(Event event) {
		eventRepository.save(event);
	}
}

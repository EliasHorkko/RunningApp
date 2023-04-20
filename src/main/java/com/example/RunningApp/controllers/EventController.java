package com.example.RunningApp.controllers;

import com.example.RunningApp.models.Event;
import com.example.RunningApp.models.Participant;
import com.example.RunningApp.services.EventService;
import com.example.RunningApp.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Define the REST API endpoints for the Event entity
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ParticipantService participantService;

    
 // Get all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    
 // Get event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 // Create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
    
 // Update an existing event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        if (updatedEvent != null) {
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 // Delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

 // Get all participants for an event
    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<Participant>> getParticipantsByEventId(@PathVariable Long eventId) {
        List<Participant> participants = participantService.getParticipantsByEventId(eventId);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

 // Create a new participant for an event
    @PostMapping("/{eventId}/participants")
    public ResponseEntity<Participant> createParticipant(@PathVariable Long eventId, @RequestBody Participant participant) {
        Participant createdParticipant = participantService.createParticipant(participant);
        return new ResponseEntity<>(createdParticipant, HttpStatus.CREATED);
    }

    
 // Update an existing participant for an event
    @PutMapping("/{eventId}/participants/{participantId}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long eventId, @PathVariable Long participantId, @RequestBody Participant participant) {
        Participant updatedParticipant = participantService.updateParticipant(eventId, participantId, participant);
        if (updatedParticipant != null) {
            return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 // Delete a participant for an event
    @DeleteMapping("/{eventId}/participants/{participantId}")
    public ResponseEntity<HttpStatus> deleteParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        participantService.deleteParticipant(participantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

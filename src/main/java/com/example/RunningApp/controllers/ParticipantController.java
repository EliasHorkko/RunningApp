package com.example.RunningApp.controllers;

import com.example.RunningApp.models.Participant;
import com.example.RunningApp.services.ParticipantService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

	private final ParticipantService participantService;

	// This is the constructor for the ParticipantController class
	public ParticipantController(ParticipantService participantService) {
	    this.participantService = participantService;
	}

	// This is the POST request that creates a new Participant object
	@PostMapping
	public ResponseEntity<Participant> createParticipant(@Valid @RequestBody Participant participant) {
	    Participant createdParticipant = participantService.createParticipant(participant);
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipant);
	}

	// This is the GET request that retrieves a Participant object by ID
	@GetMapping("/{participantId}")
	public ResponseEntity<Participant> getParticipantById(@PathVariable Long participantId) {
	    Participant participant = participantService.getParticipantById(participantId);
	    return ResponseEntity.ok(participant);
	}

	// This is the PUT request that updates a Participant object
	@PutMapping("/{participantId}")
	public ResponseEntity<Participant> updateParticipant(@PathVariable Long eventId, @PathVariable Long participantId, @RequestBody Participant participant) {
	    Participant updatedParticipant = participantService.updateParticipant(participantId, participantId, participant);
	    return ResponseEntity.ok(updatedParticipant);
	}

	// This is the DELETE request that deletes a Participant object
	@DeleteMapping("/{participantId}")
	public ResponseEntity<Void> deleteParticipant(@PathVariable Long participantId) {
	    participantService.deleteParticipant(participantId);
	    return ResponseEntity.noContent().build();
	}

	// This is the GET request that retrieves all Participant objects
	@GetMapping
	public ResponseEntity<List<Participant>> getAllParticipants() {
	    List<Participant> participants = participantService.getAllParticipants();
	    return ResponseEntity.ok(participants);
	}

}

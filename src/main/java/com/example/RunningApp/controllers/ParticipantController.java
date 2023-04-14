package com.example.RunningApp.controllers;

import com.example.RunningApp.models.Participant;
import com.example.RunningApp.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping("/{id}")
    public Participant getParticipant(@PathVariable Long id) {
        return participantRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantRepository.save(participant);
    }

    @PutMapping("/{id}")
    public Participant updateParticipant(@PathVariable Long id, @RequestBody Participant participant) {
        participant.setId(id);
        return participantRepository.save(participant);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Long id) {
        participantRepository.deleteById(id);
    }

}

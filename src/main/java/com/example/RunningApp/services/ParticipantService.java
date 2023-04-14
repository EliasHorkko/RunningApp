package com.example.RunningApp.services;

import com.example.RunningApp.models.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {
    List<Participant> getAllParticipants();
    Optional<Participant> getParticipantById(Long id);
    Participant saveParticipant(Participant participant);
    void deleteParticipant(Long id);
}

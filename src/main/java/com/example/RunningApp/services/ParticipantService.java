package com.example.RunningApp.services;

import com.example.RunningApp.models.Participant;

import java.util.List;

public interface ParticipantService {
    Participant createParticipant(Participant participant);
    Participant getParticipantById(Long id);
    List<Participant> getParticipantsByEventId(Long eventId);
    List<Participant> getAllParticipants();
    public Participant updateParticipant(Long eventId, Long participantId, Participant participant);
    void deleteParticipant(Long id);
}
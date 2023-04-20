package com.example.RunningApp.services;

import com.example.RunningApp.models.Event;
import com.example.RunningApp.models.Participant;
import com.example.RunningApp.repositories.EventRepository;
import com.example.RunningApp.repositories.ParticipantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional // declares this class as transactional
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    @Autowired // injects dependencies through constructor injection
    public ParticipantServiceImpl(ParticipantRepository participantRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant); // saves the participant using the repository
    }

    @Override
    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id).orElse(null); // gets the participant by ID using the repository
    }

    @Override
    public List<Participant> getParticipantsByEventId(Long eventId) {
        return participantRepository.findByEventId(eventId); // gets a list of participants by event ID using the repository
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll(); // gets all participants using the repository
    }
    
    @Override
    public Participant updateParticipant(Long eventId, Long participantId, Participant participant) {
        // finds the event by ID using the event repository
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            return null; // returns null if the event is not found
        }
        Event event = optionalEvent.get();
        // finds the participant by ID using a stream and a filter operation
        Optional<Participant> optionalParticipant = event.getParticipants().stream()
            .filter(p -> p.getId().equals(participantId))
            .findFirst();
        if (optionalParticipant.isEmpty()) {
            return null; // returns null if the participant is not found
        }
        Participant existingParticipant = optionalParticipant.get();
        // updates the participant's name and email
        existingParticipant.setName(participant.getName());
        existingParticipant.setEmail(participant.getEmail());
        return participantRepository.save(existingParticipant); // saves the updated participant using the repository
    }

    @Override
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id); // deletes the participant by ID using the repository
    }

    @Override
    public void saveParticipant(Participant participant) {
        participantRepository.save(participant); // saves the participant using the repository
    }
}

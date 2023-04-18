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
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant getParticipantById(Long id) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        return optionalParticipant.orElse(null);
    }

    @Override
    public List<Participant> getParticipantsByEventId(Long eventId) {
        return participantRepository.findByEventId(eventId);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }
    
	@Override
	public Participant updateParticipant(Long eventId, Long participantId, Participant participant) {
	    Optional<Event> optionalEvent = eventRepository.findById(eventId);
	    if (optionalEvent.isEmpty()) {
	        return null;
	    }
	    Event event = optionalEvent.get();
	    Optional<Participant> optionalParticipant = event.getParticipants().stream()
	        .filter(p -> p.getId().equals(participantId))
	        .findFirst();
	    if (optionalParticipant.isEmpty()) {
	        return null;
	    }
	    Participant existingParticipant = optionalParticipant.get();
	    existingParticipant.setName(participant.getName());
	    existingParticipant.setEmail(participant.getEmail());
	    return participantRepository.save(existingParticipant);
	}

    @Override
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

}

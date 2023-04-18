package com.example.RunningApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RunningApp.models.Event;
import com.example.RunningApp.models.Participant;
import com.example.RunningApp.models.User;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByEvent(Event event);

    List<Participant> findAllByUser(User user);

    Optional<Participant> findByEventAndUser(Event event, User user);

    void deleteAllByEvent(Event event);

	List<Participant> findByEventId(Long eventId);
}


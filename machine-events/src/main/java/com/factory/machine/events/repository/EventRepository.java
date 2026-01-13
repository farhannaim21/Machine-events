package com.factory.machine.events.repository;

import com.factory.machine.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByEventId(String eventId);

    List<Event> findByMachineIdAndEventTimeBetween(
            String machineId, Instant start, Instant end);
}

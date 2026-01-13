package com.factory.machine.events.service;

import com.factory.machine.events.dto.StatsResponse;
import com.factory.machine.events.model.Event;
import com.factory.machine.events.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class StatsService {

    private final EventRepository repo;

    public StatsService(EventRepository repo) {
        this.repo = repo;
    }

    public StatsResponse getStats(String machineId, Instant start, Instant end) {
        List<Event> events = repo.findByMachineIdAndEventTimeBetween(machineId, start, end);

        long defects = events.stream()
                .filter(e -> e.getDefectCount() != -1)
                .mapToLong(Event::getDefectCount)
                .sum();

        double hours = Duration.between(start, end).toSeconds() / 3600.0;
        double rate = defects / hours;

        StatsResponse res = new StatsResponse();
        res.machineId = machineId;
        res.eventsCount = events.size();
        res.defectsCount = defects;
        res.avgDefectRate = rate;
        res.status = rate < 2.0 ? "Healthy" : "Warning";

        return res;
    }
}

package com.factory.machine.events.service;

import com.factory.machine.events.dto.BatchResponse;
import com.factory.machine.events.dto.Rejection;
import com.factory.machine.events.model.Event;
import com.factory.machine.events.repository.EventRepository;
import com.factory.machine.events.util.HashUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class EventService {

    private final EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public BatchResponse ingest(List<Event> events) {
        BatchResponse response = new BatchResponse();

        for (Event e : events) {

            // Validation
            if (e.getDurationMs() < 0 || e.getDurationMs() > 21600000) {
                response.rejected++;
                response.rejections.add(new Rejection(e.getEventId(), "INVALID_DURATION"));
                continue;
            }

            if (e.getEventTime().isAfter(Instant.now().plusSeconds(900))) {
                response.rejected++;
                response.rejections.add(new Rejection(e.getEventId(), "FUTURE_EVENT"));
                continue;
            }

            e.setReceivedTime(Instant.now());

            String hash = HashUtil.sha256(
                    e.getMachineId() + e.getDurationMs() + e.getDefectCount() + e.getEventTime()
            );
            e.setPayloadHash(hash);

            repo.findByEventId(e.getEventId()).ifPresentOrElse(existing -> {
                if (existing.getPayloadHash().equals(hash)) {
                    response.deduped++;
                } else if (e.getReceivedTime().isAfter(existing.getReceivedTime())) {
                    e.setId(existing.getId());
                    repo.save(e);
                    response.updated++;
                }
            }, () -> {
                repo.save(e);
                response.accepted++;
            });
        }
        return response;
    }
}

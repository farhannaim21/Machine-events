package com.factory.machine.events.controller;

import com.factory.machine.events.dto.BatchResponse;
import com.factory.machine.events.model.Event;
import com.factory.machine.events.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping("/batch")
    public BatchResponse ingest(@RequestBody List<Event> events) {
        return service.ingest(events);
    }
}

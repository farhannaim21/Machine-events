package com.factory.machine.events.controller;

import com.factory.machine.events.dto.StatsResponse;
import com.factory.machine.events.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping
    public StatsResponse stats(
            @RequestParam String machineId,
            @RequestParam Instant start,
            @RequestParam Instant end) {
        return service.getStats(machineId, start, end);
    }
}

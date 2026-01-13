package com.factory.machine.events;

import java.time.Instant;

import com.factory.machine.events.model.Event;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.factory.machine.events.service.EventService;

@SpringBootTest
class MachineEventsApplicationTests {
	@Autowired
    EventService service;

	@Test
	void contextLoads() {
		Event e = new Event();
        e.setEventId("E1");
        e.setMachineId("M1");
        e.setDurationMs(1000L);
        e.setDefectCount(0);
        e.setEventTime(Instant.now());

        service.ingest(List.of(e, e));
	}

}

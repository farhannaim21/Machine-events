package com.factory.machine.events.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "events", uniqueConstraints = {
        @UniqueConstraint(columnNames = "eventId")
})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;
    private Instant eventTime;
    private Instant receivedTime;
    private String machineId;
    private Long durationMs;
    private Integer defectCount;
    private String payloadHash;

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public Instant getEventTime() {
        return eventTime;
    }

    public Instant getReceivedTime() {
        return receivedTime;
    }

    public String getMachineId() {
        return machineId;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public Integer getDefectCount() {
        return defectCount;
    }

    public String getPayloadHash() {
        return payloadHash;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventTime(Instant eventTime) {
        this.eventTime = eventTime;
    }

    public void setReceivedTime(Instant receivedTime) {
        this.receivedTime = receivedTime;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public void setDefectCount(Integer defectCount) {
        this.defectCount = defectCount;
    }

    public void setPayloadHash(String payloadHash) {
        this.payloadHash = payloadHash;
    }
}

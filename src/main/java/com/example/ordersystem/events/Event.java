package com.example.ordersystem.events;

import java.time.Instant;
import java.util.UUID;

public abstract class Event {
    protected String eventId;
    protected Instant timestamp;
    protected String eventType;

    public Event(String eventId, Instant timestamp, String eventType) {
        this.eventId = eventId != null ? eventId : UUID.randomUUID().toString();
        this.timestamp = timestamp != null ? timestamp : Instant.now();
        this.eventType = eventType;
    }

    public String getEventId() { return eventId; }
    public Instant getTimestamp() { return timestamp; }
    public String getEventType() { return eventType; }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", timestamp=" + timestamp +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}

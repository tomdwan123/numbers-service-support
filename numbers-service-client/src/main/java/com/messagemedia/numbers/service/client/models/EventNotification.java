/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class EventNotification {

    private final UUID eventId;
    private final OffsetDateTime eventTimestamp;
    private final Event event;
    private final NumberDto number;
    private final AssignmentDto assignment;

    public EventNotification(UUID eventId, OffsetDateTime eventTimestamp, Event event, NumberDto number, AssignmentDto assignment) {
        this.eventId = eventId;
        this.eventTimestamp = eventTimestamp;
        this.event = event;
        this.number = number;
        this.assignment = assignment;
    }

    public EventNotification(Event event, NumberDto number, AssignmentDto assignment) {
        this(UUID.randomUUID(), OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC), event, number, assignment);
    }

    public UUID getEventId() {
        return eventId;
    }

    public OffsetDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public Event getEvent() {
        return event;
    }

    public NumberDto getNumber() {
        return number;
    }

    public AssignmentDto getAssignment() {
        return assignment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("eventId", eventId)
                .append("eventTimestamp", eventTimestamp)
                .append("event", event)
                .append("number", number)
                .append("assignment", assignment)
                .toString();
    }
}

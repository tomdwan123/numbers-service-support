/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;

import static com.messagemedia.framework.test.AccessorAsserter.assertGetters;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.TestData.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventNotificationTest {

    @Test
    public void testAccessors() throws Exception {
        assertGetters(randomEventNotification());
    }

    @Test
    public void testToString() {
        assertToString(randomEventNotification());
    }

    @Test
    public void shouldSerializeCorrectly() throws Exception {
        EventNotification eventNotification = randomEventNotification();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String s = mapper.writeValueAsString(eventNotification);
        assertThat(s, containsString("eventId"));
        assertThat(s, containsString("eventTimestamp"));
        assertThat(s, containsString("event"));
        assertThat(s, containsString("number"));
        assertThat(s, containsString("assignment"));
    }
}

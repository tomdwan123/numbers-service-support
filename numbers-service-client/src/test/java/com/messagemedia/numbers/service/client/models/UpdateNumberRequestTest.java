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
import com.messagemedia.framework.jackson.core.valuewithnull.ValueWithNull;
import org.junit.Test;

import java.time.OffsetDateTime;

import static com.messagemedia.framework.test.AccessorAsserter.assertGetters;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.TestData.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.*;

public class UpdateNumberRequestTest {

    @Test
    public void testAccessors() throws Exception {
        assertGetters(randomUpdateNumberRequest());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(new UpdateNumberRequest(null, null, null, null, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(randomClassification(), null, null, null, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(null, randomCapabilities(), null, null, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(null, null, ValueWithNull.of(OffsetDateTime.now()), null, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(randomClassification(), randomCapabilities(), null, null, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(randomClassification(), null, ValueWithNull.of(OffsetDateTime.now()), null, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(null, randomCapabilities(), ValueWithNull.of(OffsetDateTime.now()), null, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(null, null, ValueWithNull.of(OffsetDateTime.now()), FALSE, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(null, randomCapabilities(), null, TRUE, null, null).isEmpty());
        assertFalse(new UpdateNumberRequest(null, null, null, null, Status.PENDING, null).isEmpty());
        assertFalse(randomUpdateNumberRequest().isEmpty());
    }

    @Test
    public void testHasStatus() {
        assertTrue(new UpdateNumberRequest(null, null, null, null, Status.ASSIGNED, null).hasStatus());
        assertTrue(new UpdateNumberRequest(null, randomCapabilities(), ValueWithNull.of(OffsetDateTime.now()), null, Status.PENDING,
                null).hasStatus());
        assertFalse(new UpdateNumberRequest(null, randomCapabilities(), null, null, null, null).hasStatus());
        assertFalse(new UpdateNumberRequest(null, null, ValueWithNull.of(OffsetDateTime.now()), null, null, null).hasStatus());
    }

    @Test
    public void testNullValue() {
        UpdateNumberRequest updateNumberRequest1 = new UpdateNumberRequest(null, null, null, null, null, null);
        assertNull(updateNumberRequest1.getClassification());
        assertNull(updateNumberRequest1.getCapabilities());
        assertNull(updateNumberRequest1.getAvailableAfter());
        assertNull(updateNumberRequest1.getDedicatedReceiver());
        assertNull(updateNumberRequest1.getStatus());
        assertNull(updateNumberRequest1.getProviderId());

        UpdateNumberRequest updateNumberRequest2 = new UpdateNumberRequest(null, null, ValueWithNull.explicitNull(), null, null, null);
        assertNull(updateNumberRequest2.getClassification());
        assertNull(updateNumberRequest2.getCapabilities());
        assertNull(updateNumberRequest2.getAvailableAfter().get());
        assertNull(updateNumberRequest1.getDedicatedReceiver());
        assertNull(updateNumberRequest1.getStatus());
        assertNull(updateNumberRequest1.getProviderId());
    }

    @Test
    public void testToString() {
        assertToString(randomUpdateNumberRequest());
    }

    @Test
    public void shouldSerializeAndDeserializeCorrectly() throws Exception {
        UpdateNumberRequest request = randomUpdateNumberRequest();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String s = mapper.writeValueAsString(request);
        UpdateNumberRequest fromJson = mapper.readValue(s, UpdateNumberRequest.class);
        String f = mapper.writeValueAsString(fromJson);
        assertEquals(s, f);
    }
}

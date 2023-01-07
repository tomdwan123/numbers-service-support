/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagemedia.framework.jackson.core.valuewithnull.ValueWithNull;
import org.junit.Test;

import static com.messagemedia.framework.test.AccessorAsserter.assertGetters;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.TestData.randomUpdateAssignmentRequest;
import static org.junit.Assert.*;

public class UpdateAssignmentRequestTest {

    @Test
    public void testAccessors() throws Exception {
        assertGetters(randomUpdateAssignmentRequest());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(new UpdateAssignmentRequest(null, null, null).isEmpty());
        assertFalse(new UpdateAssignmentRequest(null, ValueWithNull.explicitNull(), null).isEmpty());
        assertFalse(new UpdateAssignmentRequest(ValueWithNull.explicitNull(), null, null).isEmpty());
        assertFalse(new UpdateAssignmentRequest(null, null, ValueWithNull.explicitNull()).isEmpty());
        assertFalse(new UpdateAssignmentRequest(ValueWithNull.explicitNull(), ValueWithNull.explicitNull(), null).isEmpty());
        assertFalse(new UpdateAssignmentRequest(ValueWithNull.explicitNull(), null, ValueWithNull.explicitNull()).isEmpty());
        assertFalse(new UpdateAssignmentRequest(null, ValueWithNull.explicitNull(), ValueWithNull.explicitNull()).isEmpty());
        assertFalse(new UpdateAssignmentRequest(ValueWithNull.explicitNull(), ValueWithNull.explicitNull(), null).isEmpty());
        assertFalse(randomUpdateAssignmentRequest().isEmpty());
    }

    @Test
    public void testNullValue() {
        UpdateAssignmentRequest assignmentRequest1 = new UpdateAssignmentRequest(ValueWithNull.explicitNull(),
                ValueWithNull.explicitNull(),
                ValueWithNull.explicitNull());
        assertNull(assignmentRequest1.getCallbackUrlValue());
        assertNull(assignmentRequest1.getMetadataValue());
        assertNull(assignmentRequest1.getLabelValue());

        UpdateAssignmentRequest assignmentRequest2 = new UpdateAssignmentRequest(null, null, null);
        assertNull(assignmentRequest2.getCallbackUrlValue());
        assertNull(assignmentRequest2.getMetadataValue());
        assertNull(assignmentRequest2.getLabel());
    }

    @Test
    public void testToString() {
        assertToString(randomUpdateAssignmentRequest());
    }

    @Test
    public void shouldSerializeAndDeserializeCorrectly() throws Exception {
        UpdateAssignmentRequest request = randomUpdateAssignmentRequest();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(request);
        UpdateAssignmentRequest fromJson = mapper.readValue(s, UpdateAssignmentRequest.class);
        String f = mapper.writeValueAsString(fromJson);
        assertEquals(s, f);
    }
}

/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.messagemedia.framework.config.JsonConfig;
import com.messagemedia.framework.json.JsonFastMapper;
import org.junit.Test;

import static com.messagemedia.framework.test.AccessorAsserter.assertGetters;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.TestData.randomAssignNumberRequest;
import static org.junit.Assert.assertEquals;

public class AssignNumberRequestTest {

    @Test
    public void testAccessors() throws Exception {
        assertGetters(randomAssignNumberRequest());
    }

    @Test
    public void testToString() {
        assertToString(randomAssignNumberRequest());
    }

    @Test
    public void shouldSerializeAndDeserializeCorrectly() throws Exception {
        AssignNumberRequest request = randomAssignNumberRequest();
        JsonFastMapper mapper = new JsonConfig().fastMapper();
        String s = mapper.toJsonString(request);
        AssignNumberRequest fromJson = mapper.readFrom(s, AssignNumberRequest.class);
        String f = mapper.toJsonString(fromJson);
        assertEquals(s, f);
    }
}
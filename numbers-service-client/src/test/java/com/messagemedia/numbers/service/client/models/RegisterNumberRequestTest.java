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
import static com.messagemedia.numbers.service.client.models.TestData.randomRegisterNumberRequest;
import static org.junit.Assert.assertEquals;

public class RegisterNumberRequestTest {

    @Test
    public void testAccessors() throws Exception {
        assertGetters(randomRegisterNumberRequest());
    }

    @Test
    public void testToString() {
        assertToString(randomRegisterNumberRequest());
    }

    @Test
    public void shouldSerializeAndDeserializeCorrectly() throws Exception {
        RegisterNumberRequest request = randomRegisterNumberRequest();
        JsonFastMapper mapper = new JsonConfig().fastMapper();
        String s = mapper.toJsonString(request);
        RegisterNumberRequest fromJson = mapper.readFrom(s, RegisterNumberRequest.class);
        String f = mapper.toJsonString(fromJson);
        assertEquals(s, f);
    }
}
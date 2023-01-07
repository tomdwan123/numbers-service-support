/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagemedia.framework.config.JsonConfig;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ServiceTypeTest {

    static final ObjectMapper MAPPER = new JsonConfig().objectMapper();

    @Test
    public void shouldSerializeCorrectly() throws Exception {
        ServiceType type = ServiceType.SMS;
        String expected = "\"SMS\"";
        String actual = MAPPER.writeValueAsString(type);
        assertThat(expected, is(actual));
    }

    @Test
    public void shouldDeserializeCorrectly() throws Exception {
        ServiceType expected = ServiceType.SMS;
        ServiceType actual = MAPPER.readValue("\"SMS\"", ServiceType.class);
        assertThat(expected, is(actual));
    }

    @Test
    public void shouldSerializeCorrectlyAsArray() throws Exception {
        ServiceType[] types = new ServiceType[]{ServiceType.SMS, ServiceType.MMS, ServiceType.TTS, ServiceType.CALL};
        String expected = "[\"SMS\",\"MMS\",\"TTS\",\"CALL\"]";
        String actual = MAPPER.writeValueAsString(types);
        assertThat(expected, is(actual));
    }

    @Test
    public void shouldDeserializeCorrectlyAsArray() throws Exception {
        ServiceType[] expected = new ServiceType[]{ServiceType.SMS, ServiceType.MMS, ServiceType.TTS, ServiceType.CALL};
        ServiceType[] actual = MAPPER.readValue("[\"SMS\",\"MMS\",\"TTS\",\"CALL\"]", ServiceType[].class);
        assertThat(expected, is(actual));
    }
}

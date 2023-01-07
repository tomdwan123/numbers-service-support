/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.NumberSearchRequest.NumberSearchRequestBuilder.aNumberSearchRequestBuilder;
import static com.messagemedia.numbers.service.client.models.TestData.randomCountryCode;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumberSearchRequestTest {
    private NumberSearchRequest createNumberSearchRequest() {
        return aNumberSearchRequestBuilder()
                .withToken(UUID.randomUUID()).withAssigned(true).withCountry(randomCountryCode()).withMatching("+3_6%")
                .withPageSize(40).withServiceTypes(new ServiceType[]{ServiceType.SMS, ServiceType.TTS})
                .withExactServiceTypes(false)
                .withClassification(Classification.BRONZE)
                .withAvailableBy(OffsetDateTime.now())
                .build();
    }

    @Test
    public void testAccessors() {
        NumberSearchRequest request = createNumberSearchRequest();
        request.setAssigned(true);
        request.setAvailableBy(OffsetDateTime.MAX);
        request.setClassification(Classification.GOLD);
        request.setCountry("A Country");
        request.setMatching("A Match");
        request.setPageSize(Integer.MAX_VALUE);
        request.setServiceTypes(new ServiceType[]{ServiceType.TTS});
        request.setExactServiceTypes(Boolean.TRUE);
        request.setToken(UUID.fromString("783d8812-b8a4-45cc-9916-d7d9a53ae656"));

        assertTrue(request.getAssigned());
        assertEquals(OffsetDateTime.MAX, request.getAvailableBy());
        assertEquals(Classification.GOLD, request.getClassification());
        assertEquals("A Country", request.getCountry());
        assertEquals("A Match", request.getMatching());
        assertEquals(Integer.MAX_VALUE, request.getPageSize());
        assertTrue(request.getExactServiceTypes());
        assertArrayEquals(new ServiceType[]{ServiceType.TTS}, request.getServiceTypes());
        assertEquals(UUID.fromString("783d8812-b8a4-45cc-9916-d7d9a53ae656"), request.getToken());
    }

    @Test
    public void testToString() {
        assertToString(createNumberSearchRequest());
    }
}

/*
 * Copyright (c) Message4U Pty Ltd 2014-2021
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.junit.Test;

import java.time.OffsetDateTime;

import static com.messagemedia.framework.test.AccessorAsserter.assertGettersAndSetters;
import static com.messagemedia.framework.test.AccessorAsserter.registerTestInstanceFor;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.TestData.randomNumberForwardDto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NumberForwardDtoTest {

    @Test
    public void testConstructor() {
        NumberForwardDto numberForwardDto1 = new NumberForwardDto();
        assertNull(numberForwardDto1.getDestination());
        NumberForwardDto numberForwardDto2 = new NumberForwardDto("+123456789");
        assertEquals("+123456789", numberForwardDto2.getDestination());
    }

    @Test
    public void testAccessors() throws Exception {
        registerTestInstanceFor(OffsetDateTime.class, OffsetDateTime.now());
        assertGettersAndSetters(randomNumberForwardDto());
    }

    @Test
    public void testToString() {
        assertToString(randomNumberForwardDto());
    }
}
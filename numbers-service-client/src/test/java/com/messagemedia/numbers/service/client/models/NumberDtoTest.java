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
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.messagemedia.framework.test.AccessorAsserter.assertGettersAndSetters;
import static com.messagemedia.framework.test.AccessorAsserter.registerTestInstanceFor;
import static com.messagemedia.framework.test.CanonicalAsserter.assertCanonical;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.TestData.randomNumberDto;
import static com.messagemedia.numbers.service.client.models.TestData.randomNumberDtoBuilder;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberDtoTest {

    @Test
    public void testConstructor() {
        NumberDto original = randomNumberDto();
        NumberDto copy = new NumberDto(original);
        Assert.assertEquals(original, copy);
    }

    @Test
    public void testAccessors() throws Exception {
        registerTestInstanceFor(OffsetDateTime.class, OffsetDateTime.now());
        assertGettersAndSetters(randomNumberDto());
    }

    @Test
    public void testToString() {
        assertToString(randomNumberDto());
    }

    @Test
    public void testCanonical() {
        UUID id = UUID.randomUUID();
        assertCanonical(randomNumberDtoBuilder().withId(id).build(),
                randomNumberDtoBuilder().withId(id).build(),
                randomNumberDto());
    }

    @Test
    public void shouldSerializeCorrectly() throws Exception {
        NumberDto numberDto = randomNumberDto();
        numberDto.setAvailableAfter(null);
        JsonFastMapper mapper = new JsonConfig().fastMapper();
        String s = mapper.toJsonString(numberDto);
        assertThat(s, containsString("availableAfter"));
    }
}
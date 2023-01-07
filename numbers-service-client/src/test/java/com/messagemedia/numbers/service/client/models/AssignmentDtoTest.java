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

import static com.messagemedia.framework.test.AccessorAsserter.assertGettersAndSetters;
import static com.messagemedia.framework.test.AccessorAsserter.registerTestInstanceFor;
import static com.messagemedia.framework.test.CanonicalAsserter.assertCanonical;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.TestData.*;

public class AssignmentDtoTest {

    @Test
    public void testAccessors() throws Exception {
        registerTestInstanceFor(OffsetDateTime.class, OffsetDateTime.now());
        assertGettersAndSetters(randomAssignNumberDto());
    }

    @Test
    public void testToString() {
        assertToString(randomAssignNumberDto());
    }

    @Test
    public void testCanonical() {
        UUID id = UUID.randomUUID();
        assertCanonical(randomAssignmentDtoBuilder().withId(id).build(),
                randomAssignmentDtoBuilder().withId(id).build(),
                randomAssignNumberDto());
    }

    @Test
    public void testCanonicalWithMinimumFields() {
        UUID id = UUID.randomUUID();
        assertCanonical(randomAssignmentDtoBuilderWithMinimumFields().withId(id).build(),
                randomAssignmentDtoBuilderWithMinimumFields().withId(id).build(),
                randomAssignmentDtoBuilderWithMinimumFields());
    }
}
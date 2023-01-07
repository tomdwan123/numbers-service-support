/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.messagemedia.domainmodels.accounts.VendorAccountId;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.messagemedia.framework.test.AccessorAsserter.assertGettersAndSetters;
import static com.messagemedia.framework.test.AccessorAsserter.registerTestInstanceFor;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.AssignmentAuditSearchRequest.AssignmentAuditSearchRequestBuilder;
import static com.messagemedia.numbers.service.client.models.TestData.randomAuditToken;

public class AssignmentAuditSearchRequestTest {

    @Test
    public void testAccessors() throws Exception {
        registerTestInstanceFor(OffsetDateTime.class, OffsetDateTime.now());
        registerTestInstanceFor(VendorAccountId.class, VendorAccountId.GLOBAL);
        registerTestInstanceFor(AuditToken.class, randomAuditToken());
        assertGettersAndSetters(new AssignmentAuditSearchRequest());
    }

    @Test
    public void testToString() {
        AssignmentAuditSearchRequest assignmentAuditSearchRequest = AssignmentAuditSearchRequestBuilder.anAssignmentAuditSearchRequest()
                .withId(UUID.randomUUID())
                .withCreatedAfter(OffsetDateTime.now())
                .withCreatedBefore(OffsetDateTime.now())
                .withDeletedAfter(OffsetDateTime.MIN)
                .withDeletedBefore(OffsetDateTime.MAX)
                .withNumberId(UUID.randomUUID())
                .withPageSize(1)
                .withToken(randomAuditToken())
                .withVendorAccountId(VendorAccountId.GLOBAL)
                .build();
        assertToString(assignmentAuditSearchRequest);
    }
}
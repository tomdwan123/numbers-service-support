/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.junit.Test;

import java.util.UUID;

import static com.messagemedia.framework.test.AccessorAsserter.assertGettersAndSetters;
import static com.messagemedia.framework.test.AccessorAsserter.registerTestInstanceFor;
import static com.messagemedia.framework.test.CanonicalAsserter.assertToString;
import static com.messagemedia.numbers.service.client.models.NumberAssignmentSearchRequest.NumberAssignmentSearchRequestBuilder;
import static com.messagemedia.numbers.service.client.models.TestData.randomCountryCode;

public class NumberAssignmentSearchRequestTest {

    @Test
    public void testAccessors() throws Exception {
        registerTestInstanceFor(ServiceType[].class, new ServiceType[]{ServiceType.SMS});
        registerTestInstanceFor(String[].class, new String[]{});
        assertGettersAndSetters(createNumberAssignmentSearchRequest());
    }

    @Test
    public void testToString() {
        assertToString(createNumberAssignmentSearchRequest());
    }

    private NumberAssignmentSearchRequest createNumberAssignmentSearchRequest() {
        return NumberAssignmentSearchRequestBuilder.aNumberAssignmentSearchRequestBuilder()
                .withPageSize(3)
                .withToken(UUID.randomUUID())
                .withVendorId("vendor")
                .withAccountId("account")
                .withServiceTypes(new ServiceType[]{ServiceType.SMS, ServiceType.TTS})
                .withClassification(Classification.BRONZE)
                .withCountry(randomCountryCode())
                .withMatching("+3_6%")
                .withLabel("MyLabel%")
                .withStatus(Status.UNVERIFIED)
                .withAccounts(new String[]{})
                .build();
    }
}

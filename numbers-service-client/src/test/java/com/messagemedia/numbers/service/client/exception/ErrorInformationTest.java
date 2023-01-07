/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.exception;

import com.messagemedia.framework.test.AccessorAsserter;
import com.messagemedia.framework.test.CanonicalAsserter;
import org.junit.Test;

public class ErrorInformationTest {

    @Test
    public void testEntityInformation() throws Exception {
        ErrorInformation instance = new ErrorInformation("some event", "some issue");
        CanonicalAsserter.assertToString(instance);
        AccessorAsserter.assertGetters(instance);
    }
}

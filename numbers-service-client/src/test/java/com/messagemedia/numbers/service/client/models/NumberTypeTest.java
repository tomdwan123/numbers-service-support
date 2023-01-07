/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.junit.Test;

import static com.messagemedia.framework.test.AccessorAsserter.assertEnum;

public class NumberTypeTest {

    @Test
    public void test() throws Exception {
        assertEnum(NumberType.class);
    }
}
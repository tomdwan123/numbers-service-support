/*
 * Copyright (c) Message4U Pty Ltd 2014-2021
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.exception;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumbersServiceForbiddenExceptionTest {

    @Test
    public void shouldConstructExceptionWithMessage() {
        assertThat(new NumbersServiceForbiddenException("Forbidden").getMessage(), is("Forbidden"));
    }

    @Test
    public void shouldConstructExceptionWithMessageAndErrorInformation() {
        String msg = "Forbidden";
        ErrorInformation errorInfo = new ErrorInformation(msg, msg);
        assertThat(new NumbersServiceForbiddenException(msg).getMessage(), is(msg));
        assertThat(new NumbersServiceForbiddenException(msg, errorInfo).getErrorInformation().get(), is(errorInfo));
    }

    public void shouldConstructExceptionWithMessageAndReason() {
        assertThat(new NumbersServiceForbiddenException("Forbidden").getMessage(), is("Forbidden"));
    }
}

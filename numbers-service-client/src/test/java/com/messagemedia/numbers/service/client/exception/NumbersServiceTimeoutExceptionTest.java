/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.exception;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class NumbersServiceTimeoutExceptionTest {

    @Test
    public void shouldConstructExceptionWithMessage() {
        assertThat(new NumbersServiceTimeoutException("Service failure").getMessage(), is("Service failure"));
    }

    @Test
    public void shouldConstructExceptionWithMessageAndCause() {
        Throwable cause = new IOException();
        NumbersServiceTimeoutException exception = new NumbersServiceTimeoutException("Service failure", cause);

        assertThat(exception.getMessage(), is("Service failure"));
        assertThat(exception.getCause(), sameInstance(cause));
    }
}

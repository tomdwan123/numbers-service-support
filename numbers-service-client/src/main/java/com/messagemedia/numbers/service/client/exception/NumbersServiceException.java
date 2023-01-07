/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */
package com.messagemedia.numbers.service.client.exception;

import java.util.Optional;

public class NumbersServiceException extends Exception {

    private Optional<ErrorInformation> errorInformation;

    public NumbersServiceException(String message) {
        this(message, (ErrorInformation) null);
    }

    public NumbersServiceException(String message, ErrorInformation errorInformation) {
        this(message, errorInformation, null);
    }

    public NumbersServiceException(String message, Throwable cause) {
        this(message, null, cause);
    }

    public NumbersServiceException(String message, ErrorInformation errorInformation, Throwable cause) {
        super(message, cause);
        this.errorInformation = Optional.ofNullable(errorInformation);
    }

    public Optional<ErrorInformation> getErrorInformation() {
        return errorInformation;
    }
}

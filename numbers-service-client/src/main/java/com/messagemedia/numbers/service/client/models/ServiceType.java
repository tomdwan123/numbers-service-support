/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ServiceType {
    SMS("SMS"),
    TTS("TTS"),
    MMS("MMS"),
    CALL("CALL");

    private final String value;

    ServiceType(String value) {
        this.value = value;
    }

    @Override
    @JsonCreator
    @JsonValue
    public String toString() {
        return value;
    }
}

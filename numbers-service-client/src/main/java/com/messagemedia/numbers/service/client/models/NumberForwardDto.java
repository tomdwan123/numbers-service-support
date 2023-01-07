/*
 * Copyright (c) Message4U Pty Ltd 2014-2021
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class NumberForwardDto {

    @JsonProperty("destination")
    @JsonInclude()
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Destination should be in E.164 format")
    private String destination;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public NumberForwardDto() {
    }

    public NumberForwardDto(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("destination", destination)
                .toString();
    }
}

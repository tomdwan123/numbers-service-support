/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.messagemedia.numbers.service.client.models.validator.ValidCountryCode;
import com.messagemedia.numbers.service.client.models.validator.ValidPhoneNumber;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@ValidPhoneNumber
public final class RegisterNumberRequest {

    @NotBlank
    private final String phoneNumber;

    @NotNull
    private final UUID providerId;

    @ValidCountryCode
    @NotBlank
    private final String country;

    @NotNull
    private final NumberType type;

    @NotNull
    private final Classification classification;

    @NotNull
    @Size(min = 1)
    private final Set<ServiceType> capabilities;

    private final Boolean dedicatedReceiver;

    @JsonCreator
    public RegisterNumberRequest(@JsonProperty("phoneNumber") String phoneNumber,
                                 @JsonProperty("providerId") UUID providerId,
                                 @JsonProperty("country") String country,
                                 @JsonProperty("type") NumberType type,
                                 @JsonProperty("classification") Classification classification,
                                 @JsonProperty("capabilities") Set<ServiceType> capabilities,
                                 @JsonProperty("dedicatedReceiver") Boolean dedicatedReceiver) {
        this.phoneNumber = phoneNumber;
        this.providerId = providerId;
        this.country = country;
        this.type = type;
        this.classification = classification;
        this.capabilities = capabilities;
        this.dedicatedReceiver = dedicatedReceiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getProviderId() {
        return providerId;
    }

    public String getCountry() {
        return country;
    }

    public NumberType getType() {
        return type;
    }

    public Classification getClassification() {
        return classification;
    }

    public Set<ServiceType> getCapabilities() {
        return capabilities;
    }

    public Boolean getDedicatedReceiver() {
        return dedicatedReceiver;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("phoneNumber", phoneNumber)
                .append("providerId", providerId)
                .append("country", country)
                .append("type", type)
                .append("classification", classification)
                .append("capabilities", capabilities)
                .append("dedicatedReceiver", dedicatedReceiver)
                .toString();
    }
}

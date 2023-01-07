/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.messagemedia.framework.jackson.core.valuewithnull.ValueWithNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public class UpdateNumberRequest {

    private final Classification classification;

    private final UUID providerId;

    @Size(min = 1)
    private final Set<ServiceType> capabilities;

    private final ValueWithNull<OffsetDateTime> availableAfter;

    private final Boolean dedicatedReceiver;

    private final Status status;

    @JsonCreator
    public UpdateNumberRequest(@JsonProperty("classification") Classification classification,
                               @JsonProperty("capabilities") Set<ServiceType> capabilities,
                               @JsonProperty("availableAfter") ValueWithNull<OffsetDateTime> availableAfter,
                               @JsonProperty("dedicatedReceiver") Boolean dedicatedReceiver,
                               @JsonProperty("status") Status status,
                               @JsonProperty("providerId") UUID providerId) {
        this.classification = classification;
        this.capabilities = capabilities;
        this.availableAfter = availableAfter;
        this.dedicatedReceiver = dedicatedReceiver;
        this.status = status;
        this.providerId = providerId;
    }

    public Classification getClassification() {
        return classification;
    }

    public Set<ServiceType> getCapabilities() {
        return capabilities;
    }

    public ValueWithNull<OffsetDateTime> getAvailableAfter() {
        return availableAfter;
    }

    public Boolean getDedicatedReceiver() {
        return dedicatedReceiver;
    }

    public Status getStatus() {
        return status;
    }

    public UUID getProviderId() {
        return providerId;
    }

    @JsonIgnore
    @SuppressWarnings("checkstyle:BooleanExpressionComplexity")
    public boolean isEmpty() {
        return classification == null
                && CollectionUtils.isEmpty(capabilities)
                && availableAfter == null
                && dedicatedReceiver == null
                && status == null
                && providerId == null;
    }

    @JsonIgnore
    public boolean hasStatus() {
        return this.status != null;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("classification", classification)
                .append("capabilities", capabilities)
                .append("availableAfter", availableAfter)
                .append("dedicatedReceiver", dedicatedReceiver)
                .append("status", status)
                .append("providerId", providerId)
                .toString();
    }
}

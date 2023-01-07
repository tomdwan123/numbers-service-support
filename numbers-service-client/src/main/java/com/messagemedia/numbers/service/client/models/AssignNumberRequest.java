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
import com.messagemedia.numbers.service.client.models.validator.ValidLabel;
import com.messagemedia.numbers.service.client.models.validator.ValidMetadata;
import com.messagemedia.numbers.service.client.models.validator.ValidURL;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

public final class AssignNumberRequest {

    @NotBlank
    private final String vendorId;

    @NotBlank
    private final String accountId;

    @ValidURL(protocols = {"http", "https"})
    private final String callbackUrl;

    @ValidMetadata
    private final Map<String, String> metadata;

    @ValidLabel
    private String label;

    @JsonCreator
    public AssignNumberRequest(@JsonProperty("vendorId") String vendorId,
                               @JsonProperty("accountId") String accountId,
                               @JsonProperty("callbackUrl") String callbackUrl,
                               @JsonProperty("metadata") Map<String, String> metadata,
                               @JsonProperty("label") String label) {
        this.vendorId = vendorId;
        this.accountId = accountId;
        this.callbackUrl = callbackUrl;
        this.metadata = metadata;
        this.label = label;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("vendorId", vendorId)
                .append("accountId", accountId)
                .append("callbackUrl", callbackUrl)
                .append("metadata", metadata)
                .append("label", label)
                .toString();
    }
}

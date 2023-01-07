/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.messagemedia.framework.jackson.core.valuewithnull.ValueWithNull;
import com.messagemedia.numbers.service.client.models.validator.ValidLabel;
import com.messagemedia.numbers.service.client.models.validator.ValidMetadata;
import com.messagemedia.numbers.service.client.models.validator.ValidURL;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class UpdateAssignmentRequest {

    private final ValueWithNull<String> callbackUrl;

    private final ValueWithNull<Map<String, String>> metadata;

    private final ValueWithNull<String> label;

    public UpdateAssignmentRequest(@JsonProperty("callbackUrl") ValueWithNull<String> callbackUrl,
            @JsonProperty("metadata") ValueWithNull<Map<String, String>> metadata,
            @JsonProperty("label") ValueWithNull<String> label) {
        this.callbackUrl = callbackUrl;
        this.metadata = metadata;
        this.label = label;
    }

    public ValueWithNull<String> getCallbackUrl() {
        return callbackUrl;
    }

    @JsonIgnore
    @ValidURL(protocols = {"http", "https"})
    public String getCallbackUrlValue() {
        if (callbackUrl == null) {
            return null;
        }
        return callbackUrl.get();
    }

    public ValueWithNull<Map<String, String>> getMetadata() {
        return metadata;
    }

    @JsonIgnore
    @ValidMetadata
    public Map<String, String> getMetadataValue() {
        if (metadata == null) {
            return null;
        }
        return metadata.get();
    }

    public ValueWithNull<String> getLabel() {
        return label;
    }

    @JsonIgnore
    @ValidLabel
    public String getLabelValue() {
        if (label == null) {
            return null;
        }
        return label.get();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return null == callbackUrl
                && null == metadata
                && null == label;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("callbackUrl", callbackUrl)
                .append("metadata", metadata)
                .append("label", label)
                .toString();
    }
}

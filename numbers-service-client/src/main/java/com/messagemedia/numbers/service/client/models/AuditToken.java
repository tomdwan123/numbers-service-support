/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.messagemedia.numbers.service.client.models.support.AuditTokenDeserializer;
import com.messagemedia.numbers.service.client.models.support.AuditTokenSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(using = AuditTokenDeserializer.class)
@JsonSerialize(using = AuditTokenSerializer.class)
public class AuditToken {

    public static final String TOKEN_DELIMITER = ":";

    private final UUID tokenId;
    private final Integer revNumber;

    public AuditToken(UUID tokenId, Integer revNumber) {
        this.tokenId = tokenId;
        this.revNumber = revNumber;
    }

    public String toJsonString() {
        return tokenId + TOKEN_DELIMITER + revNumber;
    }

    public static AuditToken fromString(String value) {
        Objects.requireNonNull(value);
        String[] parts = value.split(TOKEN_DELIMITER);
        if (parts.length != 2) {
            throw new IllegalArgumentException("must be in the form <assignmentId>:<revisionNumber>");
        }
        return new AuditToken(UUID.fromString(parts[0]), Integer.valueOf(parts[1]));
    }

    public UUID getTokenId() {
        return tokenId;
    }

    public Integer getRevNumber() {
        return revNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tokenId", tokenId)
                .append("revNumber", revNumber)
                .toString();
    }
}

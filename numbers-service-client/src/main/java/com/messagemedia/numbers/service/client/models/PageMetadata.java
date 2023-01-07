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
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class PageMetadata {
    private final int pageSize;
    private final UUID token;

    @JsonCreator
    public PageMetadata(@JsonProperty("pageSize") int pageSize,
                        @JsonProperty("token") UUID token) {
        this.pageSize = pageSize;
        this.token = token;
    }

    public int getPageSize() {
        return pageSize;
    }

    public UUID getToken() {
        return token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pageSize", pageSize)
                .append("token", token)
                .toString();
    }
}

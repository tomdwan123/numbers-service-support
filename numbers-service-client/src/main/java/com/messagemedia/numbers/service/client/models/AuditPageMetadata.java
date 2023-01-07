/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AuditPageMetadata {

    private final int pageSize;
    private final AuditToken token;

    public AuditPageMetadata(int pageSize, AuditToken token) {
        this.pageSize = pageSize;
        this.token = token;
    }

    public int getPageSize() {
        return pageSize;
    }

    public AuditToken getToken() {
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

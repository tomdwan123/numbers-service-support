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

import java.util.List;

public class AssignmentAuditListResponse {

    private final List<AssignmentDto> assignments;
    private final AuditPageMetadata pageMetadata;

    @JsonCreator
    public AssignmentAuditListResponse(@JsonProperty("assignments") List<AssignmentDto> assignments,
                                       @JsonProperty("pageMetadata") AuditPageMetadata pageMetadata) {
        this.assignments = assignments;
        this.pageMetadata = pageMetadata;
    }

    public List<AssignmentDto> getAssignments() {
        return assignments;
    }

    public AuditPageMetadata getPageMetadata() {
        return pageMetadata;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("assignments", assignments)
                .append("pageMetadata", pageMetadata)
                .toString();
    }
}

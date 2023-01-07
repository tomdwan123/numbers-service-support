/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
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

public class NumberAssignmentListResponse {

    private final List<NumberAssignmentDto> numberAssignments;
    private final PageMetadata pageMetadata;

    @JsonCreator
    public NumberAssignmentListResponse(@JsonProperty("numberAssignments") List<NumberAssignmentDto> numberAssignments,
                                        @JsonProperty("pageMetadata") PageMetadata pageMetadata) {
        this.numberAssignments = numberAssignments;
        this.pageMetadata = pageMetadata;
    }

    public List<NumberAssignmentDto> getNumberAssignments() {
        return numberAssignments;
    }

    public PageMetadata getPageMetadata() {
        return pageMetadata;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("numberAssignments", numberAssignments)
                .append("pageMetadata", pageMetadata)
                .toString();
    }
}

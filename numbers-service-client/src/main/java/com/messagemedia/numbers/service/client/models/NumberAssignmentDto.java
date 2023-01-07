/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class NumberAssignmentDto {

    @JsonProperty("number")
    private NumberDto number;

    @JsonProperty("assignment")
    private AssignmentDto assignment;

    public NumberAssignmentDto() {
    }

    public NumberAssignmentDto(NumberDto number, AssignmentDto assignment) {
        this.number = number;
        this.assignment = assignment;
    }

    public NumberDto getNumber() {
        return number;
    }

    public void setNumber(NumberDto number) {
        this.number = number;
    }

    public AssignmentDto getAssignment() {
        return assignment;
    }

    public void setAssignment(AssignmentDto assignment) {
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("assignment", assignment)
                .toString();
    }
}

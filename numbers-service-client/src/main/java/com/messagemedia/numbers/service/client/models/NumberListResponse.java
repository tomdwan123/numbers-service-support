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

public class NumberListResponse {
    private final List<NumberDto> numbers;
    private final PageMetadata pageMetadata;

    @JsonCreator
    public NumberListResponse(@JsonProperty("numbers") List<NumberDto> numbers,
                              @JsonProperty("pageMetadata") PageMetadata pageMetadata) {
        this.numbers = numbers;
        this.pageMetadata = pageMetadata;
    }

    public List<NumberDto> getNumbers() {
        return numbers;
    }

    public PageMetadata getPageMetadata() {
        return pageMetadata;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("numbers", numbers)
                .append("pageMetadata", pageMetadata)
                .toString();
    }
}

/*
 * Copyright (c) Message4U Pty Ltd 2014-2020
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.messagemedia.numbers.service.client.models.validator.ValidCountryCode;
import com.messagemedia.numbers.service.client.models.validator.ValidRegularExpression;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

public abstract class BaseNumberSearchRequest {
    public static final int DEFAULT_PAGE_SIZE = 50;

    @Min(value = 0)
    @Max(value = 100)
    private int pageSize = DEFAULT_PAGE_SIZE;

    private UUID token;

    @ValidCountryCode(ignoreCase = true)
    private String country;
    private ServiceType[] serviceTypes;
    private Boolean exactServiceTypes;

    @ValidRegularExpression
    private String matching;
    private String[] matchings;
    private Classification classification;
    private Status status;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ServiceType[] getServiceTypes() {
        return ArrayUtils.clone(serviceTypes);
    }

    public void setServiceTypes(ServiceType[] serviceTypes) {
        this.serviceTypes = ArrayUtils.clone(serviceTypes);
    }

    public Boolean getExactServiceTypes() {
        return exactServiceTypes;
    }

    public void setExactServiceTypes(Boolean exactServiceTypes) {
        this.exactServiceTypes = exactServiceTypes;
    }

    public String getMatching() {
        return matching;
    }

    public void setMatching(String matching) {
        this.matching = matching;
    }

    public String[] getMatchings() {
        return matchings;
    }

    public void setMatchings(String[] matchings) {
        this.matchings = matchings;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pageSize", pageSize)
                .append("token", token)
                .append("country", country)
                .append("serviceTypes", serviceTypes)
                .append("exactServiceTypes", exactServiceTypes)
                .append("matching", matching)
                .append("matchings", matchings)
                .append("status", status)
                .toString();
    }
}

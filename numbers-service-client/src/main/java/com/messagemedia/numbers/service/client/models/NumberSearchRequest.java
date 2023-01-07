/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

public class NumberSearchRequest extends BaseNumberSearchRequest {

    private Boolean assigned;
    private OffsetDateTime availableBy;

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public OffsetDateTime getAvailableBy() {
        return availableBy;
    }

    public void setAvailableBy(OffsetDateTime availableBy) {
        this.availableBy = availableBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("assigned", assigned)
                .append("availableBy", availableBy)
                .toString();
    }

    public static final class NumberSearchRequestBuilder {
        private Integer pageSize;
        private UUID token;
        private String country;
        private ServiceType[] serviceTypes;
        private String matching;
        private Classification classification;
        private Boolean assigned;
        private OffsetDateTime availableBy;
        private Boolean exactServiceTypes;

        private NumberSearchRequestBuilder() {
        }

        public static NumberSearchRequestBuilder aNumberSearchRequestBuilder() {
            return new NumberSearchRequestBuilder();
        }

        public NumberSearchRequestBuilder withPageSize(Integer aPageSize) {
            this.pageSize = aPageSize;
            return this;
        }

        public NumberSearchRequestBuilder withToken(UUID aToken) {
            this.token = aToken;
            return this;
        }

        public NumberSearchRequestBuilder withCountry(String aCountry) {
            this.country = aCountry;
            return this;
        }

        public NumberSearchRequestBuilder withServiceTypes(ServiceType[] aServiceTypes) {
            this.serviceTypes = ArrayUtils.clone(aServiceTypes);
            return this;
        }

        public NumberSearchRequestBuilder withExactServiceTypes(Boolean aExactServiceTypes) {
            this.exactServiceTypes = aExactServiceTypes;
            return this;
        }

        public NumberSearchRequestBuilder withMatching(String aMatching) {
            this.matching = aMatching;
            return this;
        }

        public NumberSearchRequestBuilder withAssigned(Boolean anAssigned) {
            this.assigned = anAssigned;
            return this;
        }

        public NumberSearchRequestBuilder withClassification(Classification aClassification) {
            this.classification = aClassification;
            return this;
        }

        public NumberSearchRequestBuilder withAvailableBy(OffsetDateTime anAvailableBy) {
            this.availableBy = anAvailableBy;
            return this;
        }

        public NumberSearchRequest build() {
             NumberSearchRequest request = new NumberSearchRequest();
             if (pageSize != null) {
                 request.setPageSize(pageSize);
             }
             request.setCountry(country);
             request.setAssigned(assigned);
             request.setMatching(matching);
             request.setServiceTypes(serviceTypes);
             request.setExactServiceTypes(exactServiceTypes);
             request.setToken(token);
             request.setClassification(classification);
             request.setAvailableBy(availableBy);
             return request;
        }
    }
}

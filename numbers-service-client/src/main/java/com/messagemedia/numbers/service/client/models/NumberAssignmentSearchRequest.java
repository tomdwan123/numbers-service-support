/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.messagemedia.numbers.service.client.models.validator.ValidRegularExpression;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

public class NumberAssignmentSearchRequest extends BaseNumberSearchRequest {

    @NotBlank
    private String vendorId;

    @NotBlank
    private String accountId;

    @ValidRegularExpression
    private String label;

    private String[] accounts;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getAccounts() {
        return accounts;
    }

    public void setAccounts(String[] accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("vendorId", vendorId)
                .append("accountId", accountId)
                .append("label", label)
                .append("accounts", accounts)
                .toString();
    }

    public static final class NumberAssignmentSearchRequestBuilder {
        private Integer pageSize;
        private UUID token;
        private String vendorId;
        private String accountId;
        private String country;
        private ServiceType[] serviceTypes;
        private String matching;
        private String[] matchings;
        private Classification classification;
        private String label;
        private Status status;
        private String[] accounts;

        private NumberAssignmentSearchRequestBuilder() {
        }

        public static NumberAssignmentSearchRequestBuilder aNumberAssignmentSearchRequestBuilder() {
            return new NumberAssignmentSearchRequestBuilder();
        }

        public NumberAssignmentSearchRequestBuilder withPageSize(Integer aPageSize) {
            this.pageSize = aPageSize;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withToken(UUID aToken) {
            this.token = aToken;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withVendorId(String aVendorId) {
            this.vendorId = aVendorId;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withAccountId(String anAccountId) {
            this.accountId = anAccountId;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withCountry(String aCountry) {
            this.country = aCountry;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withServiceTypes(ServiceType[] aServiceTypes) {
            this.serviceTypes = ArrayUtils.clone(aServiceTypes);
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withMatching(String aMatching) {
            this.matching = aMatching;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withMatchings(String[] aMatchings) {
            this.matchings = aMatchings;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withClassification(Classification aClassification) {
            this.classification = aClassification;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withLabel(String aLabel) {
            this.label = aLabel;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withStatus(Status aStatus) {
            this.status = aStatus;
            return this;
        }

        public NumberAssignmentSearchRequestBuilder withAccounts(String[] aAccounts) {
            this.accounts = aAccounts;
            return this;
        }

        public NumberAssignmentSearchRequest build() {
            NumberAssignmentSearchRequest request = new NumberAssignmentSearchRequest();
            if (pageSize != null) {
                request.setPageSize(pageSize);
            }
            request.setToken(token);
            request.setVendorId(vendorId);
            request.setAccountId(accountId);
            request.setClassification(classification);
            request.setCountry(country);
            request.setLabel(label);
            request.setMatching(matching);
            request.setMatchings(matchings);
            request.setServiceTypes(serviceTypes);
            request.setStatus(status);
            request.setAccounts(accounts);
            return request;
        }
    }
}

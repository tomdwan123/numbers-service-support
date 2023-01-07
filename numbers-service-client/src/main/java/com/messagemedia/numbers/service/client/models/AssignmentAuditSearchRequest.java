/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.messagemedia.domainmodels.accounts.VendorAccountId;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.OffsetDateTime;
import java.util.UUID;

public class AssignmentAuditSearchRequest {

    private static final int DEFAULT_PAGE_SIZE = 50;

    private UUID id;
    private UUID numberId;
    private OffsetDateTime createdBefore;
    private OffsetDateTime createdAfter;
    private OffsetDateTime deletedBefore;
    private OffsetDateTime deletedAfter;

    @Min(value = 0)
    @Max(value = 100)
    private int pageSize = DEFAULT_PAGE_SIZE;

    private VendorAccountId vendorAccountId;
    private AuditToken token;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getNumberId() {
        return numberId;
    }

    public void setNumberId(UUID numberId) {
        this.numberId = numberId;
    }

    public VendorAccountId getVendorAccountId() {
        return vendorAccountId;
    }

    public void setVendorAccountId(VendorAccountId vendorAccountId) {
        this.vendorAccountId = vendorAccountId;
    }

    public OffsetDateTime getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(OffsetDateTime createdBefore) {
        this.createdBefore = createdBefore;
    }

    public OffsetDateTime getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(OffsetDateTime createdAfter) {
        this.createdAfter = createdAfter;
    }

    public OffsetDateTime getDeletedBefore() {
        return deletedBefore;
    }

    public void setDeletedBefore(OffsetDateTime deletedBefore) {
        this.deletedBefore = deletedBefore;
    }

    public OffsetDateTime getDeletedAfter() {
        return deletedAfter;
    }

    public void setDeletedAfter(OffsetDateTime deletedAfter) {
        this.deletedAfter = deletedAfter;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public AuditToken getToken() {
        return token;
    }

    public void setToken(AuditToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("numberId", numberId)
                .append("vendorAccountId", vendorAccountId)
                .append("createdBefore", createdBefore)
                .append("createdAfter", createdAfter)
                .append("deletedBefore", deletedBefore)
                .append("deletedAfter", deletedAfter)
                .append("pageSize", pageSize)
                .append("token", token)
                .toString();
    }

    public static final class AssignmentAuditSearchRequestBuilder {
        private UUID id;
        private UUID numberId;
        private VendorAccountId vendorAccountId;
        private OffsetDateTime createdBefore;
        private OffsetDateTime createdAfter;
        private OffsetDateTime deletedBefore;
        private OffsetDateTime deletedAfter;
        private int pageSize;
        private AuditToken token;

        private AssignmentAuditSearchRequestBuilder() {
        }

        public static AssignmentAuditSearchRequestBuilder anAssignmentAuditSearchRequest() {
            return new AssignmentAuditSearchRequestBuilder();
        }

        public AssignmentAuditSearchRequestBuilder withId(UUID anId) {
            this.id = anId;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withNumberId(UUID aNumberId) {
            this.numberId = aNumberId;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withVendorAccountId(VendorAccountId aVendorAccountId) {
            this.vendorAccountId = aVendorAccountId;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withCreatedBefore(OffsetDateTime createdBeforeDate) {
            this.createdBefore = createdBeforeDate;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withCreatedAfter(OffsetDateTime createdAfterDate) {
            this.createdAfter = createdAfterDate;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withDeletedBefore(OffsetDateTime deletedBeforeDate) {
            this.deletedBefore = deletedBeforeDate;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withDeletedAfter(OffsetDateTime deletedAfterDate) {
            this.deletedAfter = deletedAfterDate;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withPageSize(int aPageSize) {
            this.pageSize = aPageSize;
            return this;
        }

        public AssignmentAuditSearchRequestBuilder withToken(AuditToken aToken) {
            this.token = aToken;
            return this;
        }

        public AssignmentAuditSearchRequest build() {
            AssignmentAuditSearchRequest assignmentAuditSearchRequest = new AssignmentAuditSearchRequest();
            assignmentAuditSearchRequest.setId(id);
            assignmentAuditSearchRequest.setNumberId(numberId);
            assignmentAuditSearchRequest.setVendorAccountId(vendorAccountId);
            assignmentAuditSearchRequest.setCreatedBefore(createdBefore);
            assignmentAuditSearchRequest.setCreatedAfter(createdAfter);
            assignmentAuditSearchRequest.setDeletedBefore(deletedBefore);
            assignmentAuditSearchRequest.setDeletedAfter(deletedAfter);
            assignmentAuditSearchRequest.setPageSize(pageSize);
            assignmentAuditSearchRequest.setToken(token);
            return assignmentAuditSearchRequest;
        }
    }
}

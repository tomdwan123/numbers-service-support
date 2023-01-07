/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AssignmentDto {

    private UUID id;
    private UUID numberId;
    private String vendorId;
    private String accountId;
    private String callbackUrl;
    private Map<String, String> metadata;
    private String label;
    private OffsetDateTime created;
    private OffsetDateTime deleted;

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

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(OffsetDateTime deleted) {
        this.deleted = deleted;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssignmentDto that = (AssignmentDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("numberId", numberId)
                .append("vendorId", vendorId)
                .append("accountId", accountId)
                .append("callbackUrl", callbackUrl)
                .append("metadata", metadata)
                .append("label", label)
                .append("created", created)
                .append("deleted", deleted)
                .toString();
    }

    public static final class AssignmentDtoBuilder {
        private UUID id;
        private UUID numberId;
        private String vendorId;
        private String accountId;
        private String callbackUrl;
        private Map<String, String> metadata;
        private String label;
        private OffsetDateTime created;
        private OffsetDateTime deleted;

        public static AssignmentDtoBuilder anAssignmentDto() {
            return new AssignmentDtoBuilder();
        }

        public AssignmentDtoBuilder withId(UUID anId) {
            this.id = anId;
            return this;
        }

        public AssignmentDtoBuilder withNumberId(UUID aNumberId) {
            this.numberId = aNumberId;
            return this;
        }

        public AssignmentDtoBuilder withVendorId(String aVendorId) {
            this.vendorId = aVendorId;
            return this;
        }

        public AssignmentDtoBuilder withAccountId(String anAccountId) {
            this.accountId = anAccountId;
            return this;
        }

        public AssignmentDtoBuilder withCallbackUrl(String aCallbackUrl) {
            this.callbackUrl = aCallbackUrl;
            return this;
        }

        public AssignmentDtoBuilder withMetadata(Map<String, String> metadataMap) {
            this.metadata = metadataMap;
            return this;
        }

        public AssignmentDtoBuilder withLabel(String labelValue) {
            this.label = labelValue;
            return this;
        }

        public AssignmentDtoBuilder withCreated(OffsetDateTime createdDate) {
            this.created = createdDate;
            return this;
        }

        public AssignmentDtoBuilder withDeleted(OffsetDateTime deletedDate) {
            this.deleted = deletedDate;
            return this;
        }

        public AssignmentDto build() {
            AssignmentDto assignmentDto = new AssignmentDto();
            assignmentDto.setId(id);
            assignmentDto.setNumberId(numberId);
            assignmentDto.setVendorId(vendorId);
            assignmentDto.setAccountId(accountId);
            assignmentDto.setCallbackUrl(callbackUrl);
            assignmentDto.setMetadata(metadata);
            assignmentDto.setLabel(label);
            assignmentDto.setCreated(created);
            assignmentDto.setDeleted(deleted);
            return assignmentDto;
        }
    }
}

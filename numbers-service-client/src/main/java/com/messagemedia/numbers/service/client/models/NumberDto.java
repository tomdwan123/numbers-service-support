/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.OffsetDateTime;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public final class NumberDto {

    private UUID id;
    private UUID providerId;
    private String phoneNumber;
    private String country;
    private NumberType type;
    private Classification classification;
    private OffsetDateTime created;
    private OffsetDateTime updated;

    @JsonInclude(content = JsonInclude.Include.ALWAYS)
    private OffsetDateTime availableAfter;
    private Set<ServiceType> capabilities;
    private AssignmentDto assignedTo;
    private Boolean dedicatedReceiver;
    @JsonInclude(content = JsonInclude.Include.ALWAYS)
    private Status status;

    public NumberDto() {
    }

    public NumberDto(NumberDto other) {
        this.id = other.id;
        this.providerId = other.providerId;
        this.phoneNumber = other.phoneNumber;
        this.country = other.country;
        this.type = other.type;
        this.classification = other.classification;
        this.created = other.created;
        this.updated = other.updated;
        this.availableAfter = other.availableAfter;
        this.capabilities = EnumSet.copyOf(other.capabilities);
        this.assignedTo = other.assignedTo;
        this.dedicatedReceiver = other.dedicatedReceiver;
        this.status = other.status;
    }

    public UUID getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getProviderId() {
        return providerId;
    }

    public String getCountry() {
        return country;
    }

    public NumberType getType() {
        return type;
    }

    public Classification getClassification() {
        return classification;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public OffsetDateTime getUpdated() {
        return updated;
    }

    public Set<ServiceType> getCapabilities() {
        return capabilities;
    }

    public OffsetDateTime getAvailableAfter() {
        return availableAfter;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProviderId(UUID providerId) {
        this.providerId = providerId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setType(NumberType type) {
        this.type = type;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }

    public void setAvailableAfter(OffsetDateTime availableAfter) {
        this.availableAfter = availableAfter;
    }

    public void setCapabilities(Set<ServiceType> capabilities) {
        this.capabilities = capabilities;
    }

    public AssignmentDto getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(AssignmentDto assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Boolean getDedicatedReceiver() {
        return dedicatedReceiver;
    }

    public void setDedicatedReceiver(Boolean dedicatedReceiver) {
        this.dedicatedReceiver = dedicatedReceiver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumberDto numberDto = (NumberDto) o;
        return Objects.equals(id, numberDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("providerId", providerId)
                .append("phoneNumber", phoneNumber)
                .append("country", country)
                .append("type", type)
                .append("classification", classification)
                .append("created", created)
                .append("updated", updated)
                .append("capabilities", capabilities)
                .append("availableAfter", availableAfter)
                .append("assignedTo", assignedTo)
                .append("dedicatedReceiver", dedicatedReceiver)
                .append("status", status)
                .toString();
    }

    public static final class NumberDtoBuilder {
        private UUID id;
        private String phoneNumber;
        private UUID providerId;
        private String country;
        private NumberType type;
        private Classification classification;
        private OffsetDateTime created;
        private OffsetDateTime updated;
        private OffsetDateTime availableAfter;
        private Set<ServiceType> capabilities;
        private AssignmentDto assignedTo;
        private Boolean dedicatedReceiver;
        private Status status;

        private NumberDtoBuilder() {
        }

        public static NumberDtoBuilder aNumberDto() {
            return new NumberDtoBuilder();
        }

        public NumberDtoBuilder withId(UUID anId) {
            this.id = anId;
            return this;
        }

        public NumberDtoBuilder withPhoneNumber(String aPhoneNumber) {
            this.phoneNumber = aPhoneNumber;
            return this;
        }

        public NumberDtoBuilder withProviderId(UUID aProviderId) {
            this.providerId = aProviderId;
            return this;
        }

        public NumberDtoBuilder withCountry(String aCountry) {
            this.country = aCountry;
            return this;
        }

        public NumberDtoBuilder withType(NumberType aType) {
            this.type = aType;
            return this;
        }

        public NumberDtoBuilder withClassification(Classification aClassification) {
            this.classification = aClassification;
            return this;
        }

        public NumberDtoBuilder withCreated(OffsetDateTime aCreatedDate) {
            this.created = aCreatedDate;
            return this;
        }

        public NumberDtoBuilder withUpdated(OffsetDateTime aUpdatedDate) {
            this.updated = aUpdatedDate;
            return this;
        }

        public NumberDtoBuilder withAvailableAfter(OffsetDateTime availableAfterDate) {
            this.availableAfter = availableAfterDate;
            return this;
        }

        public NumberDtoBuilder withCapabilities(Set<ServiceType> capabilitySet) {
            this.capabilities = capabilitySet;
            return this;
        }

        public NumberDtoBuilder withAssignedTo(AssignmentDto assignment) {
            this.assignedTo = assignment;
            return this;
        }

        public NumberDtoBuilder withDedicatedReceiver(Boolean dedicatedReceiverFlag) {
            this.dedicatedReceiver = dedicatedReceiverFlag;
            return this;
        }

        public NumberDtoBuilder withStatus(Status numberStatus) {
            this.status = numberStatus;
            return this;
        }

        public NumberDto build() {
            NumberDto numberDto = new NumberDto();
            numberDto.id = this.id;
            numberDto.phoneNumber = this.phoneNumber;
            numberDto.created = this.created;
            numberDto.capabilities = this.capabilities;
            numberDto.country = this.country;
            numberDto.type = this.type;
            numberDto.classification = this.classification;
            numberDto.availableAfter = this.availableAfter;
            numberDto.updated = this.updated;
            numberDto.providerId = this.providerId;
            numberDto.assignedTo = this.assignedTo;
            numberDto.dedicatedReceiver = this.dedicatedReceiver;
            numberDto.status = this.status;
            return numberDto;
        }
    }
}

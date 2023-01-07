/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models;

import com.google.common.collect.ImmutableList;
import com.messagemedia.framework.jackson.core.valuewithnull.ValueWithNull;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static com.messagemedia.numbers.service.client.models.AssignmentDto.AssignmentDtoBuilder.anAssignmentDto;
import static com.messagemedia.numbers.service.client.models.NumberDto.NumberDtoBuilder.aNumberDto;
import static com.messagemedia.numbers.service.client.models.validator.MetadataValidator.MAX_KEY_LENGTH;
import static com.messagemedia.numbers.service.client.models.validator.MetadataValidator.MAX_SIZE;
import static com.messagemedia.numbers.service.client.models.validator.MetadataValidator.MAX_VALUE_LENGTH;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class TestData {

    private static final List<String> VALID_ISO_COUNTRY_CODES = ImmutableList.copyOf(Locale.getISOCountries());
    private static Random random = new Random();

    public static NumberDto.NumberDtoBuilder randomNumberDtoBuilder() {
        return aNumberDto()
                .withClassification(randomEnum(Classification.class))
                .withCountry(randomCountryCode())
                .withPhoneNumber(randomPhoneNumber())
                .withProviderId(UUID.randomUUID())
                .withType(randomEnum(NumberType.class))
                .withCreated(OffsetDateTime.now())
                .withUpdated(OffsetDateTime.now())
                .withAvailableAfter(OffsetDateTime.now())
                .withCapabilities(randomCapabilities())
                .withAssignedTo(randomAssignNumberDto());
    }

    public static NumberDto randomNumberDto() {
        return randomNumberDto(null);
    }

    public static NumberDto randomNumberDto(Boolean includeAssignment) {
        boolean isIncludedAssignment = null == includeAssignment ? random.nextBoolean() : includeAssignment;
        NumberDto numberDto = randomNumberDtoBuilder()
                .withId(UUID.randomUUID())
                .build();
        if (isIncludedAssignment) {
            AssignmentDto assignmentDto = randomAssignNumberDto();
            assignmentDto.setNumberId(numberDto.getId());
            numberDto.setAssignedTo(assignmentDto);
        }
        return numberDto;

    }

    public static NumberAssignmentDto randomNumberAssignmentDto() {
        NumberDto numberDto = randomNumberDto(TRUE);
        return new NumberAssignmentDto(numberDto, numberDto.getAssignedTo());
    }

    public static NumberAssignmentListResponse randomNumberAssignmentListResponse() {
        List<NumberAssignmentDto> numberAssignments = new ArrayList<>();
        int count = random.nextInt(30);
        for (int i = 0; i < count; i++) {
            numberAssignments.add(randomNumberAssignmentDto());
        }
        return new NumberAssignmentListResponse(numberAssignments, randomPageMetadataDto());
    }

    public static PageMetadata randomPageMetadataDto() {
        return new PageMetadata(random.nextInt(), UUID.randomUUID());
    }

    public static NumberListResponse randomNumberListResponse() {
        List<NumberDto> numbers = new ArrayList<>();
        int count = random.nextInt(30);
        for (int i = 0; i < count; i++) {
            numbers.add(randomNumberDto());
        }
        return new NumberListResponse(numbers, randomPageMetadataDto());
    }

    public static Set<ServiceType> randomCapabilities() {
        Set<ServiceType> serviceTypeSet = new HashSet<>();
        for (int i = 0; i < nextInt(1, ServiceType.values().length); i++) {
            serviceTypeSet.add(randomEnum(ServiceType.class));
        }
        return serviceTypeSet;
    }

    public static Boolean randomDedicatedReceiver() {
        int randomInt = random.nextInt(3);
        switch (randomInt) {
            case 0:
                return TRUE;
            case 1:
                return FALSE;
            case 2:
            default:
                return null;
        }
    }

    public static Classification randomClassification() {
        return randomEnum(Classification.class);
    }

    public static UUID randomProviderId() {
        return UUID.randomUUID();
    }

    public static Status randomStatus() {
        return randomEnum(Status.class);
    }

    private static String randomPhoneNumber() {
        return "+" + randomNumeric(11);
    }

    public static String randomCountryCode() {
        return VALID_ISO_COUNTRY_CODES.get(random.nextInt(VALID_ISO_COUNTRY_CODES.size()));
    }

    public static String randomValidLabel() {
        return randomAlphanumeric(new Random().nextInt(101));
    }

    public static String randomInvalidLabel() {
        return randomAlphanumeric(new Random().nextInt(1000) + 101);
    }

    public static RegisterNumberRequest randomRegisterNumberRequest() {
        return new RegisterNumberRequest(randomPhoneNumber(),
                UUID.randomUUID(),
                randomCountryCode(),
                randomEnum(NumberType.class),
                randomEnum(Classification.class),
                randomCapabilities(),
                randomDedicatedReceiver()
        );
    }

    public static UpdateNumberRequest randomUpdateNumberRequest() {
        return new UpdateNumberRequest(
                randomClassification(),
                randomCapabilities(),
                ValueWithNull.of(OffsetDateTime.now()),
                randomDedicatedReceiver(),
                randomStatus(),
                randomProviderId());
    }

    public static UpdateAssignmentRequest randomUpdateAssignmentRequest() {
        return new UpdateAssignmentRequest(
                ValueWithNull.of(randomCallbackUrl()),
                ValueWithNull.of(randomHashMap()),
                ValueWithNull.of(randomValidLabel()));
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        return clazz.getEnumConstants()[random.nextInt(clazz.getEnumConstants().length)];
    }

    public static AssignNumberRequest randomAssignNumberRequest() {
        return new AssignNumberRequest(randomAlphanumeric(20),
                randomAlphanumeric(20),
                randomAlphanumeric(200),
                randomHashMap(),
                randomValidLabel()
        );
    }

    public static AssignmentDto.AssignmentDtoBuilder randomAssignmentDtoBuilder() {
        return randomAssignmentDtoBuilderWithMinimumFields()
                .withCallbackUrl(randomAlphabetic(200))
                .withMetadata(randomHashMap())
                .withLabel(randomValidLabel());
    }

    public static AssignmentDto.AssignmentDtoBuilder randomAssignmentDtoBuilderWithMinimumFields() {
        return anAssignmentDto()
                .withAccountId(randomAlphabetic(20))
                .withVendorId(randomAlphabetic(20))
                .withCreated(OffsetDateTime.now())
                .withNumberId(UUID.randomUUID());
    }

    public static AssignmentDto randomAssignNumberDto() {
        return randomAssignmentDtoBuilder().withId(UUID.randomUUID()).build();
    }

    public static EventNotification randomEventNotification() {
        return new EventNotification(randomEnum(Event.class), randomNumberDto(), randomAssignNumberDto());
    }

    public static Map<String, String> randomHashMap() {
        return randomHashMap(random.nextInt(MAX_SIZE));
    }

    public static Map<String, String> randomHashMap(int size) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(randomAlphanumeric(MAX_KEY_LENGTH), randomAlphanumeric(MAX_VALUE_LENGTH));
        }
        return map;
    }

    public static Map<String, String> randomInvalidKeyMap() {
        Map<String, String> invalidKeyMap = randomHashMap(MAX_SIZE - 1);
        invalidKeyMap.put(randomAlphanumeric(MAX_KEY_LENGTH + 1), randomAlphanumeric(MAX_VALUE_LENGTH));
        return invalidKeyMap;
    }

    public static Map<String, String> randomInvalidValueMap() {
        Map<String, String> invalidValueMap = randomHashMap(MAX_SIZE - 1);
        invalidValueMap.put(randomAlphanumeric(MAX_KEY_LENGTH), randomAlphanumeric(MAX_VALUE_LENGTH + 1));
        return invalidValueMap;
    }

    public static Map<String, String> randomNullKeyMap() {
        Map<String, String> nullKeyMap = randomHashMap(MAX_SIZE - 1);
        nullKeyMap.put(null, randomAlphanumeric(MAX_VALUE_LENGTH));
        return nullKeyMap;
    }

    public static Map<String, String> randomEmptyKeyMap() {
        Map<String, String> emptyKeyMap = randomHashMap(MAX_SIZE - 1);
        emptyKeyMap.put(" ", randomAlphanumeric(MAX_VALUE_LENGTH));
        return emptyKeyMap;
    }

    public static String randomCallbackUrl() {
        String method = Arrays.asList("http", "https").get(random.nextInt(2));
        return String.format("%s://%s.%s", method, randomAlphanumeric(100), randomAlphanumeric(10));
    }

    public static AuditToken randomAuditToken() {
        return new AuditToken(UUID.randomUUID(), random.nextInt(1000));
    }

    public static AuditPageMetadata randomAuditPageMetadata() {
        return new AuditPageMetadata(random.nextInt(100), randomAuditToken());
    }

    public static NumberForwardDto randomNumberForwardDto() {
        return new NumberForwardDto(randomNumeric(11));
    }
}

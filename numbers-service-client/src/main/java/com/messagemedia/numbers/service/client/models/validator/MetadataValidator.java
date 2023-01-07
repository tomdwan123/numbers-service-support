/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * validate metadata when assigning an account to a number.
 * The metadata can be null or empty or maximum of 11 entries with no keys either null, empty
 * or longer than 100 characters, and no values longer than 256 characters.
 */
public class MetadataValidator implements ConstraintValidator<ValidMetadata, Map<String, String>> {

    //use public for further using
    public static final int MAX_SIZE = 11;
    public static final int MAX_KEY_LENGTH = 100;
    public static final int MAX_VALUE_LENGTH = 256;

    @Override
    public void initialize(ValidMetadata constraintAnnotation) {
    }

    @Override
    public boolean isValid(Map<String, String> metadata, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(metadata)) {
            return true;
        }

        if (metadata.size() > MAX_SIZE) {
            return false;
        }

        return !metadata.entrySet().stream()
                .filter(p -> StringUtils.isBlank(p.getKey())
                        || StringUtils.length(p.getKey()) > MAX_KEY_LENGTH
                        || StringUtils.length(p.getValue()) > MAX_VALUE_LENGTH)
                .findAny()
                .isPresent();
    }
}

/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LabelValidator implements ConstraintValidator<ValidLabel, String> {

    public static final int MAX_LENGTH = 100;

    @Override
    public void initialize(ValidLabel constraintAnnotation) {
    }

    @Override
    public boolean isValid(String label, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(label)) {
            return true;
        }

        return label.length() <= MAX_LENGTH;
    }
}

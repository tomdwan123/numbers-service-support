/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegularExpressionValidator implements ConstraintValidator<ValidRegularExpression, String> {
    @Override
    public void initialize(ValidRegularExpression constraintAnnotation) {
    }

    @Override
    public boolean isValid(String regex, ConstraintValidatorContext constraintValidatorContext) {
        if (regex == null) {
            return true;
        }
        // only escape the + sign if it's in the beginning.
        // + is a regex character but since we are dealing with phone numbers we have to treat it as a string literal if it is the first character
        String sanitisedValue = regex.trim();
        if (sanitisedValue.startsWith("+")) {
            sanitisedValue = sanitisedValue.replace("+", "");
        }
        try {
            Pattern.compile(sanitisedValue);
            return true;
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }
}

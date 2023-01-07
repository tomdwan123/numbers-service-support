/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;
import java.util.Set;

public class CountryCodeValidator implements ConstraintValidator<ValidCountryCode, String> {

    private static final Set<String> VALID_ISO_COUNTRY_CODES = ImmutableSet.copyOf(Locale.getISOCountries());
    private boolean ignoreCase;

    @Override
    public void initialize(ValidCountryCode constraintAnnotation) {
        this.ignoreCase = constraintAnnotation.ignoreCase();
    }

    @Override
    public boolean isValid(String countryCode, ConstraintValidatorContext constraintValidatorContext) {
        return (StringUtils.isEmpty(countryCode)) || VALID_ISO_COUNTRY_CODES.contains(ignoreCase ? countryCode.toUpperCase() : countryCode);
    }
}

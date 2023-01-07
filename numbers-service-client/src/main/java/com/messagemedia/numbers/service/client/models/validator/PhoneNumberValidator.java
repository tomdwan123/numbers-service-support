/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import com.messagemedia.domainmodels.telecommunication.AddressValidationResult;
import com.messagemedia.domainmodels.telecommunication.AddressValidator;
import com.messagemedia.numbers.service.client.models.RegisterNumberRequest;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * <pre>
 * Checks the phone number (combine number, type and country code) of register number request is valid.
 * This is using AddressValidator class of framework to validate a phone number.
 * Assumes using @NotNull and @NotBlank annotation on properties of RegisterNumberRequest
 *
 * Return true if
 *   - The register number request is null
 *   - One of phone number, type, country code properties is blank or null
 *   - Phone number is valid with country code and number type
 * Otherwise return false
 * </pre>
 */
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, RegisterNumberRequest> {

    private static final String INVALID_E164_FORMAT_MSG = "PhoneNumber {%s} with type {%s} must be in E.164 format";
    private static final String INVALID_COUNTRY_MSG = "PhoneNumber {%s} with country code {%s} is invalid";

    private static final AddressValidator ADDRESS_VALIDATOR = AddressValidator.getDefaultInstance();

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegisterNumberRequest numberRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (numberRequest == null || numberRequest.getType() == null
                || StringUtils.isBlank(numberRequest.getPhoneNumber())
                || StringUtils.isBlank(numberRequest.getCountry())) {
            //return true because these properties are already validated by @Notnull, @NotBlank annotations
            return true;
        }

        AddressValidationResult validPhoneNumber = isValidPhoneNumber(numberRequest);
        if (!validPhoneNumber.isValid()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(validPhoneNumber.getValidationErrors().toString())
                    .addPropertyNode("phoneNumber").addConstraintViolation();
            return false;
        }
        return true;
    }

    private AddressValidationResult isValidPhoneNumber(RegisterNumberRequest numberRequest) {
        switch (numberRequest.getType()) {
            case MOBILE:
            case TOLL_FREE:
            case LANDLINE:
                //Currently, AddressValidator doesn't support E.164 format, so need validate + first
                if (!numberRequest.getPhoneNumber().startsWith(AddressValidator.INTERNATIONAL_PREFIX)) {
                    String errorMsg = String.format(INVALID_E164_FORMAT_MSG, numberRequest.getPhoneNumber(), numberRequest.getType());
                    return new AddressValidationResult(Arrays.asList(errorMsg));
                }

                AddressValidationResult result = ADDRESS_VALIDATOR.validateInternational(numberRequest.getPhoneNumber());
                return validateCountry(numberRequest, result);

            case SHORT_CODE:
                return ADDRESS_VALIDATOR.validateShortcode(numberRequest.getPhoneNumber());

            default:
                throw new IllegalArgumentException("PhoneNumber validation is not implemented for type " + numberRequest.getType());
        }
    }

    private AddressValidationResult validateCountry(RegisterNumberRequest numberRequest, AddressValidationResult result) {
        if (result.isValid() && result.getCountryInformation().isPresent()) {
            AddressValidationResult.CountryInformation countryInformation = result.getCountryInformation().get();
            if (!numberRequest.getCountry().equals(countryInformation.getCountryCode())) {
                String errorMsg = String.format(INVALID_COUNTRY_MSG, numberRequest.getPhoneNumber(), numberRequest.getCountry());
                return new AddressValidationResult(Arrays.asList(errorMsg));
            }
        }
        return result;
    }
}

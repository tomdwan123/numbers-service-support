/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import com.messagemedia.numbers.service.client.models.BaseNumberSearchRequest;
import com.messagemedia.numbers.service.client.models.RegisterNumberRequest;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class CountryCodeValidatorTest {
    @DataProvider
    public static String[] validCountryCodes() {
        return new String[]{
                "au",
                "Au",
                "aU",
                "AU"
        };
    }

    @Test
    @UseDataProvider("validCountryCodes")
    public void testValidCountryIgnoreCase(String countryCode) throws Exception {
        Field callbackUrlField = BaseNumberSearchRequest.class.getDeclaredField("country");
        CountryCodeValidator countryCodeValidator = new CountryCodeValidator();
        countryCodeValidator.initialize(callbackUrlField.getAnnotation(ValidCountryCode.class));
        assertTrue(countryCodeValidator.isValid(countryCode, null));
    }

    @DataProvider
    public static String[] invalidCountryCodes() {
        return new String[]{
                "au",
                "a",
                "Aus1234"
        };
    }

    @Test
    @UseDataProvider("invalidCountryCodes")
    public void testInValidCountry(String countryCode) throws Exception {
        Field callbackUrlField = RegisterNumberRequest.class.getDeclaredField("country");
        CountryCodeValidator countryCodeValidator = new CountryCodeValidator();
        countryCodeValidator.initialize(callbackUrlField.getAnnotation(ValidCountryCode.class));
        assertFalse(countryCodeValidator.isValid(countryCode, null));
    }
}
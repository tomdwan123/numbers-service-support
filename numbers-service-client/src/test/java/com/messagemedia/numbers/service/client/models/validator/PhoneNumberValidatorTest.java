/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import com.messagemedia.numbers.service.client.models.Classification;
import com.messagemedia.numbers.service.client.models.NumberType;
import com.messagemedia.numbers.service.client.models.RegisterNumberRequest;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

import static com.messagemedia.numbers.service.client.models.TestData.randomCapabilities;
import static com.messagemedia.numbers.service.client.models.TestData.randomEnum;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(DataProviderRunner.class)
public class PhoneNumberValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder builder;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        doNothing().when(context).disableDefaultConstraintViolation();
        when(builder.addPropertyNode(anyString())).thenReturn(nodeBuilder);
        when(nodeBuilder.addConstraintViolation()).thenReturn(context);
    }

    @DataProvider
    public static Object[][] phoneNumbers() {
        return new Object[][]{
                {null, "AU", NumberType.MOBILE, true},
                {"+61491570157", "AU", NumberType.MOBILE, true},
                {"+841698750114", "VN", NumberType.LANDLINE, true},
                {"+61712341234", "AU", NumberType.LANDLINE, true},
                {"911", "US", NumberType.SHORT_CODE, true},
                {"113", "VN", NumberType.SHORT_CODE, true},

                {" 61491570156", "AU", NumberType.MOBILE, false},
                {"6149157 0156", "AU", NumberType.MOBILE, false},
                {"+298761234", "AU", NumberType.LANDLINE, false},
                {"+", "AU", NumberType.MOBILE, false},
                {"+12-3", "AU", NumberType.LANDLINE, false},
                {"61491570157", "AU", NumberType.LANDLINE, false},
                {"+6149157015a7", "AU", NumberType.LANDLINE, false},
                {"911a", "AU", NumberType.SHORT_CODE, false},

                {"+61" + RandomStringUtils.random(7, false, true), "AU", NumberType.TOLL_FREE, true},
                {"+61" + RandomStringUtils.random(13, false, true), "AU", NumberType.TOLL_FREE, true},
                {"+62" + RandomStringUtils.random(13, false, true), "AU", NumberType.TOLL_FREE, false},
                {"+61" + RandomStringUtils.random(6, false, true), "AU", NumberType.TOLL_FREE, false},
                {"+61" + RandomStringUtils.random(14, false, true), "AU", NumberType.TOLL_FREE, false},
                {"+61" + RandomStringUtils.random(13, true, true), "AU", NumberType.TOLL_FREE, false},
                {"123456789012", "AU", NumberType.TOLL_FREE, false},
                {"+6112345567@12", "AU", NumberType.TOLL_FREE, false},
                {"+6112-34556712", "AU", NumberType.TOLL_FREE, false},
                {"123a", "AU", NumberType.TOLL_FREE, false},
                {"123", "AU", NumberType.TOLL_FREE, false}
        };
    }

    @Test
    @UseDataProvider("phoneNumbers")
    public void shouldValidatePhoneNumber(String phoneNumber, String countryCode, NumberType numberType, boolean expected) throws Exception {
        RegisterNumberRequest numberRequest = new RegisterNumberRequest(phoneNumber,
                UUID.randomUUID(),
                countryCode,
                numberType,
                randomEnum(Classification.class),
                randomCapabilities(),
                null);
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        phoneNumberValidator.initialize(null);
        assertThat(phoneNumberValidator.isValid(numberRequest, context), is(equalTo(expected)));
    }
}
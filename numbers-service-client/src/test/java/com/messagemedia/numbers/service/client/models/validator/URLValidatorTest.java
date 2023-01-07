/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import com.messagemedia.numbers.service.client.models.AssignNumberRequest;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(DataProviderRunner.class)
public class URLValidatorTest {

    @DataProvider
    public static Object[][] urlData() {
        return new Object[][]{
                {null, true},
                {"http://xyz", true},
                {"https://abc", true},

                {"", false},
                {"  ", false},
                {"invalid", false},
                {"___invalid", false},
                {"ftp://abc.com", false},
        };
    }

    @Test
    @UseDataProvider("urlData")
    public void testURLValidatorWithCallbackFieldConfig(String url, boolean expected) throws Exception {
        Field callbackUrlField = AssignNumberRequest.class.getDeclaredField("callbackUrl");
        callbackUrlField.setAccessible(true);
        URLValidator urlValidator = new URLValidator();
        urlValidator.initialize(callbackUrlField.getAnnotation(ValidURL.class));
        assertThat(urlValidator.isValid(url, null), is(equalTo(expected)));
    }
}
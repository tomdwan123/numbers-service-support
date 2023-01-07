/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(DataProviderRunner.class)
public class RegularExpressionValidatorTest {

    @DataProvider
    public static Object[][] urlData() {
        return new Object[][]{
                {null, true},
                {".*FLOWER", true},
                {"^[a-z0-9_-]{3,16}$", true},
                {"^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", true},

                {"*foo", false},
                {"foo([", false},
                {"/foo)]", false},
                {"+61400000000", true},
                {" +61400000000", true}
        };
    }

    @Test
    @UseDataProvider("urlData")
    public void testURLValidatorWithCallbackFieldConfig(String regex, boolean expected) throws Exception {
        RegularExpressionValidator validator = new RegularExpressionValidator();
        validator.initialize(null);
        assertThat(validator.isValid(regex, null), is(equalTo(expected)));
    }
}
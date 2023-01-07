/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
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

import static com.messagemedia.numbers.service.client.models.TestData.randomInvalidLabel;
import static com.messagemedia.numbers.service.client.models.TestData.randomValidLabel;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(DataProviderRunner.class)
public class LabelValidatorTest {

    @DataProvider
    public static Object[][] labelData() {
        return new Object[][]{
                {null, true},
                {"", true},
                {"  ", true},
                {"null", true},
                {"a-good-label", true},
                {"MyFavouriteNumber", true},
                {randomValidLabel(), true},
                {randomValidLabel(), true},
                {randomAlphanumeric(100), true},
                {randomAlphanumeric(101), false},
                {"一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十", true},
                {"一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一", false},
                {randomInvalidLabel(), false},
        };
    }

    @Test
    @UseDataProvider("labelData")
    public void testLabelValidator(String label, boolean expected) throws Exception {
        Field labelField = AssignNumberRequest.class.getDeclaredField("label");
        labelField.setAccessible(true);
        LabelValidator labelValidator = new LabelValidator();
        labelValidator.initialize(labelField.getAnnotation(ValidLabel.class));
        assertThat(labelValidator.isValid(label, null), is(equalTo(expected)));
    }
}
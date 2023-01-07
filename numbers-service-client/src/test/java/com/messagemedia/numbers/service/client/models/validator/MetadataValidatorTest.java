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

import java.util.HashMap;
import java.util.Map;

import static com.messagemedia.numbers.service.client.models.TestData.*;
import static com.messagemedia.numbers.service.client.models.validator.MetadataValidator.MAX_SIZE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(DataProviderRunner.class)
public class MetadataValidatorTest {

    @DataProvider
    public static Object[][] metadata() {
        return new Object[][]{
                {null, true},
                {new HashMap<>(), true},
                {randomHashMap(), true},

                {randomHashMap(MAX_SIZE + 1), false},
                {randomInvalidKeyMap(), false},
                {randomInvalidValueMap(), false},
                {randomNullKeyMap(), false},
                {randomEmptyKeyMap(), false}
        };
    }

    @Test
    @UseDataProvider("metadata")
    public void testMetadataValidator(Map<String, String> metadata, boolean expected) throws Exception {
        MetadataValidator metadataValidator = new MetadataValidator();
        metadataValidator.initialize(null);
        assertThat(metadataValidator.isValid(metadata, null), is(equalTo(expected)));
    }
}
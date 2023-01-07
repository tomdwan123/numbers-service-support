/*
 * Copyright (c) Message4U Pty Ltd 2014-2018
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.models.validator;

import com.google.common.collect.Sets;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class URLValidator implements ConstraintValidator<ValidURL, String> {

    private Set<String> supportedProtocols = new HashSet<>();

    @Override
    public void initialize(ValidURL constraintAnnotation) {
        this.supportedProtocols = Sets.newHashSet(constraintAnnotation.protocols());
    }

    @Override
    public boolean isValid(String urlStr, ConstraintValidatorContext context) {
        // null is valid and must be checked with the notnull annotation
        return urlStr == null ? true : isValidURL(urlStr);
    }

    private boolean isValidURL(String urlStr) {
        URL url;
        try {
            url = new URL(urlStr);
            url.toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return supportedProtocols.isEmpty() || supportedProtocols.contains(url.getProtocol());
    }
}

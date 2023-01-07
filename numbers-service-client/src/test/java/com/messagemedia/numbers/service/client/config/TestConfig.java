/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client.config;

import com.messagemedia.framework.config.PlatformEnvironmentConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({
        PlatformEnvironmentConfig.class,
        NumbersServiceClientConfig.class
})
@PropertySource("classpath:test.properties")
public class TestConfig {
}

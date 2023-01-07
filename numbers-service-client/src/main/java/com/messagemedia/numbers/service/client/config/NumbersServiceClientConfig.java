/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */
package com.messagemedia.numbers.service.client.config;

import com.messagemedia.framework.service.RestTemplateFactory;
import com.messagemedia.framework.service.config.RestUtilConfig;
import com.messagemedia.numbers.service.client.NumbersServiceClient;
import com.messagemedia.numbers.service.client.NumbersServiceClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({RestUtilConfig.class})
public class NumbersServiceClientConfig {

    @Value("${service.numbers-service.endpoint}")
    private String endpoint;

    @Value("${service.numbers-service.connectionTimeout}")
    private int connectionTimeout;

    @Value("${service.numbers-service.readTimeout}")
    private int readTimeout;

    public NumbersServiceClientConfig() {
    }

    @Bean
    public NumbersServiceClient numbersServiceClient(RestTemplateFactory restTemplateFactory) {
        RestTemplate restTemplate = restTemplateFactory.createRestTemplateBuilder()
                .withConnectionTimeout(this.connectionTimeout)
                .withReadTimeout(this.readTimeout).build();
        return new NumbersServiceClientImpl(this.endpoint, restTemplate);
    }

}

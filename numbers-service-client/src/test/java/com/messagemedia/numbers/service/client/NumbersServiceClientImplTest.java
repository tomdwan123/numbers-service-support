/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.messagemedia.numbers.service.client;

import com.google.common.collect.ImmutableMap;
import com.messagemedia.framework.config.JsonConfig;
import com.messagemedia.framework.jackson.core.valuewithnull.ValueWithNull;
import com.messagemedia.framework.json.JsonFastMapper;
import com.messagemedia.numbers.service.client.exception.NumbersServiceBadRequestException;
import com.messagemedia.numbers.service.client.exception.NumbersServiceException;
import com.messagemedia.numbers.service.client.exception.NumbersServiceForbiddenException;
import com.messagemedia.numbers.service.client.exception.NumbersServiceNotFoundException;
import com.messagemedia.numbers.service.client.models.AssignNumberRequest;
import com.messagemedia.numbers.service.client.models.AssignmentDto;
import com.messagemedia.numbers.service.client.models.Classification;
import com.messagemedia.numbers.service.client.models.NumberAssignmentListResponse;
import com.messagemedia.numbers.service.client.models.NumberAssignmentSearchRequest;
import com.messagemedia.numbers.service.client.models.NumberDto;
import com.messagemedia.numbers.service.client.models.NumberForwardDto;
import com.messagemedia.numbers.service.client.models.NumberListResponse;
import com.messagemedia.numbers.service.client.models.NumberSearchRequest;
import com.messagemedia.numbers.service.client.models.ServiceType;
import com.messagemedia.numbers.service.client.models.UpdateAssignmentRequest;
import com.messagemedia.numbers.service.client.models.UpdateNumberRequest;
import com.messagemedia.numbers.service.client.models.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.messagemedia.framework.test.IntegrationTestUtilities.pathToString;
import static com.messagemedia.numbers.service.client.NumbersServiceClientImpl.ASSIGNMENTS_PATH;
import static com.messagemedia.numbers.service.client.NumbersServiceClientImpl.ASSIGNMENT_PATH;
import static com.messagemedia.numbers.service.client.NumbersServiceClientImpl.FORWARD_PATH;
import static com.messagemedia.numbers.service.client.NumbersServiceClientImpl.NUMBERS_SEARCH_PATH;
import static com.messagemedia.numbers.service.client.NumbersServiceClientImpl.NUMBER_PATH;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RunWith(MockitoJUnitRunner.class)
public class NumbersServiceClientImplTest {

    private static final String ENDPOINT = "http://localhost:8500";
    private static final JsonFastMapper MAPPER = new JsonConfig().fastMapper();
    private static final UUID NUMBER_ID = UUID.fromString("b9ee3fe8-2c20-47b1-96e9-c5d12d7ed985");

    @Mock
    private RestTemplate restTemplate;
    private NumbersServiceClientImpl client;

    @Before
    public void setup() {
        client = new NumbersServiceClientImpl(ENDPOINT, restTemplate);
    }

    @Test
    public void testGetNumbersWithNoFilters() throws Exception {
        String jsonString = pathToString("/responses/numbers-list-response.json");
        NumberListResponse expected = MAPPER.readFrom(jsonString, NumberListResponse.class);
        when(this.restTemplate.exchange(eq(ENDPOINT + NUMBERS_SEARCH_PATH), eq(GET), Matchers.any(HttpEntity.class),
                                        eq(NumberListResponse.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(OK).body(expected));

        NumberListResponse actual = client.getNumbers(new NumberSearchRequest());

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + NUMBERS_SEARCH_PATH), eq(GET), Matchers.any(HttpEntity.class),
                                            eq(NumberListResponse.class), Matchers.<Object>anyVararg());
    }

    @Test
    public void testGetNumbersWithFilters() throws Exception {
        String jsonString = pathToString("/responses/numbers-list-response.json");
        NumberListResponse expected = MAPPER.readFrom(jsonString, NumberListResponse.class);

        when(this.restTemplate.exchange(eq(ENDPOINT + NUMBERS_SEARCH_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberListResponse.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(OK).body(expected));

        NumberSearchRequest numberSearchRequest = NumberSearchRequest.NumberSearchRequestBuilder
                                                    .aNumberSearchRequestBuilder()
                .withAssigned(true)
                .withCountry("AU")
                .withMatching("^[a-z0-9_-]{3,16}$")
                .withClassification(Classification.BRONZE)
                .withAvailableBy(OffsetDateTime.now())
                .withPageSize(3)
                .withServiceTypes(new ServiceType[]{ServiceType.SMS})
                .withToken(UUID.fromString("b0747959-311b-41d4-8eba-989bb99e0325"))
                                                    .build();
        NumberListResponse actual = client.getNumbers(numberSearchRequest);

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + NUMBERS_SEARCH_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberListResponse.class), Matchers.<Object>anyVararg());
    }

    @Test
    public void testGetNumber() throws Exception {
        String jsonString = pathToString("/responses/number-response.json");
        NumberDto expected = MAPPER.readFrom(jsonString, NumberDto.class);

        when(this.restTemplate.exchange(eq(ENDPOINT + NUMBER_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberDto.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(OK).body(expected));

        NumberDto actual = client.getNumber(NUMBER_ID);

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + NUMBER_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberDto.class), Matchers.<Object>anyVararg());
    }

    @Test
    public void testUpdateNumber() throws Exception {
        String jsonString = pathToString("/responses/number-response.json");
        NumberDto expected = MAPPER.readFrom(jsonString, NumberDto.class);

        UpdateNumberRequest request = new UpdateNumberRequest(null, null, null, null, Status.PENDING, null);

        when(this.restTemplate.exchange(eq(ENDPOINT + NUMBER_PATH), eq(PATCH), Matchers.any(HttpEntity.class),
                eq(NumberDto.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(OK).body(expected));

        NumberDto actual = client.updateNumber(NUMBER_ID, request);

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + NUMBER_PATH), eq(PATCH), Matchers.any(HttpEntity.class),
                eq(NumberDto.class), Matchers.<Object>anyVararg());
    }

    @Test
    public void getNumberForward() throws Exception {
        String jsonString = pathToString("/responses/number-forward-response.json");
        NumberForwardDto expected = MAPPER.readFrom(jsonString, NumberForwardDto.class);

        when(this.restTemplate.exchange(eq(ENDPOINT + FORWARD_PATH), eq(GET), ArgumentMatchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), ArgumentMatchers.<Object>any()))
                .thenReturn(status(OK).body(expected));

        NumberForwardDto actual = client.getNumberForward(NUMBER_ID);

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + FORWARD_PATH), eq(GET), ArgumentMatchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), ArgumentMatchers.<Object>any());
    }

    @Test(expected = NumbersServiceNotFoundException.class)
    public void testGetNumberFailWithNotFoundException() throws Exception {
        when(this.restTemplate.exchange(eq(ENDPOINT + NUMBER_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new HttpClientErrorException(NOT_FOUND));
        client.getNumber(NUMBER_ID);
    }

    @Test(expected = NumbersServiceException.class)
    public void testGetNumberFailWithSocketTimeoutException() throws Exception {
        when(this.restTemplate.exchange(eq(ENDPOINT + NUMBER_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new RestClientException("SocketTimeout", new SocketTimeoutException()));
        client.getNumber(NUMBER_ID);
    }

    @Test(expected = NumbersServiceException.class)
    public void testGetNumberFailWithGenericRestClientException() throws Exception {
        when(this.restTemplate.exchange(eq(ENDPOINT + NUMBER_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new RestClientException("Some Exception", new Exception()));
        client.getNumber(NUMBER_ID);
    }

    @Test
    public void testGetNumberAssignments() throws Exception {
        String jsonString = pathToString("/responses/number-assignment-list-response.json");
        NumberAssignmentListResponse expected = MAPPER.readFrom(jsonString, NumberAssignmentListResponse.class);

        when(this.restTemplate.exchange(eq(ENDPOINT + ASSIGNMENTS_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberAssignmentListResponse.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(OK).body(expected));

        NumberAssignmentSearchRequest request = NumberAssignmentSearchRequest.NumberAssignmentSearchRequestBuilder
                                                    .aNumberAssignmentSearchRequestBuilder()
                                                    .withPageSize(3)
                                                    .withToken(UUID.fromString("b0747959-311b-41d4-8eba-989bb99e0325"))
                                                    .withVendorId("vendorIdTest0")
                                                    .withAccountId("accountIdTest0")
                                                    .withLabel("MyLabel.*")
                                                    .withCountry("AU")
                                                    .withMatching("^[a-z0-9_-]{3,16}$")
                                                    .withClassification(Classification.BRONZE)
                                                    .withServiceTypes(new ServiceType[]{ServiceType.SMS})
                                                    .withStatus(Status.UNVERIFIED)
                                                    .withAccounts(new String[]{"all"})
                                                    .withMatchings(new String[]{"+1800", "677"})
                                                    .build();

        NumberAssignmentListResponse actual = client.getAssignments(request);

        assertThat(actual, equalTo(expected));
        verify(this.restTemplate).exchange(eq(ENDPOINT + ASSIGNMENTS_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(NumberAssignmentListResponse.class), eq(request.getVendorId()), eq(request.getAccountId()), eq(request.getPageSize()),
                        eq(request.getToken()), eq(request.getCountry()), eq("SMS"), eq(request.getMatching()),
                        eq(request.getLabel()), eq(request.getClassification()), eq(request.getStatus()), eq("all"), eq("+1800,677"));
    }

    @Test(expected = NumbersServiceBadRequestException.class)
    public void testGetNumberAssignmentsFailWithBadRequestException() throws Exception {
        client.getAssignments(NumberAssignmentSearchRequest.NumberAssignmentSearchRequestBuilder.aNumberAssignmentSearchRequestBuilder().build());
    }

    @Test
    public void testGetAssignment() throws Exception {
        String jsonString = pathToString("/responses/assignment-response.json");
        AssignmentDto expected = MAPPER.readFrom(jsonString, AssignmentDto.class);

        when(this.restTemplate.exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(OK).body(expected));

        AssignmentDto actual = client.getAssignment(NUMBER_ID);

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(GET), Matchers.any(HttpEntity.class),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg());
    }

    @Test
    public void testCreateAssignment() throws Exception {
        String jsonString = pathToString("/responses/assignment-response.json");
        AssignmentDto expected = MAPPER.readFrom(jsonString, AssignmentDto.class);

        AssignNumberRequest request = new AssignNumberRequest("vendorIdTest0", "accountIdTest0",
                                                                "https://example.com", ImmutableMap.of("key1", "value1"),
                                                                "labelTestValue");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        when(this.restTemplate.exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(POST), eq(new HttpEntity<>(request, headers)),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(CREATED).body(expected));

        AssignmentDto actual = client.createAssignment(NUMBER_ID, request);

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(POST), eq(new HttpEntity<>(request, headers)),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg());
    }

    @Test(expected = NumbersServiceBadRequestException.class)
    public void testCreateAssignmentFailWithBadRequestException() throws Exception {
        AssignNumberRequest request = new AssignNumberRequest("vendorIdTest0", "accountIdTest0",
                "https://example.com", ImmutableMap.of("key1", "value1"),
                "labelValueTest");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        when(this.restTemplate.exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(POST), eq(new HttpEntity<>(request, headers)),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new HttpClientErrorException(BAD_REQUEST));
        client.createAssignment(NUMBER_ID, request);
    }

    @Test(expected = NumbersServiceException.class)
    public void testCreateAssignmentFailWithBadGatewayException() throws Exception {
        AssignNumberRequest request = new AssignNumberRequest("vendorIdTest0", "accountIdTest0",
                "https://example.com", ImmutableMap.of("key1", "value1"),
                "labelValueTest");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        when(this.restTemplate.exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(POST), eq(new HttpEntity<>(request, headers)),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_GATEWAY));
        client.createAssignment(NUMBER_ID, request);
    }

    @Test
    public void testUpdateAssignment() throws Exception {
        String jsonString = pathToString("/responses/assignment-response.json");
        AssignmentDto expected = MAPPER.readFrom(jsonString, AssignmentDto.class);

        UpdateAssignmentRequest request = new UpdateAssignmentRequest(ValueWithNull.of("https://example.com"),
                                                ValueWithNull.of(ImmutableMap.of("key1", "value1")),
                                                ValueWithNull.of("labelValueTest"));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        when(this.restTemplate.exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(PATCH), eq(new HttpEntity<>(request, headers)),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(OK).body(expected));

        AssignmentDto actual = client.updateAssignment(NUMBER_ID, request);

        assertThat(actual, is(equalTo(expected)));
        verify(this.restTemplate).exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(PATCH), eq(new HttpEntity<>(request, headers)),
                eq(AssignmentDto.class), Matchers.<Object>anyVararg());
    }

    @Test
    public void testDeleteAssignment() throws Exception {
        when(this.restTemplate.exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(DELETE), Matchers.any(HttpEntity.class),
                eq(Void.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(NO_CONTENT).build());
        client.deleteAssignment(NUMBER_ID);
        verify(this.restTemplate).exchange(eq(ENDPOINT + ASSIGNMENT_PATH), eq(DELETE), Matchers.any(HttpEntity.class),
                eq(Void.class), Matchers.<Object>anyVararg());

    }

    @Test
    public void testDeleteNumberForward() throws Exception {
        when(this.restTemplate.exchange(eq(ENDPOINT + FORWARD_PATH),
                    eq(DELETE),
                    Matchers.any(HttpEntity.class),
                    eq(Void.class),
                    Matchers.<Object>anyVararg()))
            .thenReturn(status(NO_CONTENT).build());
        client.deleteNumberForward(NUMBER_ID);
        verify(this.restTemplate).exchange(eq(ENDPOINT + FORWARD_PATH), eq(DELETE), Matchers.any(HttpEntity.class),
                eq(Void.class), Matchers.<Object>anyVararg());
    }

    @Test
    public void testCreateNumberConfig() throws Exception {
        String jsonString = pathToString("/responses/number-forward-response.json");
        NumberForwardDto expected = MAPPER.readFrom(jsonString, NumberForwardDto.class);

        NumberForwardDto numberForwardDto = new NumberForwardDto(expected.getDestination());
        when(this.restTemplate.exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg()))
                .thenReturn(status(CREATED).body(expected));
        client.createNumberForwardConfig(NUMBER_ID, numberForwardDto);
        verify(this.restTemplate).exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg());
    }

    @Test(expected = NumbersServiceBadRequestException.class)
    public void testCreateNumberConfigBadRequest() throws Exception {
        NumberForwardDto numberForwardDto = new NumberForwardDto("+123456789");

        when(this.restTemplate.exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new HttpClientErrorException(BAD_REQUEST));
        client.createNumberForwardConfig(NUMBER_ID, numberForwardDto);
        verify(this.restTemplate).exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg());
    }

    @Test(expected = NumbersServiceNotFoundException.class)
    public void testCreateNumberConfigNotFound() throws Exception {
        NumberForwardDto numberForwardDto = new NumberForwardDto("+123456789");

        when(this.restTemplate.exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new HttpClientErrorException(NOT_FOUND));
        client.createNumberForwardConfig(NUMBER_ID, numberForwardDto);
        verify(this.restTemplate).exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg());
    }

    @Test(expected = NumbersServiceForbiddenException.class)
    public void testCreateNumberConfigForbidden() throws Exception {
        NumberForwardDto numberForwardDto = new NumberForwardDto("+123456789");
        when(this.restTemplate.exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg()))
                .thenThrow(new HttpClientErrorException(FORBIDDEN));
        client.createNumberForwardConfig(NUMBER_ID, numberForwardDto);
        verify(this.restTemplate).exchange(eq(ENDPOINT + FORWARD_PATH), eq(POST), Matchers.any(HttpEntity.class),
                eq(NumberForwardDto.class), Matchers.<Object>anyVararg());
    }
}

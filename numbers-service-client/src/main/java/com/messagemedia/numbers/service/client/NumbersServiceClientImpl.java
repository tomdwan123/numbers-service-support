/*
 * Copyright (c) Message4U Pty Ltd 2014-2020
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */
package com.messagemedia.numbers.service.client;

import com.google.common.base.Joiner;
import com.messagemedia.framework.logging.Logger;
import com.messagemedia.framework.logging.LoggerFactory;
import com.messagemedia.numbers.service.client.exception.ErrorInformation;
import com.messagemedia.numbers.service.client.exception.NumbersServiceBadRequestException;
import com.messagemedia.numbers.service.client.exception.NumbersServiceException;
import com.messagemedia.numbers.service.client.exception.NumbersServiceForbiddenException;
import com.messagemedia.numbers.service.client.exception.NumbersServiceNotFoundException;
import com.messagemedia.numbers.service.client.exception.NumbersServiceTimeoutException;
import com.messagemedia.numbers.service.client.models.AssignNumberRequest;
import com.messagemedia.numbers.service.client.models.AssignmentDto;
import com.messagemedia.numbers.service.client.models.NumberAssignmentListResponse;
import com.messagemedia.numbers.service.client.models.NumberAssignmentSearchRequest;
import com.messagemedia.numbers.service.client.models.NumberDto;
import com.messagemedia.numbers.service.client.models.NumberForwardDto;
import com.messagemedia.numbers.service.client.models.NumberListResponse;
import com.messagemedia.numbers.service.client.models.NumberSearchRequest;
import com.messagemedia.numbers.service.client.models.UpdateAssignmentRequest;
import com.messagemedia.numbers.service.client.models.UpdateNumberRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;
import java.util.UUID;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;

public class NumbersServiceClientImpl implements NumbersServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumbersServiceClientImpl.class);

    protected static final String NUMBERS_PATH = "/v1/numbers";
    protected static final String NUMBERS_SEARCH_PATH = NUMBERS_PATH
                                                        + "?pageSize={pageSize}&token={token}&country={country}&serviceTypes={serviceTypes}"
                                                        + "&exactServiceTypes={exactServiceTypes}&matching={matching}&assigned={assigned}"
                                                        + "&classification={classification}&availableBy={availableBy}";
    protected static final String NUMBER_PATH = NUMBERS_PATH + "/{numberId}";
    protected static final String FORWARD_PATH = NUMBER_PATH + "/forward";
    protected static final String ASSIGNMENT_PATH = NUMBER_PATH + "/assignment";
    protected static final String ASSIGNMENTS_PATH = NUMBERS_PATH
                                                        + "/assignments?vendorId={vendorId}&accountId={accountId}&pageSize={pageSize}&token={token}"
                                                        + "&country={country}&serviceTypes={serviceTypes}&matching={matching}&label={label}"
                                                        + "&classification={classification}&status={status}&accounts={accounts}"
                                                        + "&matchings={matchings}";

    private static final HttpHeaders COMMON_HEADERS;
    private static final HttpEntity<Object> COMMON_HTTP_ENTITY;

    private final String endpoint;
    private final RestTemplate restTemplate;

    static {
        COMMON_HEADERS = new HttpHeaders();
        COMMON_HEADERS.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        COMMON_HTTP_ENTITY = new HttpEntity<>(COMMON_HEADERS);
    }

    public NumbersServiceClientImpl(String endpoint, RestTemplate restTemplate) {
        this.endpoint = endpoint;
        this.restTemplate = restTemplate;
    }

    @Override
    public NumberListResponse getNumbers(NumberSearchRequest request) throws NumbersServiceException {
        return exchange(NUMBERS_SEARCH_PATH, GET, COMMON_HTTP_ENTITY, NumberListResponse.class, "Failed to query numbers",
                request.getPageSize(),
                request.getToken(),
                request.getCountry(),
                null != request.getServiceTypes() ? Joiner.on(",").join(request.getServiceTypes()) : null,
                request.getExactServiceTypes(),
                request.getMatching(),
                request.getAssigned(),
                request.getClassification(),
                request.getAvailableBy()).getBody();
    }

    @Override
    public NumberDto getNumber(UUID numberId) throws NumbersServiceException {
        return exchange(NUMBER_PATH, GET, COMMON_HTTP_ENTITY, NumberDto.class,
                String.format("Failed to query number %s", numberId),
                numberId).getBody();
    }

    @Override
    public NumberDto updateNumber(UUID numberId, UpdateNumberRequest updateNumberRequest) throws NumbersServiceException {
        return exchange(NUMBER_PATH, PATCH, new HttpEntity<>(updateNumberRequest, COMMON_HEADERS),
                NumberDto.class, String.format("Failed to update number %s", numberId), numberId).getBody();
    }

    @Override
    public NumberForwardDto getNumberForward(UUID numberId) throws NumbersServiceException {
        return exchange(FORWARD_PATH, GET, COMMON_HTTP_ENTITY, NumberForwardDto.class,
                String.format("Failed to query number forward %s", numberId),
                numberId).getBody();
    }

    @Override
    public NumberAssignmentListResponse getAssignments(NumberAssignmentSearchRequest request) throws NumbersServiceException {
        if (!validVendorAccountId(request)) {
            throw new NumbersServiceBadRequestException("VendorId and AccountId are required");
        }
        return exchange(ASSIGNMENTS_PATH, GET, COMMON_HTTP_ENTITY, NumberAssignmentListResponse.class, "Failed to query assignments",
                request.getVendorId(),
                request.getAccountId(),
                request.getPageSize(),
                request.getToken(),
                request.getCountry(),
                null != request.getServiceTypes() ? Joiner.on(",").join(request.getServiceTypes()) : null,
                request.getMatching(),
                request.getLabel(),
                request.getClassification(),
                request.getStatus(),
                ArrayUtils.isNotEmpty(request.getAccounts()) ? Joiner.on(",").join(request.getAccounts()) : null,
                ArrayUtils.isNotEmpty(request.getMatchings()) ? Joiner.on(",").join(request.getMatchings()) : null
                ).getBody();
    }

    @Override
    public AssignmentDto getAssignment(UUID numberId) throws NumbersServiceException {
        return exchange(ASSIGNMENT_PATH, GET, COMMON_HTTP_ENTITY, AssignmentDto.class,
                String.format("Failed to query assignment for number %s", numberId),
                numberId).getBody();
    }

    @Override
    public AssignmentDto createAssignment(UUID numberId, AssignNumberRequest request) throws NumbersServiceException {
        return exchange(ASSIGNMENT_PATH, POST, new HttpEntity<>(request, COMMON_HEADERS), AssignmentDto.class,
                String.format("Failed to create assignment for number %s", numberId),
                numberId).getBody();
    }

    @Override
    public AssignmentDto updateAssignment(UUID numberId, UpdateAssignmentRequest request) throws NumbersServiceException {
        return exchange(ASSIGNMENT_PATH, PATCH, new HttpEntity<>(request, COMMON_HEADERS), AssignmentDto.class,
                String.format("Failed to update assignment for number %s", numberId),
                numberId).getBody();
    }

    @Override
    public void deleteAssignment(UUID numberId) throws NumbersServiceException {
        exchange(ASSIGNMENT_PATH, DELETE, COMMON_HTTP_ENTITY, Void.class,
                String.format("Failed to delete assignment for number %s", numberId),
                numberId);
    }

    @Override
    public void deleteNumberForward(UUID numberId) throws NumbersServiceException {
        exchange(FORWARD_PATH, DELETE, COMMON_HTTP_ENTITY, Void.class,
                String.format("Failed to delete number forward for number %s", numberId),
                numberId);
    }

    @Override
    public ResponseEntity<NumberForwardDto> createNumberForwardConfig(
            UUID numberId,
            NumberForwardDto request
    ) throws NumbersServiceException {
         return exchange(FORWARD_PATH, POST, new HttpEntity<>(request, COMMON_HEADERS), NumberForwardDto.class,
                String.format("Failed to create number forward config for number %s", numberId),
                numberId);
    }

    private <T> ResponseEntity<T> exchange(String url, HttpMethod httpMethod, HttpEntity data,
                                           Class<T> responseType, String errorMessage,
                                           Object... uriVariables) throws NumbersServiceException {
        try {
            return restTemplate.exchange(endpoint + url, httpMethod, data, responseType, uriVariables);
        } catch (HttpStatusCodeException scx) {
            throw handleStatusCodeException(scx, errorMessage);
        } catch (RestClientException e) {
            throw logAndConvertException(e, errorMessage);
        }
    }

    private NumbersServiceException handleStatusCodeException(HttpStatusCodeException exception, String errorMessage) {
        ErrorInformation error = new ErrorInformation(exception.getMessage(), exception.getResponseBodyAsString());
        if (exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            LOGGER.warnWithReason(errorMessage, "Resource not found");
            return new NumbersServiceNotFoundException(errorMessage, error);
        } else if (exception.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            LOGGER.warnWithReason(errorMessage, "Bad request");
            return new NumbersServiceBadRequestException(errorMessage, error);
        } else if (exception.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
            LOGGER.warnWithReason(errorMessage, "Forbidden");
            return new NumbersServiceForbiddenException(errorMessage, error);
        } else {
            LOGGER.warnWithReason(errorMessage, "Status code=" + exception.getStatusCode());
            return new NumbersServiceException(errorMessage + " (Status code=" + exception.getStatusCode() + ")", error, exception);
        }
    }

    private NumbersServiceException logAndConvertException(Exception exception, String eventDesc) {
        LOGGER.errorWithReason(eventDesc, exception.getMessage(), exception);
        if (exception.getCause() instanceof SocketTimeoutException) {
            return new NumbersServiceTimeoutException(eventDesc, exception);
        } else {
            return new NumbersServiceException(eventDesc, exception);
        }
    }

    private boolean validVendorAccountId(NumberAssignmentSearchRequest request) {
        return StringUtils.isNotBlank(request.getVendorId()) && StringUtils.isNotBlank(request.getAccountId());
    }
}

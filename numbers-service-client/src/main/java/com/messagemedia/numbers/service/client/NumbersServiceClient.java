/*
 * Copyright (c) Message4U Pty Ltd 2014-2019
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */
package com.messagemedia.numbers.service.client;

import com.messagemedia.numbers.service.client.exception.NumbersServiceException;
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
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface NumbersServiceClient {

    NumberListResponse getNumbers(NumberSearchRequest request) throws NumbersServiceException;

    NumberDto getNumber(UUID numberId) throws NumbersServiceException;

    NumberDto updateNumber(UUID numberId, UpdateNumberRequest updateNumberRequest) throws NumbersServiceException;

    NumberForwardDto getNumberForward(UUID numberId) throws NumbersServiceException;

    NumberAssignmentListResponse getAssignments(NumberAssignmentSearchRequest request) throws NumbersServiceException;

    AssignmentDto getAssignment(UUID numberId) throws NumbersServiceException;

    AssignmentDto createAssignment(UUID numberId, AssignNumberRequest request) throws NumbersServiceException;

    AssignmentDto updateAssignment(UUID numberId, UpdateAssignmentRequest request) throws NumbersServiceException;

    void deleteAssignment(UUID numberId) throws NumbersServiceException;

    void deleteNumberForward(UUID numberId) throws NumbersServiceException;

    ResponseEntity<NumberForwardDto> createNumberForwardConfig(UUID numberId, NumberForwardDto request) throws NumbersServiceException;
}

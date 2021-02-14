package com.acme.architecture.core.exception.handler;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;
import com.acme.architecture.core.response.CoreExceptionResponse;

public class ResonseEntityExceptionHandlerTest {

	private static final String MESSAGE = "Message exception";

	private ResponseEntityExceptionHandler customResponseExceptionHandler = new ResponseEntityExceptionHandler();

	private WebRequest webRequest;

	@BeforeEach
	public final void setUp() {

		webRequest = null;
	}

	@Test
	public void whenCallHttpClientErrorException_thenReturnAnyStatusResponse() {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ResponseEntity<Object> response = customResponseExceptionHandler
				.handleHttpClientErrorException(new HttpClientErrorException(status, MESSAGE), webRequest);

		validateResponse(response, status);
	}

	@Test
	public void whenCallAcmeInvokeRuntimeException_thenReturnAnyStatusResponse() {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ResponseEntity<Object> response = customResponseExceptionHandler
				.handleAcmeInvokeRuntimeException(new AcmeInvokeRuntimeException(MESSAGE, status, null), webRequest);

		validateResponse(response, status);
	}

	@Test
	public void whenCallHandleInternalWithNullPointerException_thenReturnInternalErrorResponse() {

		ResponseEntity<Object> response = customResponseExceptionHandler
				.handleInternal(new NullPointerException(MESSAGE), webRequest);

		validateResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	public void whenCallHandleInternalWithIllegalArgumentException_thenReturnInternalErrorResponse() {

		ResponseEntity<Object> response = customResponseExceptionHandler
				.handleInternal(new IllegalArgumentException(MESSAGE), webRequest);

		validateResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void validateResponse(ResponseEntity<Object> response, HttpStatus status) {

		assertTrue(response.getStatusCode().value() == status.value());
		CoreExceptionResponse coreExceptionResponse = (CoreExceptionResponse) response.getBody();
		assertNotNull(coreExceptionResponse.getMessage());
		assertNotNull(coreExceptionResponse.getErrors());
	}
}

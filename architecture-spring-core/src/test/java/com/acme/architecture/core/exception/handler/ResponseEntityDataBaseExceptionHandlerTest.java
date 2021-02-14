package com.acme.architecture.core.exception.handler;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.acme.architecture.core.exception.handler.ResponseEntityDataBaseExceptionHandler;
import com.acme.architecture.core.response.CoreExceptionResponse;

public class ResponseEntityDataBaseExceptionHandlerTest {

	private static final String MESSAGE = "Message exception";

	private ResponseEntityDataBaseExceptionHandler customResponseExceptionHandler = new ResponseEntityDataBaseExceptionHandler();

	private WebRequest webRequest;

	@Mock
	private ConstraintViolation<String> customConstraintViolation;

	private Class<Object> rootBeanClass;

	@Mock
	private SQLException customSQLExceptionParent;

	@Mock
	private SQLException customSQLExceptionLevel1;

	@BeforeEach
	public final void setUp() {
		MockitoAnnotations.initMocks(this);

		webRequest = null;
		rootBeanClass = Object.class;
	}

	@Test
	public void whenCallHandleConstraintViolationWithEmptyViolations_thenReturnBadRequestResponse() {
		final Set<ConstraintViolation<?>> validationErrors = new HashSet<>();
		ConstraintViolationException constraintViolation = new ConstraintViolationException(MESSAGE, validationErrors);

		ResponseEntity<Object> response = customResponseExceptionHandler.handleConstraintViolation(
				new ConstraintViolationException(constraintViolation.getConstraintViolations()), webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHandleConstraintViolation_thenReturnBadRequestResponse() {

		doReturn(rootBeanClass).when(customConstraintViolation).getRootBeanClass();
		when(customConstraintViolation.getMessageTemplate()).thenReturn("Error Template");
		when(customConstraintViolation.getMessage()).thenReturn("Error Message");
		when(customConstraintViolation.getPropertyPath()).thenReturn(null);

		final Set<ConstraintViolation<?>> validationErrors = new HashSet<>();
		validationErrors.add(customConstraintViolation);

		ConstraintViolationException constraintViolation = new ConstraintViolationException(MESSAGE, validationErrors);

		ResponseEntity<Object> response = customResponseExceptionHandler.handleConstraintViolation(
				new ConstraintViolationException(constraintViolation.getConstraintViolations()), webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHandleDataIntegrityViolationExceptionSQLException_thenReturnBadRequestResponse() {

		SQLException sqlException = new SQLException(MESSAGE);

		ResponseEntity<Object> response = customResponseExceptionHandler.handleDataIntegrityViolationException(
				new DataIntegrityViolationException(MESSAGE, sqlException), webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHandleDataIntegrityViolationExceptionSQLExceptionWithCause_thenReturnBadRequestResponse() {
		doReturn(customSQLExceptionLevel1).when(customSQLExceptionParent).getCause();

		ResponseEntity<Object> response = customResponseExceptionHandler.handleDataIntegrityViolationException(
				new DataIntegrityViolationException(MESSAGE, customSQLExceptionParent), webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHandleDataIntegrityViolationException_thenReturnBadRequestResponse() {

		ResponseEntity<Object> response = customResponseExceptionHandler.handleDataIntegrityViolationException(
				new DataIntegrityViolationException(MESSAGE, new Throwable(MESSAGE)), webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void whenCallHandleInternal_thenReturnInternalErrorResponse() {

		ResponseEntity<Object> response = customResponseExceptionHandler
				.handleInternal(new InvalidDataAccessResourceUsageException(MESSAGE), webRequest);

		validateResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void validateResponse(ResponseEntity<Object> response, HttpStatus status) {

		assertTrue(response.getStatusCode().value() == status.value());
		CoreExceptionResponse coreExceptionResponse = (CoreExceptionResponse) response.getBody();
		assertNotNull(coreExceptionResponse.getMessage());
		assertNotNull(coreExceptionResponse.getErrors());
	}
}


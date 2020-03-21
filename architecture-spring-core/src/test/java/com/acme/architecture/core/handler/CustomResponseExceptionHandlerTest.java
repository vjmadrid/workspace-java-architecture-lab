package com.acme.architecture.core.handler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;
import com.acme.architecture.core.handler.CustomResponseExceptionHandler;
import com.acme.architecture.core.response.CoreExceptionResponse;

public class CustomResponseExceptionHandlerTest {

	private static final String MESSAGE = "Message exception";

	private CustomResponseExceptionHandler customResponseExceptionHandler = new CustomResponseExceptionHandler();

	private WebRequest webRequest;

	@Mock
	private HttpHeaders headers;

	private HttpStatus status;

	private Object body;
	
	@Mock
	private ConstraintViolation<String> customConstraintViolation;
	
	@Mock
	private Path mockPath;
	
	private Class<Object> rootBeanClass;
	
	@Mock
	private SQLException customSQLExceptionParent;
	
	@Mock
	private SQLException customSQLExceptionLevel1;
	
	@Before
	public final void setUp() {
		MockitoAnnotations.initMocks(this);

		webRequest = null;
		status = null;
		body = null;
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
	public void whenCallHandleMethodArgumentNotValidWithFieldErrorsEmpty_thenReturnBadRequestResponse() {
		
		final BindingResult bindingResult = new BeanPropertyBindingResult("target", "objectName");
		bindingResult.addError(new ObjectError("objectName", "defaultMessage"));
		bindingResult.addError(new FieldError("exampleObject", "exampleField", "exampleValue"));

		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

		ResponseEntity<Object> response = customResponseExceptionHandler.handleMethodArgumentNotValid(ex, headers,
				status, webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void whenCallHandleMethodArgumentNotValid_thenReturnBadRequestResponse() {

		final BindingResult bindingResult = new BeanPropertyBindingResult("target", "objectName");
		bindingResult.addError(new ObjectError("objectName", "defaultMessage"));

		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

		ResponseEntity<Object> response = customResponseExceptionHandler.handleMethodArgumentNotValid(ex, headers,
				status, webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHhandleHttpMessageNotReadable_thenReturnBadRequestResponse() {

		@SuppressWarnings("deprecation")
		ResponseEntity<Object> response = customResponseExceptionHandler.handleHttpMessageNotReadable(
				new HttpMessageNotReadableException(MESSAGE, new Exception()), headers, status, webRequest);

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
	public void whenCallHandleInternal_thenReturnInternalErrorResponse() {

		ResponseEntity<Object> response = customResponseExceptionHandler
				.handleInternal(new NullPointerException(MESSAGE), webRequest);

		validateResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	public void whenCallHandleExceptionInternal_thenReturnInternalErrorResponse() {

		ResponseEntity<Object> response = customResponseExceptionHandler.handleExceptionInternal(new Exception(MESSAGE),
				body, headers, status, webRequest);

		validateResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void validateResponse(ResponseEntity<Object> response, HttpStatus status) {
		assertTrue(response.getStatusCode().value() == status.value());
		CoreExceptionResponse coreExceptionResponse = (CoreExceptionResponse) response.getBody();
		assertNotNull(coreExceptionResponse.getMessage());
		assertNotNull(coreExceptionResponse.getErrors());
	}
}

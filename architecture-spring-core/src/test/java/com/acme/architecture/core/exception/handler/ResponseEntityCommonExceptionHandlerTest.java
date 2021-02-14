package com.acme.architecture.core.exception.handler;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.acme.architecture.core.exception.handler.ResponseEntityCommonExceptionHandler;
import com.acme.architecture.core.response.CoreExceptionResponse;

public class ResponseEntityCommonExceptionHandlerTest {

	private static final String MESSAGE = "Message exception";

	private ResponseEntityCommonExceptionHandler responseEntityCommonExceptionHandler = new ResponseEntityCommonExceptionHandler();

	private WebRequest webRequest;

	@Mock
	private HttpHeaders headers;

	private HttpStatus status;

	private Object body;

	@BeforeEach
	public final void setUp() {
		MockitoAnnotations.initMocks(this);

		webRequest = null;
		status = null;
		body = null;
	}

	@Test
	public void whenCallHandleMethodArgumentNotValidWithFieldErrorsEmpty_thenReturnBadRequestResponse() {

		final BindingResult bindingResult = new BeanPropertyBindingResult("target", "objectName");
		bindingResult.addError(new ObjectError("objectName", "defaultMessage"));
		bindingResult.addError(new FieldError("exampleObject", "exampleField", "exampleValue"));

		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

		ResponseEntity<Object> response = responseEntityCommonExceptionHandler.handleMethodArgumentNotValid(ex, headers,
				status, webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHandleMethodArgumentNotValid_thenReturnBadRequestResponse() {

		final BindingResult bindingResult = new BeanPropertyBindingResult("target", "objectName");
		bindingResult.addError(new ObjectError("objectName", "defaultMessage"));

		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

		ResponseEntity<Object> response = responseEntityCommonExceptionHandler.handleMethodArgumentNotValid(ex, headers,
				status, webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHandleHttpMessageNotReadable_thenReturnBadRequestResponse() {

		@SuppressWarnings("deprecation")
		ResponseEntity<Object> response = responseEntityCommonExceptionHandler.handleHttpMessageNotReadable(
				new HttpMessageNotReadableException(MESSAGE, new Exception()), headers, status, webRequest);

		validateResponse(response, HttpStatus.BAD_REQUEST);
	}

	@Test
	public void whenCallHandleExceptionInternal_thenReturnInternalErrorResponse() {

		ResponseEntity<Object> response = responseEntityCommonExceptionHandler.handleExceptionInternal(new Exception(MESSAGE),
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

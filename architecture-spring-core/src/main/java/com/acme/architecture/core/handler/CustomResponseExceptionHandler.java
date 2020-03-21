package com.acme.architecture.core.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;
import com.acme.architecture.core.response.CoreExceptionResponse;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

	public CustomResponseExceptionHandler() {
		super();
	}

	// 400

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

		final String message = " Invalid request body ";
		List<String> errors = new ArrayList<>();
		
		if (!ex.getConstraintViolations().isEmpty()) {
			ex.getConstraintViolations().forEach(violation -> errors.add(violation.getRootBeanClass().getName() + " "
				+ violation.getPropertyPath() + ": " + violation.getMessage()));
		}
		
		return buildResponse(message, errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {

		final String message = " Invalid request ";
		List<String> errors = new ArrayList<>();

		if (ex.getCause().getCause() instanceof SQLException) {
			SQLException sqlException = (SQLException) ex.getCause().getCause();
			errors.addAll(Collections.singletonList(sqlException.getMessage()));
		} else {
			errors.addAll(Collections.singletonList(ex.getMessage()));
		}

		return buildResponse(message, errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ HttpClientErrorException.class })
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {

		final String message = " Client invoke exception ";
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());

		return buildResponse(message, errors, ex.getStatusCode());
	}

	@ExceptionHandler({ AcmeInvokeRuntimeException.class })
	public ResponseEntity<Object> handleAcmeInvokeRuntimeException(AcmeInvokeRuntimeException ex, WebRequest request) {

		final String message = " Client invoke exception ";
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());

		return buildResponse(message, errors, ex.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		final String message = " Http message not readable ";
		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(message, errors, HttpStatus.BAD_REQUEST, headers);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		final String message = " Invalid request body ";
		List<String> errors = new ArrayList<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.add("Field '" + error.getField() + "' " + error.getDefaultMessage()));

		ex.getBindingResult().getGlobalErrors()
				.forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

		return buildResponse(message, errors, HttpStatus.BAD_REQUEST, headers);
	}

	// 500

	@ExceptionHandler({ NullPointerException.class, InvalidDataAccessResourceUsageException.class,
			IllegalArgumentException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {

		final String message = " Unexpected error ";
		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(message, errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		final String message = " Unexpected error ";
		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(message, errors, HttpStatus.INTERNAL_SERVER_ERROR, headers);

	}

	private ResponseEntity<Object> buildResponse(String message, List<String> errors, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus).body(new CoreExceptionResponse(message, errors));
	}

	private ResponseEntity<Object> buildResponse(String message, List<String> errors, HttpStatus httpStatus,
			HttpHeaders headers) {
		return ResponseEntity.status(httpStatus).headers(headers).body(new CoreExceptionResponse(message, errors));
	}

}
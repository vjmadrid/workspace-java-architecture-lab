package com.acme.architecture.core.exception.handler;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseEntityDataBaseExceptionHandler extends ResponseEntityCommonExceptionHandler {

	public ResponseEntityDataBaseExceptionHandler() {
		super();
	}

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

		if ((ex.getCause() != null) && ex.getCause().getCause() instanceof SQLException) {
			SQLException sqlException = (SQLException) ex.getCause().getCause();
			errors.addAll(Collections.singletonList(sqlException.getMessage()));
		} else {
			errors.addAll(Collections.singletonList(ex.getMessage()));
		}

		return buildResponse(message, errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {

		final String message = " Unexpected error ";
		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(message, errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
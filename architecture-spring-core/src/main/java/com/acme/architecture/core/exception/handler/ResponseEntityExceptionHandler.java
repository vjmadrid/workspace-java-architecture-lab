package com.acme.architecture.core.exception.handler;


import java.util.Collections;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import com.acme.architecture.core.constant.ResponseEntityExceptionHandlerConstant;
import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseEntityExceptionHandler extends ResponseEntityCommonExceptionHandler {

	public ResponseEntityExceptionHandler() {
		super();
	}

	@ExceptionHandler({ HttpClientErrorException.class })
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(ResponseEntityExceptionHandlerConstant.CLIENT_ERROR_MESSAGE, errors, ex.getStatusCode());
	}

	@ExceptionHandler({ AcmeInvokeRuntimeException.class })
	public ResponseEntity<Object> handleAcmeInvokeRuntimeException(AcmeInvokeRuntimeException ex, WebRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(ResponseEntityExceptionHandlerConstant.CLIENT_ERROR_MESSAGE, errors, ex.getStatus());
	}

	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(ResponseEntityExceptionHandlerConstant.UNEXPECTED_ERROR_MESSAGE, errors,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

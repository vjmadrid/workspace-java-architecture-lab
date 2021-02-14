package com.acme.architecture.core.exception.handler;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.acme.architecture.core.constant.ResponseEntityExceptionHandlerConstant;
import com.acme.architecture.core.context.RequestContext;
import com.acme.architecture.core.error.CoreExceptionError;
import com.acme.architecture.core.response.CoreExceptionResponse;

public class ResponseEntityCommonExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseEntityCommonExceptionHandler.class);
	private static final String UUID_MESSAGE = "UUID = [{}] ";
	public static final String ERROR_MSG_LOG = UUID_MESSAGE
			.concat("Response exception - Message = [{}] - HTTPStatus = [{}] - Exception Detail = [{}]");

	public ResponseEntityCommonExceptionHandler() {
		super();
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(ResponseEntityExceptionHandlerConstant.HTTP_NOT_READABLE_ERROR_MESSAGE, errors,
				HttpStatus.BAD_REQUEST, headers);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		List<String> errors = new ArrayList<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.add("Field '" + error.getField() + "' " + error.getDefaultMessage()));

		ex.getBindingResult().getGlobalErrors()
				.forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

		return buildResponse(ResponseEntityExceptionHandlerConstant.INVALID_REQUEST_ERROR_MESSAGE, errors,
				HttpStatus.BAD_REQUEST, headers);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		return buildResponse(ResponseEntityExceptionHandlerConstant.UNEXPECTED_ERROR_MESSAGE, errors,
				HttpStatus.INTERNAL_SERVER_ERROR, headers);

	}

	protected ResponseEntity<Object> buildResponse(String message, List<String> errors, HttpStatus httpStatus) {

		List<CoreExceptionError> coreExceptionErrors = buildCoreExceptionErrors(errors);

		LOGGER.error(ERROR_MSG_LOG, RequestContext.getContext().getUuid(), message, httpStatus, coreExceptionErrors);

		return ResponseEntity.status(httpStatus).body(new CoreExceptionResponse(message, coreExceptionErrors));
	}

	protected ResponseEntity<Object> buildResponse(String message, List<String> errors, HttpStatus httpStatus,
			HttpHeaders headers) {

		List<CoreExceptionError> coreExceptionErrors = buildCoreExceptionErrors(errors);

		LOGGER.error(ERROR_MSG_LOG, RequestContext.getContext().getUuid(), message, httpStatus, coreExceptionErrors);

		return ResponseEntity.status(httpStatus).headers(headers)
				.body(new CoreExceptionResponse(message, coreExceptionErrors));
	}

	protected List<CoreExceptionError> buildCoreExceptionErrors(List<String> errors) {

		List<CoreExceptionError> coreExceptionErrors = new ArrayList<>();
		errors.forEach(e -> coreExceptionErrors.add(new CoreExceptionError(e)));
		return coreExceptionErrors;
	}
}

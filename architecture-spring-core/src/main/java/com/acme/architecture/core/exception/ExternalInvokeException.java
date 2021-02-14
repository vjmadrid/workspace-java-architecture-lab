package com.acme.architecture.core.exception;

import org.springframework.http.HttpStatus;

public class ExternalInvokeException extends Exception {

	private static final long serialVersionUID = -2817438524318425462L;
	
	private final HttpStatus status;

	public ExternalInvokeException(HttpStatus status, String message) {

		super(message);
		this.status = status;

	}

	public HttpStatus getStatus() {
		return status;
	}

}


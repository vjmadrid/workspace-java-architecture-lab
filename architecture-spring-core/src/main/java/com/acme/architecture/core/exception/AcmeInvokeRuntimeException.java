package com.acme.architecture.core.exception;

import org.springframework.http.HttpStatus;

public class AcmeInvokeRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -4246155777462379702L;

	private final HttpStatus status;

	private  final transient Object error;
	
	public AcmeInvokeRuntimeException(String message, HttpStatus status) {
		super(message);
		this.status = status;
		this.error = new Object();
	}

	public AcmeInvokeRuntimeException(String message, HttpStatus status, Object error) {
		super(message);
		this.status = status;
		this.error = error;
	}

	public Object getError() {
		return error;
	}

	public HttpStatus getStatus() {
		return status;
	}

}



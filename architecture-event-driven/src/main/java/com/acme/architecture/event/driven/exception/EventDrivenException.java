package com.acme.architecture.event.driven.exception;

import com.acme.architecture.common.exception.AcmeException;

public class EventDrivenException extends AcmeException {

	private static final long serialVersionUID = -4102004426627995641L;

	public EventDrivenException(String message) {
		super(message);
	}

	public EventDrivenException(Throwable cause) {
		super(cause);
	}

	public EventDrivenException(String message, Throwable cause) {
		super(message, cause);
	}

}

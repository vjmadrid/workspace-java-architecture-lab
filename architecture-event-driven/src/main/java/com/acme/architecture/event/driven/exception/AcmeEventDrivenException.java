package com.acme.architecture.event.driven.exception;

import com.acme.architecture.common.exception.AcmeException;

public class AcmeEventDrivenException extends AcmeException {

	private static final long serialVersionUID = -4102004426627995641L;

	public AcmeEventDrivenException(String message) {
		super(message);
	}

	public AcmeEventDrivenException(Throwable cause) {
		super(cause);
	}

	public AcmeEventDrivenException(String message, Throwable cause) {
		super(message, cause);
	}

}

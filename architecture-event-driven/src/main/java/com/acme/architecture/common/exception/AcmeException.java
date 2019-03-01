package com.acme.architecture.common.exception;

public class AcmeException extends Exception {

	private static final long serialVersionUID = 8467280149220441141L;

	public AcmeException(String message) {
		super(message);
	}

	public AcmeException(Throwable cause) {
		super(cause);
	}

	public AcmeException(String message, Throwable cause) {
		super(message, cause);
	}

}

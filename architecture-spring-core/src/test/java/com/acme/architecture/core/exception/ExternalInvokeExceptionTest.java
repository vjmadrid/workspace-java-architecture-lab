package com.acme.architecture.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.acme.architecture.core.exception.ExternalInvokeException;

public class ExternalInvokeExceptionTest {

	private static final String MESSAGE = "message";
	private static final HttpStatus STATUS = HttpStatus.FORBIDDEN;

	@Test
	public void testExternalInvokeException() {

		ExternalInvokeException externalInvokeException = new ExternalInvokeException(STATUS, MESSAGE);
		assertEquals(MESSAGE, externalInvokeException.getMessage());
		assertEquals(STATUS, externalInvokeException.getStatus());
	}
}

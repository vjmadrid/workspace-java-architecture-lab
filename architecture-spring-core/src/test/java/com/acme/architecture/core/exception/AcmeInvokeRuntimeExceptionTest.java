package com.acme.architecture.core.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;

public class AcmeInvokeRuntimeExceptionTest {

	private static final String MESSAGE = "message";
	private static final HttpStatus STATUS = HttpStatus.FORBIDDEN;
	private static final Object ERROR = "Error object";

	@Test
	public void testAcmeInvokeRuntimeException() {

		AcmeInvokeRuntimeException acmeInvokeRuntimeException = new AcmeInvokeRuntimeException(MESSAGE, STATUS, ERROR);
		assertEquals(MESSAGE, acmeInvokeRuntimeException.getMessage());
		assertEquals(STATUS, acmeInvokeRuntimeException.getStatus());
		assertEquals(ERROR, acmeInvokeRuntimeException.getError());		
	}
}

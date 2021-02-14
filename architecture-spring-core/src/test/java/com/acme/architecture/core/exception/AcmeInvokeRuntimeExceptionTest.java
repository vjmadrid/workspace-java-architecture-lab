package com.acme.architecture.core.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;

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
	
	@Test
	public void testAcmeInvokeRuntimeExceptionSecondConstructor() {

		AcmeInvokeRuntimeException acmeInvokeRuntimeException = new AcmeInvokeRuntimeException(MESSAGE, STATUS);
		assertEquals(MESSAGE, acmeInvokeRuntimeException.getMessage());
		assertEquals(STATUS, acmeInvokeRuntimeException.getStatus());
	}
}

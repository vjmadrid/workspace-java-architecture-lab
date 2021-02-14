package com.acme.architecture.core.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.acme.architecture.core.error.CoreExceptionError;

public class CoreExceptionErrorTest {

	private static final String MESSAGE = "message";

	@Test
	public void testCoreExceptionErrorEmpty() {

		CoreExceptionError coreExceptionError = new CoreExceptionError();

		assertNull(coreExceptionError.getError());
		assertEquals("CoreExceptionError(error=null)", coreExceptionError.toString());
	}

	@Test
	public void testCoreExceptionError() {

		CoreExceptionError coreExceptionError = new CoreExceptionError(MESSAGE);

		assertEquals(MESSAGE, coreExceptionError.getError());
		assertEquals("CoreExceptionError(error=message)", coreExceptionError.toString());
	}
}

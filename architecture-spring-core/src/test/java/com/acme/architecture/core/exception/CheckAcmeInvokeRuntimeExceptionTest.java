package com.acme.architecture.core.exception;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class CheckAcmeInvokeRuntimeExceptionTest {
	
	@Test
	public void whenException_thenThrowException() {
		assertThrows(AcmeInvokeRuntimeException.class, () -> {
			throw new AcmeInvokeRuntimeException("TEST", HttpStatus.ACCEPTED, new String());
		});
	}

}

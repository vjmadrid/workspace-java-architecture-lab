package com.acme.architecture.common.exception;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CheckAcmeExceptionTest {
	
	@Test
	public void whenExceptionWithString_thenThrowException() {
		assertThrows(AcmeException.class, () -> {
			throw new AcmeException("Test");
		});
	}
	
	@Test
	public void whenExceptionWithThrowable_thenThrowException() {
		assertThrows(AcmeException.class, () -> {
			throw new AcmeException(new ArithmeticException());
		});
	}
	
	@Test
	public void whenExceptionWithStringAndThrowable_thenThrowException() {
		assertThrows(AcmeException.class, () -> {
			throw new AcmeException("Test", new ArithmeticException());
		});
	}

}

package com.acme.architecture.testing.support;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractExceptionTestSupport {

	private static final String PHRASE_END = ".";

	private static final String EMPTY_STRING = "";

	private Exception exceptionParameter;

	private Exception exceptionThrowable;

	private Exception exceptionParameterAndThrowable;

	protected abstract Exception getExceptionWithParameter();

	protected abstract Exception getExceptionWithThrowable();

	protected abstract Exception getExceptionWithParameterAndThrowable();

	@Before
	public void beforeTest() {
		exceptionParameter = getExceptionWithParameter();
		exceptionThrowable = getExceptionWithThrowable();
		exceptionParameterAndThrowable = getExceptionWithParameterAndThrowable();
	}

	@Test
	public void shouldGetMessageWithMessageConstructor() {
		assertThat(exceptionParameter.getMessage())
				.contains(exceptionParameter.getMessage().replace(PHRASE_END, EMPTY_STRING));
	}

	@Test
	public void shouldGetMessageWithConstructorThrowable() {
		assertTrue(exceptionThrowable.getCause().getClass().equals(RuntimeException.class));
	}

	@Test
	public void shouldGetMessageWithConstructorParameterAndThrowable() {
		assertThat(exceptionParameterAndThrowable.getMessage())
				.contains(exceptionParameterAndThrowable.getMessage().replace(PHRASE_END, EMPTY_STRING));
		assertTrue(exceptionParameterAndThrowable.getCause().getClass().equals(RuntimeException.class));
	}

	
}

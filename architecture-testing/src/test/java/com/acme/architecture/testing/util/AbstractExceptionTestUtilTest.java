package com.acme.architecture.testing.util;

import com.acme.architecture.testing.util.example.ExampleCoverageException;

public class AbstractExceptionTestUtilTest extends AbstractExceptionTestUtil {

	@Override
	protected Exception getExceptionWithParameter() {
		return new ExampleCoverageException("1");
	}

	@Override
	protected Exception getExceptionWithThrowable() {
		return new ExampleCoverageException(new RuntimeException());
	}

	@Override
	protected Exception getExceptionWithParameterAndThrowable() {
		return new ExampleCoverageException("1", new RuntimeException());
	}

}

package com.acme.architecture.testing.support;

import com.acme.architecture.testing.util.example.ExampleCoverageException;

public class AbstractExceptionTestSupportTest extends AbstractExceptionTestSupport {

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

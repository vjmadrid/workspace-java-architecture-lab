package com.acme.architecture.common.exception;

import com.acme.architecture.testing.support.AbstractExceptionTestSupport;


public class AcmeExceptionTest extends AbstractExceptionTestSupport {

	@Override
	protected Exception getExceptionWithParameter() {
		return new AcmeException("1");
	}

	@Override
	protected Exception getExceptionWithThrowable() {
		return new AcmeException(new RuntimeException());
	}

	@Override
	protected Exception getExceptionWithParameterAndThrowable() {
		return new AcmeException("1", new RuntimeException());
	}

}

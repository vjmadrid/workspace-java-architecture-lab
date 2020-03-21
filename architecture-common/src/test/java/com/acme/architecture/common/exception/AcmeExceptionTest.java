package com.acme.architecture.common.exception;

import com.acme.architecture.testing.util.AbstractExceptionTestUtil;


public class AcmeExceptionTest extends AbstractExceptionTestUtil {

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

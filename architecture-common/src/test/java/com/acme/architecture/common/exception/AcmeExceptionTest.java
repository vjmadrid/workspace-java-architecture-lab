package com.acme.architecture.common.exception;

import com.acme.architecture.testing.exception.test.AbstractExceptionTest;


public class AcmeExceptionTest extends AbstractExceptionTest {

	@Override
	protected Exception getExceptionWithParameter() {
		return new AcmeException("1");
	}

}

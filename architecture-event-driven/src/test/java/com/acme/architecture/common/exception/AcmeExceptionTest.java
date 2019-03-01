package com.acme.architecture.common.exception;

import com.acme.architecture.event.driven.entity.AbstractExceptionTest;


public class AcmeExceptionTest extends AbstractExceptionTest {

	@Override
	protected Exception getExceptionWithParameter() {
		return new AcmeException("1");
	}

}

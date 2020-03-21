package com.acme.architecture.event.driven.exception;

import com.acme.architecture.testing.util.AbstractExceptionTestUtil;


public class AcmeEventDrivenExceptionTest extends AbstractExceptionTestUtil {

	@Override
	protected Exception getExceptionWithParameter() {
		return new AcmeEventDrivenException("1");
	}
	
	@Override
	protected Exception getExceptionWithThrowable() {
		return new AcmeEventDrivenException(new RuntimeException());
	}

	@Override
	protected Exception getExceptionWithParameterAndThrowable() {
		return new AcmeEventDrivenException("1", new RuntimeException());
	}

}

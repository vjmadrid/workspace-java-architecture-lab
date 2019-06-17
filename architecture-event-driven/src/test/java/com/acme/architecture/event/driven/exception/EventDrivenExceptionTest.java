package com.acme.architecture.event.driven.exception;

import com.acme.architecture.event.driven.exception.EventDrivenException;
import com.acme.architecture.testing.exception.test.AbstractExceptionTest;


public class EventDrivenExceptionTest extends AbstractExceptionTest {

	@Override
	protected Exception getExceptionWithParameter() {
		return new EventDrivenException("1");
	}

}

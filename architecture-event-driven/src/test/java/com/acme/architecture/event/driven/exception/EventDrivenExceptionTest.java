package com.acme.architecture.event.driven.exception;

import com.acme.architecture.event.driven.entity.AbstractExceptionTest;
import com.acme.architecture.event.driven.exception.EventDrivenException;


public class EventDrivenExceptionTest extends AbstractExceptionTest {

	@Override
	protected Exception getExceptionWithParameter() {
		return new EventDrivenException("1");
	}

}

package com.acme.architecture.event.driven.exception;

import com.acme.architecture.event.driven.exception.EventDrivenException;
import com.acme.architecture.testing.util.AbstractExceptionTestUtil;


public class EventDrivenExceptionTest extends AbstractExceptionTestUtil {

	@Override
	protected Exception getExceptionWithParameter() {
		return new EventDrivenException("1");
	}

}

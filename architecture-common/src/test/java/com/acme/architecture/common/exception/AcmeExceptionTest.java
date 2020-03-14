package com.acme.architecture.common.exception;

import com.acme.architecture.testing.util.AbstractExceptionTestUtil;


public class AcmeExceptionTest extends AbstractExceptionTestUtil {

	@Override
	protected Exception getExceptionWithParameter() {
		return new AcmeException("1");
	}

}

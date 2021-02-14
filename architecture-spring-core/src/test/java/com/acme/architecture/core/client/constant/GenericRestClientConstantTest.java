package com.acme.architecture.core.client.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.junit.util.JUnitTestUtil;

public class GenericRestClientConstantTest {

	@Test
	public void whenCallACheckConstantClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(GenericRestClientConstant.class);
	}
	
}

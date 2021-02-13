package com.acme.architecture.testing.junit.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.junit.util.JUnitTestUtil;

public class JUnitTestUtilConstantTest {
	
	@Test
	public void whenCallACheckConstantClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(JUnitTestUtilConstant.class);
	}

}

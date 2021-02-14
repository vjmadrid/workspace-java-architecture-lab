package com.acme.architecture.core.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.junit.util.JUnitTestUtil;

public class DefaultSpringConfigConstantTest {

	@Test
	public void whenCheckConstantClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(DefaultSpringConfigConstant.class);
	}
}

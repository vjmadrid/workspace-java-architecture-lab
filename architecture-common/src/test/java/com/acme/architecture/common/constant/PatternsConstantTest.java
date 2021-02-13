package com.acme.architecture.common.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.junit.util.JUnitTestUtil;

public class PatternsConstantTest {

	@Test
	public void whenCheckConstantClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(PatternsConstant.class);
	}
	
}

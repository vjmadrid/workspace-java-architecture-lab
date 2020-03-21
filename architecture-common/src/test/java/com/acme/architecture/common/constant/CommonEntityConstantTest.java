package com.acme.architecture.common.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.util.JUnitTestUtil;

public class CommonEntityConstantTest {

	@Test
	public void whenCheckConstantClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(CommonEntityConstant.class);
	}
}

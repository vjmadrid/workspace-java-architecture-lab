package com.acme.architecture.event.driven.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.util.JUnitTestUtil;

public class DummyGenericEventConstantTest {

	@Test
	public void whenCheckConstantClassWellDefined() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(DummyGenericEventConstant.class);
	}

}

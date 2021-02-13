package com.acme.architecture.event.driven.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.junit.util.JUnitTestUtil;

public class GenericEventConstantTest {

	@Test
	public void whenCheckConstantClassWellDefined() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(GenericEventConstant.class);
	}

}

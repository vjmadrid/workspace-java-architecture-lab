package com.acme.architecture.event.driven.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.util.JUnitTestBuilder;

public class DummyGenericEventConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(DummyGenericEventConstant.class);
	}

}

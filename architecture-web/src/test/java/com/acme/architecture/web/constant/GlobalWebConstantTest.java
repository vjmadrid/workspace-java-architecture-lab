package com.acme.architecture.web.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.event.driven.factory.JUnitTestBuilder;

public class GlobalWebConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(GlobalWebConstant.class);
	}

}

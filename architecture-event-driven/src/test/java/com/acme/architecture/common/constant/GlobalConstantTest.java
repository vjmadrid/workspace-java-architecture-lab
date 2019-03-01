package com.acme.architecture.common.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.event.driven.factory.JUnitTestBuilder;

public class GlobalConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(GlobalConstant.class);
	}

}

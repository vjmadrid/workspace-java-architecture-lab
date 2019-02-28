package com.acme.architecture.web.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.web.constant.GlobalWebConstant;

import es.dinersclub.architecture.testing.util.JUnitTestBuilder;

public class GlobalWebConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(GlobalWebConstant.class);
	}

}

package com.acme.architecture.common.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.util.JUnitTestBuilder;

public class SpringConfigConstantTest {

	@Test
	public void checkWellFormattedClass() throws NoSuchMethodException, InvocationTargetException,
			InstantiationException, IllegalAccessException {
		JUnitTestBuilder.assertUtilityClassWellDefined(SpringConfigConstant.class);
	}

}

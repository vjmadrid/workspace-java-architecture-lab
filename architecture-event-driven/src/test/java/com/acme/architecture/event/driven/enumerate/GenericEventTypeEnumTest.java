package com.acme.architecture.event.driven.enumerate;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.event.driven.factory.JUnitTestBuilder;

public class GenericEventTypeEnumTest {

	@Test
	public void checkValueOfEnum() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JUnitTestBuilder.superficialEnumCodeCoverage(GenericEventTypeEnum.class);
	}

}

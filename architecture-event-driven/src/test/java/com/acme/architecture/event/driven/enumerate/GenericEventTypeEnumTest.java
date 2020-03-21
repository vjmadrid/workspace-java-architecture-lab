package com.acme.architecture.event.driven.enumerate;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.util.JUnitTestUtil;

public class GenericEventTypeEnumTest {

	@Test
	public void whenCallACheckSuperficialEnumCodeCoverage() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JUnitTestUtil.checkSuperficialEnumCodeCoverage(GenericEventTypeEnum.class);
	}

}

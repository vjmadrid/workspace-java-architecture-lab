package com.acme.architecture.testing.junit.util;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.junit.util.JUnitTestUtil;
import com.acme.architecture.testing.util.example.ExampleCoverageEnum;

public class JUnitTestUtilForEnumTest {

	private final int NUM_VALUES_ENUM_CLASS = 1;
	
	@Test
	public void whenCallACheckUtilClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkUtilClassWellDefined(JUnitTestUtil.class);
	}

	@Test
	public void whenCallACheckSuperficialEnumCodeCoverageWithValid_ThenReturnOneElement() throws Exception {
		assertEquals(NUM_VALUES_ENUM_CLASS, JUnitTestUtil.checkSuperficialEnumCodeCoverage(ExampleCoverageEnum.class));
	}

}

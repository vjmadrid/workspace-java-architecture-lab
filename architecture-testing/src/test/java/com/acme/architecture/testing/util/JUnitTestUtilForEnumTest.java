package com.acme.architecture.testing.util;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.acme.architecture.testing.util.example.ExampleCoverageEnum;

public class JUnitTestUtilForEnumTest {

	private final int NUM_VALUES_ENUM_CLASS = 1;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		exception.expect(IllegalStateException.class);
		
		new JUnitTestUtil();
	}

	@Test
	public void whenCallACheckSuperficialEnumCodeCoverageWithValid_ThenReturnOneElement() throws Exception {
		assertEquals(NUM_VALUES_ENUM_CLASS, JUnitTestUtil.checkSuperficialEnumCodeCoverage(ExampleCoverageEnum.class));
	}

}

package com.acme.architecture.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NumberUtilTest {

	private static final Short SHORT_VALUE = 2;
	private static final int INT_VALUE = 2;

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, ()->{
			new NumberUtil();
		});
	}

	@Test
	public void whenCallIntValueToShortValue_thenReturnShortValue() throws Exception {

		Short result = NumberUtil.intValueToShortValue(INT_VALUE);
		assertEquals(SHORT_VALUE, result);
	}

}

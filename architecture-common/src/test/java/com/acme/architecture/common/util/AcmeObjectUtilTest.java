package com.acme.architecture.common.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class AcmeObjectUtilTest {
	
	private final String TEST_STRING_VALUE = "test";

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new AcmeObjectUtil();
		});
	}

	@Test
	public void whenCallASerialize_thenReturnValue() throws Exception {

		byte[] serialized = AcmeObjectUtil.serialize(TEST_STRING_VALUE);
	
		assertNotNull(serialized);
	}
	
	@Test
	public void whenCallADeserialize_thenReturnValue() throws Exception {

		byte[] serialized = AcmeObjectUtil.serialize(TEST_STRING_VALUE);
		Object deserialized = AcmeObjectUtil.deserialize(serialized);

		assertEquals(TEST_STRING_VALUE, String.valueOf(deserialized));
	}
	
	
	
}

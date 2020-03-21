package com.acme.architecture.common.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.util.JUnitTestUtil;


public final class ObjectUtilTest {
	
	private final String TEST_STRING_VALUE = "test";

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new ObjectUtil();
		});
	}
	
	@Test
	public void whenCallACheckUtilClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkUtilClassWellDefined(ObjectUtil.class);
	}

	@Test
	public void whenCallASerialize_thenReturnValue() throws Exception {

		byte[] serialized = ObjectUtil.serialize(TEST_STRING_VALUE);
	
		assertNotNull(serialized);
	}
	
	@Test
	public void whenCallADeserialize_thenReturnValue() throws Exception {

		byte[] serialized = ObjectUtil.serialize(TEST_STRING_VALUE);
		Object deserialized = ObjectUtil.deserialize(serialized);

		assertEquals(TEST_STRING_VALUE, String.valueOf(deserialized));
	}
	
	
	
}

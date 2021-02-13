package com.acme.architecture.common.util;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.acme.architecture.common.exception.AcmeException;

public class APIDummyUtilTest {

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, () -> {
			new APIDummyUtil();
		});
	}

	@Test
	public void whenCallCreateDummyInstanceClassFromResourceTest_thenReturnInstanceOfClass()
			throws AcmeException {

		DummyUtilObjectTest objectTest = APIDummyUtil
				.createDummyInstanceClassFromResourceTest(DummyUtilObjectTest.class, "/DummyUtilObjectTest.json");

		assertNotNull(objectTest);
		assertNotNull(objectTest.getInteger());
		assertNotNull(objectTest.getString());
	}

	@Test
	public void whenCallCreateDummyInstanceClassFromResourceTestWithoutFile_thenTrowRuntimeException() {
		assertThrows(AcmeException.class, () -> {
			APIDummyUtil.createDummyInstanceClassFromResourceTest(DummyUtilObjectTest.class, "");
		});
	}

	@Test
	public void whenCallCreateDummyInstanceClassResources_thenTrowRuntimeException() {
		assertThrows(AcmeException.class, () -> {
			APIDummyUtil.createDummyInstanceClassResources(DummyUtilObjectTest.class, "");
		});
	}

}

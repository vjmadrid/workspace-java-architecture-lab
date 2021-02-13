package com.acme.architecture.event.driven.util.converter;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.event.driven.entity.GenericEvent;
import com.acme.architecture.event.driven.factory.dummy.DummyGenericEventDataFactory;
import com.acme.architecture.testing.junit.util.JUnitTestUtil;

public class GenericEventConverterTest {

	private GenericEvent genericEventTest;
	
	private final String PART_JSON_CONTAINS = "{\"id\":\"1\",\"parentId\":\"";

	@BeforeEach
	public void init() {
		genericEventTest = DummyGenericEventDataFactory.createSampleDefault();
	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new GenericEventConverter();
		});
	}
	
	@Test
	public void whenCallACheckUtilClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkUtilClassWellDefined(GenericEventConverter.class);
	}
	
	@Test
	public void whenCallAConvertGenericEventToJsonWithNull_thenReturnNull() throws Exception {
		assertNull(GenericEventConverter.convertGenericEventToJson(null));
	}

	@Test
	public void whenCallAConvertGenericEventToJson_thenReturnObjectAsJson() throws Exception {
		String resultJSON = GenericEventConverter.convertGenericEventToJson(genericEventTest);
		
		assertNotNull(resultJSON);
		assertTrue(resultJSON.contains(PART_JSON_CONTAINS));
	}

	@Test
	public void whenCallAConvertJsonToGenericEventWithNull_thenReturnNull() throws Exception {
		assertNull(GenericEventConverter.fromJsonToGenericEvent(null));
	}
	
	@Test
	public void whenCallAConvertJsonToGenericEventWithEmpty_thenReturnNull() throws Exception {
		assertNull(GenericEventConverter.fromJsonToGenericEvent(""));
	}

	
	@Test
	public void whenCallAConvertJsonToObject_thenReturnObject() throws Exception {
		String resultJSON = GenericEventConverter.convertGenericEventToJson(genericEventTest);
	
		GenericEvent resultObject = GenericEventConverter.fromJsonToGenericEvent(resultJSON);
		
		assertNotNull(resultObject);
		assertEquals(genericEventTest,resultObject);
	}

}
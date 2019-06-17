package com.acme.architecture.event.driven.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

import com.acme.architecture.event.driven.entity.GenericEvent;
import com.acme.architecture.event.driven.factory.dummy.DummyGenericEventDataFactory;
import com.acme.architecture.testing.util.JUnitTestBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class GenericEventConverterTest {

	private GenericEvent genericEventTest;
	private String genericEventJSONTest;

	@Before
	public void init() {
		genericEventTest = DummyGenericEventDataFactory.createSampleDefault();
		genericEventJSONTest ="{\"id\":\"1\",\"parentId\":\"\",\"name\":\"Test Name 1\",\"type\":\"CREATE\",\"author\":\"Test ACME Author\",\"expirationSeconds\":0,\"payload\":\"Test Message 1\",\"createdDate\":1551718494268,\"updatedDate\":null,\"deletedDate\":null}\"";
	}

	@Test
	public void shouldFromObjectToJSONWithNull() {
		String result = GenericEventConverter.INSTANCE.fromObjectToJSON(null, false);
		
		assertEquals("null", result);
	}
	
	@Test
	public void shouldFromObjectToJSON() {
		String result = GenericEventConverter.INSTANCE.fromObjectToJSON(genericEventTest,false);
		
		assertNotNull(result);
		assertTrue(result.contains("\"id\":\"1\""));
	}
	
	@Test
	public void shouldFromObjectToJSONPretty() {
		String result = GenericEventConverter.INSTANCE.fromObjectToJSON(genericEventTest,true);
		
		assertNotNull(result);
		assertTrue(result.contains("\"id\" : \"1\""));
	}
	
	
	@Test (expected=NullPointerException.class)
	public void shouldFromJSONToObjectWithNull() throws JsonParseException, JsonMappingException, IOException {
		GenericEventConverter.INSTANCE.fromJSONToObject(null);
	}
	
	@Test
	public void shouldFromJSONToObject() throws JsonParseException, JsonMappingException, IOException {
		GenericEvent result = GenericEventConverter.INSTANCE.fromJSONToObject(genericEventJSONTest);
		
		assertNotNull(result);
	}


	@Test
	public void checkValueOfValidationUtils() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JUnitTestBuilder.superficialEnumCodeCoverage(GenericEventConverter.class);
	}

}
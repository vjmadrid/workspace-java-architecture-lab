package com.acme.architecture.event.driven.factory.dummy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.acme.architecture.event.driven.constant.DummyGenericEventConstant;
import com.acme.architecture.event.driven.entity.GenericEvent;

public class DummyGenericEventDataFactoryTest {

	@Before
	public void init() {
	}

	@Test
	public void shouldCreateSampleDefault() {
		assertNotNull(DummyGenericEventDataFactory.createSampleDefault());
	}

	@Test
	public void shouldCreateSampleMap() {
		Map<String, GenericEvent> result = DummyGenericEventDataFactory.createSampleMap();
		
		assertNotNull(result);
		assertEquals(DummyGenericEventConstant.TEST_NUM_GENERIC_EVENTS,result.size());
	}
	
	@Test
	public void shouldCreateSampleList() {
		List<GenericEvent> result = DummyGenericEventDataFactory.createSampleList();
		
		assertNotNull(result);
		assertEquals(DummyGenericEventConstant.TEST_NUM_GENERIC_EVENTS,result.size());
	}
	
	@Test
	public void shouldCreateDefaultConstructor() {
		assertNotNull(new DummyGenericEventDataFactory());
	}

}
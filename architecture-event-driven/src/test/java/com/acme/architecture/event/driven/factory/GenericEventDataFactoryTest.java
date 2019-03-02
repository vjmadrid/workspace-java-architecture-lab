package com.acme.architecture.event.driven.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.acme.architecture.event.driven.constant.DummyGenericEventConstant;
import com.acme.architecture.event.driven.entity.GenericEvent;

public class GenericEventDataFactoryTest {

	@Before
	public void init() {
	}

	@Test
	public void shouldCreate() {
		GenericEvent result = GenericEventDataFactory.create(DummyGenericEventConstant.TEST_GENERIC_EVENT_1_ID,DummyGenericEventConstant.TEST_GENERIC_EVENT_1_PARENT_ID, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_NAME,DummyGenericEventConstant.TEST_GENERIC_EVENT_1_TYPE, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_AUTHOR, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_EXPIRATION_SECONDS, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_PAYLOAD);
		
		assertNotNull(result);
		assertEquals(DummyGenericEventConstant.TEST_GENERIC_EVENT_1_ID,result.getId());
		assertEquals(DummyGenericEventConstant.TEST_GENERIC_EVENT_1_PARENT_ID,result.getParentId());
		
		assertNotNull(result.getCreatedDate());
		assertNull(result.getUpdatedDate());
		assertNull(result.getDeletedDate());
	}
	
	@Test
	public void shouldCreateWithLong() {
		GenericEvent result = GenericEventDataFactory.create(1,2, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_NAME,DummyGenericEventConstant.TEST_GENERIC_EVENT_1_TYPE, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_AUTHOR, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_EXPIRATION_SECONDS, DummyGenericEventConstant.TEST_GENERIC_EVENT_1_PAYLOAD);
		
		assertNotNull(result);
		assertEquals("1",result.getId());
		assertEquals("2",result.getParentId());
		
		assertNotNull(result.getCreatedDate());
		assertNull(result.getUpdatedDate());
		assertNull(result.getDeletedDate());
	}
	
	@Test
	public void shouldCreateDefaultConstructor() {
		assertNotNull(new GenericEventDataFactory());
	}

}
package com.acme.architecture.event.driven.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.acme.architecture.event.driven.factory.dummy.DummyGenericEventDataFactory;

public class GenericEventTest {
	
	private GenericEvent genericEvent;

	private GenericEvent anotherGenericEvent;

	private GenericEvent cloneGenericEvent;
	
	@Before
	public void init() {
		genericEvent = DummyGenericEventDataFactory.createSampleDefault();
		cloneGenericEvent =  DummyGenericEventDataFactory.createSampleDefault();
		anotherGenericEvent = DummyGenericEventDataFactory.createSampleDefault();
		anotherGenericEvent.setId("2");
	}
	
	@Test
	public void equalsMethodCheckTheType() {
		assertFalse(genericEvent.equals("a string"));
	}

	@Test
	public void equalsMehtodCheckSameObject() throws Exception {
		assertTrue(genericEvent.equals(genericEvent));
	}
	
	@Test
	public void equalsMehtodCheckIdField() throws Exception {
		assertFalse(genericEvent.equals(anotherGenericEvent));
	}

	@Test
	public void equalsMehtodCheckIdFieldEquals() throws Exception {
		assertTrue(genericEvent.equals(cloneGenericEvent));
	}

	@Test
	public void hashproductMethodBasedInTheIDField() throws Exception {
		assertNotSame(genericEvent.hashCode(), anotherGenericEvent.hashCode());
	}

		
	@Test
	public void shouldHaveADescriptiveToStringMethod() {
		assertNotSame(-1, genericEvent.toString().indexOf(GenericEvent.class.getSimpleName()));
	}

	@Test
	public void shouldHasMethodAccessors() {
		assertNotNull(genericEvent.getId());
		assertNotNull(genericEvent.getParentId());
		assertNotNull(genericEvent.getName());
		assertNotNull(genericEvent.getType());
		assertNotNull(genericEvent.getAuthor());
		assertNotNull(genericEvent.getExpirationSeconds());
		assertNotNull(genericEvent.getPayload());
		assertNotNull(genericEvent.getCreatedDate());
		assertNull(genericEvent.getUpdatedDate());
		assertNull(genericEvent.getDeletedDate());
	}

}

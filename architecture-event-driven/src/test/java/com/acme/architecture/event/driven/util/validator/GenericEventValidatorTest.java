package com.acme.architecture.event.driven.util.validator;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.event.driven.entity.GenericEvent;
import com.acme.architecture.event.driven.enumerate.GenericEventTypeEnum;
import com.acme.architecture.event.driven.factory.dummy.DummyGenericEventDataFactory;
import com.acme.architecture.testing.junit.util.JUnitTestUtil;

public class GenericEventValidatorTest {

	private GenericEvent genericEventTest;

	@BeforeEach
	public void init() {
		genericEventTest = DummyGenericEventDataFactory.createSampleDefault();
	}

	@Test
	public void shouldIsNull() {
		assertTrue(GenericEventValidator.INSTANCE.isNull(null));
	}

	@Test
	public void shouldIsNullWithNotNull() {
		assertFalse(GenericEventValidator.INSTANCE.isNull(genericEventTest));
	}

	@Test
	public void shouldIsNotNull() {
		assertTrue(GenericEventValidator.INSTANCE.isNotNull(genericEventTest));
	}

	@Test
	public void shouldIsNotNullWithNull() {
		assertFalse(GenericEventValidator.INSTANCE.isNotNull(null));
	}

	@Test
	public void shouldIsValidWithNull() {
		assertFalse(GenericEventValidator.INSTANCE.isValid(null));
	}

	@Test
	public void shouldIsValidWithIdNull() {
		genericEventTest.setId(null);
		assertFalse(GenericEventValidator.INSTANCE.isValid(genericEventTest));
	}

	@Test
	public void shouldIsValid() {
		assertTrue(GenericEventValidator.INSTANCE.isValid(genericEventTest));
	}
	
	@Test
	public void shouldIsGenericCreateTypeWithNull() {
		assertFalse(GenericEventValidator.INSTANCE.isGenericCreateType(null));
	}
	
	@Test
	public void shouldIsGenericCreateTypeWithIdNull() {
		genericEventTest.setId(null);
		assertFalse(GenericEventValidator.INSTANCE.isGenericCreateType(genericEventTest));
	}

	@Test
	public void shouldIsGenericCreateTypeWithNoValidValue() {
		genericEventTest.setType(GenericEventTypeEnum.DELETE.toString());
		assertFalse(GenericEventValidator.INSTANCE.isGenericCreateType(genericEventTest));
	}
	
	@Test
	public void shouldIsGenericCreateType() {
		assertTrue(GenericEventValidator.INSTANCE.isGenericCreateType(genericEventTest));
	}
	
	@Test
	public void shouldIsGenericUpdateTypeWithNull() {
		assertFalse(GenericEventValidator.INSTANCE.isGenericUpdateType(null));
	}
	
	@Test
	public void shouldIsGenericUpdateTypeWithIdNull() {
		genericEventTest.setType(GenericEventTypeEnum.UPDATE.toString());
		genericEventTest.setId(null);
		assertFalse(GenericEventValidator.INSTANCE.isGenericUpdateType(genericEventTest));
	}

	@Test
	public void shouldIsGenericUpdateTypeWithNoValidValue() {
		genericEventTest.setType(GenericEventTypeEnum.DELETE.toString());
		assertFalse(GenericEventValidator.INSTANCE.isGenericUpdateType(genericEventTest));
	}
	
	@Test
	public void shouldIsGenericUpdateType() {
		genericEventTest.setType(GenericEventTypeEnum.UPDATE.toString());
		assertTrue(GenericEventValidator.INSTANCE.isGenericUpdateType(genericEventTest));
	}
	
	@Test
	public void shouldIsGenericDeleteTypeWithNull() {
		assertFalse(GenericEventValidator.INSTANCE.isGenericDeleteType(null));
	}
	
	@Test
	public void shouldIsGenericDeleteTypeWithIdNull() {
		genericEventTest.setType(GenericEventTypeEnum.DELETE.toString());
		genericEventTest.setId(null);
		assertFalse(GenericEventValidator.INSTANCE.isGenericDeleteType(genericEventTest));
	}

	@Test
	public void shouldIsGenericDeleteTypeWithNoValidValue() {
		genericEventTest.setType(GenericEventTypeEnum.UPDATE.toString());
		assertFalse(GenericEventValidator.INSTANCE.isGenericDeleteType(genericEventTest));
	}
	
	@Test
	public void shouldIsGenericDeleteType() {
		genericEventTest.setType(GenericEventTypeEnum.DELETE.toString());
		assertTrue(GenericEventValidator.INSTANCE.isGenericDeleteType(genericEventTest));
	}
	
	@Test
	public void shouldIsDeletedLogicalVipWithIdUserMessageNull() {
		genericEventTest.setId(null);
		assertFalse(GenericEventValidator.INSTANCE.isDeletedLogical(genericEventTest));
	}
	
	@Test
	public void shouldIsDeletedLogicalVipNoValidValue() {
		assertFalse(GenericEventValidator.INSTANCE.isDeletedLogical(genericEventTest));
	}
	
	@Test
	public void shouldUsDeletedLogical() {
		genericEventTest.setDeletedDate(new Date());
		assertTrue(GenericEventValidator.INSTANCE.isDeletedLogical(genericEventTest));
	}
	
	@Test
	public void whenCallACheckSuperficialEnumCodeCoverage() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JUnitTestUtil.checkSuperficialEnumCodeCoverage(GenericEventValidator.class);
	}

}
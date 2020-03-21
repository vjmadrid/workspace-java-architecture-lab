package com.acme.architecture.common.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.util.JUnitTestUtil;

public final class DateUtilTest {
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new DateUtil();
		});
	}
	
	@Test
	public void whenCallACheckUtilClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkUtilClassWellDefined(DateUtil.class);
	}
	
	@Test
	public void whenCallAsDateWithLocalDate_thenReturnDate() {
		assertNotNull(DateUtil.asDate(LocalDate.now()));
	}

	@Test
	public void whenCallAsDateWithLocalDateTime_thenReturnDate() {
		assertNotNull(DateUtil.asDate(LocalDateTime.now()));
	}

	@Test
	public void whenCallAsLocalDateWithDate_thenReturnLocalDateTime() {
		assertNotNull(DateUtil.asLocalDate(new Date()));
	}

	@Test
	public void whenCallAsDateTimeWithDate_thenReturnLocalDateTime() {
		assertNotNull(DateUtil.asLocalDateTime(new Date()));
	}


}
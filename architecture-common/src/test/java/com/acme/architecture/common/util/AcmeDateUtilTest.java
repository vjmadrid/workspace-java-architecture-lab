package com.acme.architecture.common.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class AcmeDateUtilTest {
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new AcmeDateUtil();
		});
	}
	
	@Test
	public void whenCallAsDateWithLocalDate_thenReturnDate() {
		assertNotNull(AcmeDateUtil.asDate(LocalDate.now()));
	}

	@Test
	public void whenCallAsDateWithLocalDateTime_thenReturnDate() {
		assertNotNull(AcmeDateUtil.asDate(LocalDateTime.now()));
	}

	@Test
	public void whenCallAsLocalDateWithDate_thenReturnLocalDateTime() {
		assertNotNull(AcmeDateUtil.asLocalDate(new Date()));
	}

	@Test
	public void whenCallAsDateTimeWithDate_thenReturnLocalDateTime() {
		assertNotNull(AcmeDateUtil.asLocalDateTime(new Date()));
	}


}
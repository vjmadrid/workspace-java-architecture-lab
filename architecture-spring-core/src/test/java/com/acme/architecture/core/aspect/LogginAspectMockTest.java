package com.acme.architecture.core.aspect;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogginAspectMockTest {

	public static final String METHOD_NAME = "testMehtod";

	private JoinPoint jp;

	private Signature signature;

	private Integer result = 0;

	private LoggingAspect loggingAspect = new LoggingAspect();

	@BeforeEach
	public final void setUp() {

		Integer[] args = { 1, 2, 3 };

		jp = mock(JoinPoint.class);
		signature = mock(Signature.class);

		when(signature.getName()).thenReturn(METHOD_NAME);
		
		when(jp.getArgs()).thenReturn(args);
		when(jp.getSignature()).thenReturn(signature);
	}
	
	@Test
	public void whenCallPointCut_doNothing() {
		
		loggingAspect.inControllerLayer();
		loggingAspect.inServiceLayer();
		
		verify(jp, times(0)).getArgs();
		verify(jp, times(0)).getSignature();
		verify(signature, times(0)).getName();
	}

	@Test
	public void whenCallLoggerBeforeControllerLayer_thenPrintEnteringControllerLogs() {
		loggingAspect.loggerBeforeControllerLayer(jp);

		verify(jp, times(2)).getArgs();
		verify(jp, times(1)).getSignature();
		verify(signature, times(1)).getName();
	}

	@Test
	public void whenCallLoggerAfterControllerLayer_thenPrintResultControllerLogs() {
		loggingAspect.loggerAfterControllerLayer(jp, result);
		
		verify(jp, times(0)).getArgs();
		verify(jp, times(1)).getSignature();
		verify(signature, times(1)).getName();
	}

	@Test
	public void whenCallLoggerBeforeServiceLayer_thenPrintEnteringServiceLogs() {
		loggingAspect.loggerBeforeServiceLayer(jp);
		
		verify(jp, times(2)).getArgs();
		verify(jp, times(1)).getSignature();
		verify(signature, times(1)).getName();
	}

	@Test
	public void whenCallLoggerAfterServiceLayer_thenPrintResultServiceLogs() {
		loggingAspect.loggerAfterServiceLayer(jp, result);

		verify(jp, times(0)).getArgs();
		verify(jp, times(1)).getSignature();
		verify(signature, times(1)).getName();
	}

	// Protected methods
	@Test
	public void whenCallGetMethodName_thenReturnCapitalizedName() {
		String method = loggingAspect.getMethodName(jp);

		assertEquals(StringUtils.capitalize(METHOD_NAME), method);
	}

	@Test
	public void whenCallGetArgumentsWithoutArg_thenReturnArgToString() {
		String argString = loggingAspect.getArguments(jp);

		assertTrue(argString.toLowerCase().contains("args"));
		assertTrue(argString.contains(jp.getArgs().toString()));
	}

	@Test
	public void whenCallGetArgumentsWithoutArg_thenReturnWithoutArguments() {
		Integer[] args = {};
		when(jp.getArgs()).thenReturn(args);

		String argString = loggingAspect.getArguments(jp);

		assertTrue(argString.toLowerCase().contains("without"));
		assertTrue(argString.toLowerCase().contains("arguments"));
	}

}

package com.acme.architecture.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.util.JUnitTestUtil;


public final class ListUtilTest {
	
	private final String EMPTY_INPUT = "";
	private final String NUMERIC_INPUT = "1,2,3";
	private final String CHARACTER_INPUT = "a,b,c";
	private final String HYBRID_INPUT = "a,1,b,2,c,3"; 
	
	private final String NUMERIC_RESULT = "[1, 2, 3]";
	private final String CHARACTER_RESULT = "[a, b, c]";
	private final String HYBRID_RESULT = "[a, 1, b, 2, c, 3]";
	private final String EMPTY_RESULT = "[]";
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new ListUtil();
		});
	}
	
	@Test
	public void whenCallACheckUtilClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkUtilClassWellDefined(ListUtil.class);
	}

	@Test
	public void whenCallAGetTokensByStringTokenizerWithNull_thenReturnAEmptyList() throws Exception {
		List<String> result = ListUtil.getTokensByStringTokenizer(null);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithEmptyString_thenReturnAEmptyList() throws Exception {
		List<String> result = ListUtil.getTokensByStringTokenizer(EMPTY_INPUT);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithNumericString_thenReturnAResultList() throws Exception {
		List<String> result = ListUtil.getTokensByStringTokenizer(NUMERIC_INPUT);
		
		assertNotNull(result);
		assertEquals(NUMERIC_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithCharacterString_thenReturnAResultList() throws Exception {
		List<String> result = ListUtil.getTokensByStringTokenizer(CHARACTER_INPUT);
		
		assertNotNull(result);
		assertEquals(CHARACTER_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithHybridString_thenReturnAResultList() throws Exception {
		List<String> result = ListUtil.getTokensByStringTokenizer(HYBRID_INPUT);
		
		assertNotNull(result);
		assertEquals(HYBRID_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAIsNumericWithNull_thenReturnFalse() throws Exception {
		assertFalse(ListUtil.isNumeric(null));
	}
	
	@Test
	public void whenCallAIsNumericWithEmpty_thenReturnFalse() throws Exception {
		assertFalse(ListUtil.isNumeric(EMPTY_INPUT));
	}
	
	@Test
	public void whenCallAIsNumericWithValueInvalid_thenReturnFalse() throws Exception {
		assertFalse(ListUtil.isNumeric("A"));
	}
	
	@Test
	public void whenCallAIsNumeric_thenReturnFalse() throws Exception {
		assertTrue(ListUtil.isNumeric("1"));
	}
	
	@Test
	public void whenCallAGetNumericListWithNull_thenReturnAEmptyList() throws Exception {
		List<String> result = ListUtil.getNumericList(null);
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithEmpty_thenReturnAEmptyList() throws Exception {
		List<String> result = ListUtil.getNumericList(Collections.emptyList());
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithOneInvalidValue_thenReturnAEmptyList() throws Exception {
		List<String> result = ListUtil.getNumericList(Arrays.asList("uno"));
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithOneValidValue_thenReturnAOneElementList() throws Exception {
		List<String> result = ListUtil.getNumericList(Arrays.asList("1"));
		
		assertNotNull(result);
		assertEquals("[1]", result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithWithNumeric_thenReturnANumericList() throws Exception {
		List<String> result = ListUtil.getNumericList(ListUtil.getTokensByStringTokenizer(NUMERIC_INPUT));
		
		assertNotNull(result);
		assertEquals(NUMERIC_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithWithCharacter_thenReturnAEmptyList() throws Exception {
		List<String> result = ListUtil.getNumericList(ListUtil.getTokensByStringTokenizer(CHARACTER_INPUT));
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithWithHybrid_thenReturnANumericList() throws Exception {
		List<String> result = ListUtil.getNumericList(ListUtil.getTokensByStringTokenizer(HYBRID_INPUT));
		
		assertNotNull(result);
		assertEquals(NUMERIC_RESULT, result.toString());
	}
	
}

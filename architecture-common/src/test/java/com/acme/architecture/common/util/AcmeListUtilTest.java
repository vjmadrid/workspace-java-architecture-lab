package com.acme.architecture.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


public class AcmeListUtilTest {
	
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
			new AcmeListUtil();
		});
	}

	@Test
	public void whenCallAGetTokensByStringTokenizerWithNull_thenReturnAEmptyList() throws Exception {
		List<String> result = AcmeListUtil.getTokensByStringTokenizer(null);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithEmptyString_thenReturnAEmptyList() throws Exception {
		List<String> result = AcmeListUtil.getTokensByStringTokenizer(EMPTY_INPUT);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithNumericString_thenReturnAResultList() throws Exception {
		List<String> result = AcmeListUtil.getTokensByStringTokenizer(NUMERIC_INPUT);
		
		assertNotNull(result);
		assertEquals(NUMERIC_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithCharacterString_thenReturnAResultList() throws Exception {
		List<String> result = AcmeListUtil.getTokensByStringTokenizer(CHARACTER_INPUT);
		
		assertNotNull(result);
		assertEquals(CHARACTER_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetTokensByStringTokenizerWithHybridString_thenReturnAResultList() throws Exception {
		List<String> result = AcmeListUtil.getTokensByStringTokenizer(HYBRID_INPUT);
		
		assertNotNull(result);
		assertEquals(HYBRID_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAIsNumericWithNull_thenReturnFalse() throws Exception {
		assertFalse(AcmeListUtil.isNumeric(null));
	}
	
	@Test
	public void whenCallAIsNumericWithEmpty_thenReturnFalse() throws Exception {
		assertFalse(AcmeListUtil.isNumeric(EMPTY_INPUT));
	}
	
	@Test
	public void whenCallAIsNumericWithValueInvalid_thenReturnFalse() throws Exception {
		assertFalse(AcmeListUtil.isNumeric("A"));
	}
	
	@Test
	public void whenCallAIsNumeric_thenReturnFalse() throws Exception {
		assertTrue(AcmeListUtil.isNumeric("1"));
	}
	
	@Test
	public void whenCallAGetNumericListWithNull_thenReturnAEmptyList() throws Exception {
		List<String> result = AcmeListUtil.getNumericList(null);
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithEmpty_thenReturnAEmptyList() throws Exception {
		List<String> result = AcmeListUtil.getNumericList(Collections.emptyList());
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithOneInvalidValue_thenReturnAEmptyList() throws Exception {
		List<String> result = AcmeListUtil.getNumericList(Arrays.asList("uno"));
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithOneValidValue_thenReturnAOneElementList() throws Exception {
		List<String> result = AcmeListUtil.getNumericList(Arrays.asList("1"));
		
		assertNotNull(result);
		assertEquals("[1]", result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithWithNumeric_thenReturnANumericList() throws Exception {
		List<String> result = AcmeListUtil.getNumericList(AcmeListUtil.getTokensByStringTokenizer(NUMERIC_INPUT));
		
		assertNotNull(result);
		assertEquals(NUMERIC_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithWithCharacter_thenReturnAEmptyList() throws Exception {
		List<String> result = AcmeListUtil.getNumericList(AcmeListUtil.getTokensByStringTokenizer(CHARACTER_INPUT));
		
		assertNotNull(result);
		assertEquals(EMPTY_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetNumericListWithWithHybrid_thenReturnANumericList() throws Exception {
		List<String> result = AcmeListUtil.getNumericList(AcmeListUtil.getTokensByStringTokenizer(HYBRID_INPUT));
		
		assertNotNull(result);
		assertEquals(NUMERIC_RESULT, result.toString());
	}
	
}

package com.acme.architecture.testing.util;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AnnotationTestUtilTest {
	
	private String ANNOTATION_STRING_CLASS_RESULT = "[]";
	private String ANNOTATION_MOCK_CLASS_RESULT = "[Target, Retention, Documented]";

	private List<String> annotationListMockClassTest = null;
	private List<String> annotationPartialListMockClassTest = null;
	
	
	@BeforeEach
	public final void setUp() {
		annotationListMockClassTest = Arrays.asList("Target","Retention","Documented");
		annotationPartialListMockClassTest = Arrays.asList("Target","Retention");
	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		
		assertThrows(IllegalStateException.class, ()->{
			new AnnotationTestUtil();
		});

	}

	
	@Test
	public void whenCallAGetAnnotationNameListWithNull_thenReturnAEmptyList() throws Exception {
		List<String> result = AnnotationTestUtil.getAnnotationNameList(null);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenCallAGetAnnotationNameListWithString_thenReturnAEmptyList() throws Exception {
		List<String> result = AnnotationTestUtil.getAnnotationNameList(Object.class);
		
		assertNotNull(result);
		assertTrue(result.isEmpty());
		assertEquals(ANNOTATION_STRING_CLASS_RESULT, result.toString());
	}
	
	@Test
	public void whenCallAGetAnnotationNameListWithMock_thenReturnAAnnotationList() throws Exception {
		List<String> result = AnnotationTestUtil.getAnnotationNameList(Mock.class);
		
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(ANNOTATION_MOCK_CLASS_RESULT, result.toString());
	}
	
	@Test
	public void whenCallACheckAnnotationsListValidWithNullClass_thenReturnFalse() throws Exception {
		assertFalse(AnnotationTestUtil.checkAnnotationsListValid(null, annotationListMockClassTest));
	}
	
	@Test
	public void whenCallACheckAnnotationsListValidWithClassNoAnnotationsAndNullList_thenReturnFalse() throws Exception {
		assertFalse(AnnotationTestUtil.checkAnnotationsListValid(Object.class, null));
	}
	
	@Test
	public void whenCallACheckAnnotationsListValidWithNullList_thenReturnFalse() throws Exception {
		assertFalse(AnnotationTestUtil.checkAnnotationsListValid(Mock.class, null));
	}
	
	@Test
	public void whenCallACheckAnnotationsListValidWithClassNoAnnotations_thenReturnFalse() throws Exception {
		assertFalse(AnnotationTestUtil.checkAnnotationsListValid(Object.class, annotationListMockClassTest));
	}
	
	@Test
	public void whenCallACheckAnnotationsListValid_thenReturnFalse() throws Exception {
		assertTrue(AnnotationTestUtil.checkAnnotationsListValid(Mock.class, annotationListMockClassTest));
	}
	
	@Test
	public void whenCallACheckAnnotationsListValidWithPartialList_thenReturnFalse() throws Exception {
		assertTrue(AnnotationTestUtil.checkAnnotationsListValid(Mock.class, annotationPartialListMockClassTest));
	}

}


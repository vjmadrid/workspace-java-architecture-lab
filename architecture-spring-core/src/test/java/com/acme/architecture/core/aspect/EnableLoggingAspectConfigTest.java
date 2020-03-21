package com.acme.architecture.core.aspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.acme.architecture.testing.util.AnnotationTestUtil;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={EnableLoggingAspectConfig.class})
public class EnableLoggingAspectConfigTest {
	
	private final Integer NUM_ANNOTATIONS = 2;
	private final String ANNOTATION_CONFIGURATION = "Configuration";
	private final String ANNOTATION_ENABLE_ASPECT_JAUTO_PROXY = "EnableAspectJAutoProxy";
	private List<String> ANNOTATION_NAME_LIST_VALIDATION = Arrays.asList(ANNOTATION_CONFIGURATION, ANNOTATION_ENABLE_ASPECT_JAUTO_PROXY);
	
	Class<EnableLoggingAspectConfig> enableLoggingAspectConfigClass = EnableLoggingAspectConfig.class;
	
	@Autowired
	private EnableLoggingAspectConfig enableLoggingAspectConfig;
	
	@BeforeEach
	public final void setUp() {
	
	}
	
	@Test
	public void shouldContentAnnotationsValidStruct() {
		List<String> annotationNameList = AnnotationTestUtil.getAnnotationNameList(enableLoggingAspectConfigClass);
		
		assertNotNull(annotationNameList);
		assertFalse(annotationNameList.isEmpty());
		assertEquals(NUM_ANNOTATIONS, Integer.valueOf(annotationNameList.size()));
	}
	
	@Test
	public void shouldContentAnnotationsValidContent() {
		assertTrue(AnnotationTestUtil.checkAnnotationsListValid(enableLoggingAspectConfigClass, ANNOTATION_NAME_LIST_VALIDATION));
	}
	
	@Test
    public final void shouldBeInjected() {
		assertNotNull(enableLoggingAspectConfig);
	}

}

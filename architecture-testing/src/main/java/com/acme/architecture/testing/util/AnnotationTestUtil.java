package com.acme.architecture.testing.util;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationTestUtil {

	protected AnnotationTestUtil() {
		throw new IllegalStateException("AnnotationTestUtil");
	}

	public static List<String> getAnnotationNameList(Class<?> valueClass) {
		
		if (valueClass == null) {
			return Collections.emptyList();
		}
		
		List<Annotation> annotationList = Arrays.asList(valueClass.getAnnotations());
		
		return annotationList.stream().map(annotation -> annotation .annotationType().getSimpleName()).collect(Collectors.toList());
	}
	
	public static Boolean checkAnnotationsListValid(Class<?> valueClass, List<String> annotationNameValueList) {
		
		if ( (valueClass == null)||(annotationNameValueList == null)||(annotationNameValueList.isEmpty())){
			return false;
		}
		
		List<String> annotationList = getAnnotationNameList(valueClass);
		
		if (!annotationList.isEmpty()) {
			return annotationList.containsAll(annotationNameValueList);
		}
		
		return false;
	}
	
	
	
}

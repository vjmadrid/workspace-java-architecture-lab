package com.acme.architecture.core.util.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import com.acme.architecture.common.util.converter.AcmeCollectionConverter;
import com.acme.architecture.core.util.example.ExampleComponentClass;
import com.acme.architecture.core.util.example.ExampleComponentWithParameterClass;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public class AcmeReflectSpringUtilTest {
	
	private int NUM_CLASS = 2;
	
	private String BASE_PACKAGE_TEST_VALUE = "com.acme.architecture.core.util.example";
	
	private String CLASS_SIMPLE_NAME_VALID = "ExampleComponentClass";
	private String CLASS_SIMPLE_NAME_WITH_PARAMETER_VALID = "ExampleComponentWithParameterClass";
	private String CLASS_SIMPLE_NAME_PARAMETER_VALID = "ExampleParameterClass";
	
	@BeforeEach
	public final void setUp() throws IOException {
		
	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new AcmeReflectionSpringUtil();
		});
	}
	
	@Test
	public void whenCallGetBeanDefinitionsSetWithNullBasePackage_thenReturnEmptySet() throws Exception {
		Set<BeanDefinition> beanDefinitionSet = AcmeReflectionSpringUtil.getBeanDefinitionsSet(null, null);
		
		assertNotNull(beanDefinitionSet);
		assertThat(beanDefinitionSet).isEmpty();
		
	}
	
	@Test
	public void whenCallAGetBeanDefinitionsSetWithNullAnnotation_thenReturnOneElementSet() throws Exception {
		Set<BeanDefinition> beanDefinitionSet = AcmeReflectionSpringUtil.getBeanDefinitionsSet(BASE_PACKAGE_TEST_VALUE, null);

		assertNotNull(beanDefinitionSet);
		assertThat(beanDefinitionSet).isEmpty();
	}
	
	@Test
	public void whenCallAGetBeanDefinitionsSetWithAnnotationInvalid_thenReturnOneElementSet() throws Exception {
		Set<BeanDefinition> beanDefinitionSet = AcmeReflectionSpringUtil.getBeanDefinitionsSet(BASE_PACKAGE_TEST_VALUE, JsonNaming.class);
		
		assertNotNull(beanDefinitionSet);
		assertThat(beanDefinitionSet).isEmpty();
	}
	
	@Test
	public void whenCallAGetBeanDefinitionsSet_thenReturnOneElementSet() throws Exception {
		Set<BeanDefinition> beanDefinitionSet = AcmeReflectionSpringUtil.getBeanDefinitionsSet(BASE_PACKAGE_TEST_VALUE, Component.class);

		assertNotNull(beanDefinitionSet);
		assertThat(beanDefinitionSet).hasSize(NUM_CLASS);
		
		List<String> beanDefinitionList = AcmeCollectionConverter.toList(beanDefinitionSet).stream().map( bd -> bd.getBeanClassName()).collect(Collectors.toList());
	
		assertThat(beanDefinitionList).hasSize(NUM_CLASS);
		assertThat(beanDefinitionList).contains(BASE_PACKAGE_TEST_VALUE + "." + CLASS_SIMPLE_NAME_VALID, BASE_PACKAGE_TEST_VALUE + "." + CLASS_SIMPLE_NAME_WITH_PARAMETER_VALID);
	}
	
	@Test
	public void whenCallAGetClassSetWithNullBasePackage_thenReturnEmptySet() throws Exception {
		Set<Class<?>> classSet = AcmeReflectionSpringUtil.getClassSet(null, null);
		
		assertNotNull(classSet);
		assertThat(classSet).isEmpty();
	}
	
	@Test
	public void whenCallAGetClassSetWithAnnotationInvalid_thenReturnEmptySet() throws Exception {
		Set<Class<?>> classSet = AcmeReflectionSpringUtil.getClassSet(BASE_PACKAGE_TEST_VALUE, JsonNaming.class);
		
		assertNotNull(classSet);
		assertThat(classSet).isEmpty();
	}
	
	@Test
	public void whenCallAGetClassSet_thenReturnOneElementSet() throws Exception {
		Set<Class<?>> classSet = AcmeReflectionSpringUtil.getClassSet(BASE_PACKAGE_TEST_VALUE, Component.class);
		
		assertNotNull(classSet);
		assertThat(classSet).hasSize(NUM_CLASS);
		
		List<String> classList = AcmeCollectionConverter.toList(classSet).stream().map( clazz -> clazz.getSimpleName()).collect(Collectors.toList());
		
		assertThat(classList).hasSize(NUM_CLASS);
		assertThat(classList).contains(CLASS_SIMPLE_NAME_VALID,CLASS_SIMPLE_NAME_WITH_PARAMETER_VALID);
	}
	
	@Test
	public void whenCallAGetInstanceSetWithNullBasePackage_thenReturnEmptySet() throws Exception {
		Set<Object> instanceSet = AcmeReflectionSpringUtil.getInstancesSet(null, null);
		
		assertNotNull(instanceSet);
		assertThat(instanceSet).isEmpty();
		
	}
	
	@Test
	public void whenCallAGetInstanceSetWithAnnotationInvalid_thenReturnEmptySet() throws Exception {
		Set<Object> instanceSet = AcmeReflectionSpringUtil.getInstancesSet(BASE_PACKAGE_TEST_VALUE, JsonNaming.class);
		
		assertNotNull(instanceSet);
		assertThat(instanceSet).isEmpty();
	}
	
	@Test
	public void whenCallAGetInstanceSetWithExampleComponentClass_thenReturnElementSet() throws Exception {
		Set<Object> instanceSet = AcmeReflectionSpringUtil.getInstancesSet(BASE_PACKAGE_TEST_VALUE, Component.class);
		
		assertNotNull(instanceSet);
		assertThat(instanceSet).hasSize(NUM_CLASS);
		
		List<Object> instanceList = AcmeCollectionConverter.toList(instanceSet).stream().filter(instanceClazz -> instanceClazz instanceof ExampleComponentClass).collect(Collectors.toList());
	
		assertThat(instanceList).hasSize(1);
		
		ExampleComponentClass exampleConfigurationClassTest = (ExampleComponentClass) instanceList.get(0);
		
		assertEquals("test", exampleConfigurationClassTest.getValue());
	}

	@Test
	public void whenCallAGetInstanceSetWithExampleComponentWithParameterClass_thenReturnElementSet() throws Exception {
		Set<Object> instanceSet = AcmeReflectionSpringUtil.getInstancesSet(BASE_PACKAGE_TEST_VALUE, Component.class);
		
		assertNotNull(instanceSet);
		assertThat(instanceSet).hasSize(NUM_CLASS);
		
		List<Object> instanceList = AcmeCollectionConverter.toList(instanceSet).stream().filter(instanceClazz -> instanceClazz instanceof ExampleComponentWithParameterClass).collect(Collectors.toList());
		
		assertThat(instanceList).hasSize(1);
		
		ExampleComponentWithParameterClass exampleComponentWithParameterClass = (ExampleComponentWithParameterClass) instanceList.get(0);

		assertEquals("test", exampleComponentWithParameterClass.getValue());
		assertEquals(null, exampleComponentWithParameterClass.getParameterValue());
		
		assertNotNull(exampleComponentWithParameterClass.getParam());
		
		when(exampleComponentWithParameterClass.getParam().getValue()).thenReturn("test updated");
		assertEquals("test updated", exampleComponentWithParameterClass.getParam().getValue());
	}

	
}

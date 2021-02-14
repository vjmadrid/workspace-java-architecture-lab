package com.acme.architecture.core.util.reflection;

import static org.mockito.Mockito.mock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public final class AcmeReflectionSpringUtil {
	
	protected AcmeReflectionSpringUtil() {
		throw new IllegalStateException("ReflectionSpringUtil");

	}

	public static Set<BeanDefinition> getBeanDefinitionsSet(final String basePackage, final Class<? extends Annotation> annotationType) {

		if ((Objects.isNull(basePackage)) || (Objects.isNull(annotationType))) {
			return Collections.emptySet();
		}
		
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(new DefaultListableBeanFactory(), false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(annotationType));
		return scanner.findCandidateComponents(basePackage);
	}
	
	public static Set<Class<?>> getClassSet(final String basePackage, final Class<? extends Annotation> annotationType) throws ClassNotFoundException {
		
		Set<BeanDefinition> beanDefinitionSet = AcmeReflectionSpringUtil.getBeanDefinitionsSet(basePackage, annotationType);
		
		if (!(Objects.isNull(basePackage)) && (!beanDefinitionSet.isEmpty())  ) {
			Set<Class<?>> clazzSet = new HashSet<>();
			
			for (BeanDefinition beanDefiniton : beanDefinitionSet) {
				String className = beanDefiniton.getBeanClassName();
				Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);
				clazzSet.add(clazz);
			}
			
			return clazzSet;
		}
		
		return Collections.emptySet();
	}
	
	public static Set<Object> getInstancesSet(final String basePackage, final Class<? extends Annotation> annotationType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Set<Class<?>> classSet = AcmeReflectionSpringUtil.getClassSet(basePackage, annotationType);
		
		if (!(Objects.isNull(basePackage)) && (!classSet.isEmpty())  ) {
			Set<Object> instancesSet = new HashSet<>();
			
			for (Class<?> clazz : classSet) {
				Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
				Object[] argumentsConstructor = new Object[constructor.getParameterTypes().length];
				
				int i = 0;
				for (Class<?> parameterTypeConstructor : constructor.getParameterTypes()) {
					argumentsConstructor[i] = mock(parameterTypeConstructor);
					i++;
				}
				
				instancesSet.add(constructor.newInstance(argumentsConstructor));
			}
			
			return instancesSet;
		}
		
		return Collections.emptySet();
	}

}

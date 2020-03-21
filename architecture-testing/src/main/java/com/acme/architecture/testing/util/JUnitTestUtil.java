package com.acme.architecture.testing.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.acme.architecture.testing.util.constant.JUnitTestUtilConstant;

public final class JUnitTestUtil {

	protected JUnitTestUtil() {
		throw new IllegalStateException("JUnitTestUtil");
	}

	public static int checkSuperficialEnumCodeCoverage(Class<? extends Enum<?>> enumClass)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException {
		int cont = 0;

		for (Object o : (Object[]) enumClass.getMethod("values").invoke(null)) {
			enumClass.getMethod("valueOf", String.class).invoke(null, o.toString());
			cont++;
		}
		return cont;
	}

	private static void checkCommonValidationClassWellDefined(final Class<?> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Check Class
		assertTrue(JUnitTestUtilConstant.VALIDATION_CLASS_FINAL_MESSAGE, Modifier.isFinal(clazz.getModifiers()));

		// Check Constructors
		assertEquals(JUnitTestUtilConstant.VALIDATION_CLASS_ONE_CONSTRUCTOR_MESSAGE, 1,
				clazz.getDeclaredConstructors().length);
	}

	public static void checkConstantClassWellDefined(final Class<?> clazz)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		
		checkCommonValidationClassWellDefined(clazz);
		
		final Constructor<?> constructor = clazz.getDeclaredConstructor();

		// Depends || constructor.isAccessible()
		if (!Modifier.isPrivate(constructor.getModifiers())  ) {
			fail(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PRIVATE_MESSAGE);
		}

		constructor.setAccessible(true);
		constructor.newInstance();
		constructor.setAccessible(false);

		// Check Fields
		for (final Field field : clazz.getDeclaredFields()) {
			if (!Modifier.isStatic(field.getModifiers())) {
				fail(JUnitTestUtilConstant.VALIDATION_FIELD_NO_STATIC_MESSAGE
						.replace(JUnitTestUtilConstant.SUBSTITUTION_MARK, field.getName()));
			}
		}
	}

	public static void checkUtilClassWellDefined(final Class<?> clazz) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		checkCommonValidationClassWellDefined(clazz);
		
		final Constructor<?> constructor = clazz.getDeclaredConstructor();

		// Depends || constructor.isAccessible()
		if (!Modifier.isProtected(constructor.getModifiers())  ) {
			fail(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PROTECTED_MESSAGE);
		}

		//constructor.setAccessible(true);
		//constructor.newInstance();
		//constructor.setAccessible(false);

		// Check Methods
		for (final Method method : clazz.getMethods()) {
			if (!Modifier.isStatic(method.getModifiers()) && method.getDeclaringClass().equals(clazz)) {
				fail(JUnitTestUtilConstant.VALIDATION_METHOD_NO_STATIC_MESSAGE
						.replace(JUnitTestUtilConstant.SUBSTITUTION_MARK, method.getName()));
			}
		}
	}

}

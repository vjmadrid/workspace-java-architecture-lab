package com.acme.architecture.testing.junit.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.junit.constant.JUnitTestUtilConstant;
import com.acme.architecture.testing.junit.util.JUnitTestUtil;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageEmptyClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageNoFinalClass;
import com.acme.architecture.testing.util.example.clazz.util.ExampleCoverageUtilMoreConstructorClass;
import com.acme.architecture.testing.util.example.clazz.util.ExampleCoverageUtilNoStaticMethodClass;
import com.acme.architecture.testing.util.example.clazz.util.ExampleCoverageUtilPrivateConstructorClass;
import com.acme.architecture.testing.util.example.clazz.util.ExampleCoverageUtilValidClass;

public class JUnitTestUtilForUtilTest {
	
	@Test
	public void whenCallACheckUtilClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkUtilClassWellDefined(JUnitTestUtil.class);
	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithNoFinal_ThenReturnAssertionErrorInClass() throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageNoFinalClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CLASS_FINAL_MESSAGE, exception.getMessage());

	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithMoreConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageUtilMoreConstructorClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CLASS_ONE_CONSTRUCTOR_MESSAGE + " expected:<1> but was:<2>", exception.getMessage());

	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithProtectedConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageUtilPrivateConstructorClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PROTECTED_MESSAGE, exception.getMessage());

	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithEmpty_ThenReturnAssertionErrorInClass() throws Exception {
		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageEmptyClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PROTECTED_MESSAGE, exception.getMessage());
		
	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithPublicConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageUtilNoStaticMethodClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_METHOD_NO_STATIC_MESSAGE
				.replace(JUnitTestUtilConstant.SUBSTITUTION_MARK, "getValue"), exception.getMessage());

	}
	
	@Test
	public void whenCallACheckUtilClassWell_ThenReturnNothing() throws Exception {
		JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageUtilValidClass.class);
	}

}

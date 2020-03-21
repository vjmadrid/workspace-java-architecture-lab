package com.acme.architecture.testing.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.util.constant.JUnitTestUtilConstant;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageEmptyClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageMoreConstructorsClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageNoFinalClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageProtectedConstructorClass;
import com.acme.architecture.testing.util.example.clazz.util.ExampleCoverageUtilInvalidClass;

public class JUnitTestUtilForUtilTest {

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new JUnitTestUtil();
		});
	}

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
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageMoreConstructorsClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CLASS_ONE_CONSTRUCTOR_MESSAGE + " expected:<1> but was:<2>", exception.getMessage());

	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithProtectedConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageProtectedConstructorClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PRIVATE_MESSAGE, exception.getMessage());

	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithEmpty_ThenReturnNothing() throws Exception {
		JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageEmptyClass.class);
	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithPublicConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageUtilInvalidClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_METHOD_NO_STATIC_MESSAGE
				.replace(JUnitTestUtilConstant.SUBSTITUTION_MARK, "getValue"), exception.getMessage());

	}

}

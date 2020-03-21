package com.acme.architecture.testing.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.util.constant.JUnitTestUtilConstant;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageProtectedConstructorClass;
import com.acme.architecture.testing.util.example.clazz.constant.ExampleCoverageConstantClass;
import com.acme.architecture.testing.util.example.clazz.constant.ExampleCoverageConstantInvalidClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageEmptyClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageMoreConstructorsClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageNoFinalClass;

public class JUnitTestUtilForConstantTest {

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new JUnitTestUtil();
		});

	}

	@Test
	public void whenCallACheckConstantClassWellDefinedWithNoFinal_ThenReturnAssertionErrorInClass() throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageNoFinalClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CLASS_FINAL_MESSAGE, exception.getMessage());
	}

	@Test
	public void whenCallACheckConstantClassWellDefinedWithMoreConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageMoreConstructorsClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CLASS_ONE_CONSTRUCTOR_MESSAGE + " expected:<1> but was:<2>",
				exception.getMessage());
	}

	@Test
	public void whenCallACheckConstantClassWellDefinedWithProtectedConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageProtectedConstructorClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PRIVATE_MESSAGE, exception.getMessage());
	}

	@Test
	public void whenCallACheckConstantClassWellDefinedWithEmpty_ThenReturnNothing() throws Exception {
		JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageEmptyClass.class);
	}

	@Test
	public void whenCallACheckConstantClassWellDefinedWithPublicConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {

		AssertionError exception = assertThrows(AssertionError.class, () -> {
			JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageConstantInvalidClass.class);
		});

		assertEquals(JUnitTestUtilConstant.VALIDATION_FIELD_NO_STATIC_MESSAGE
				.replace(JUnitTestUtilConstant.SUBSTITUTION_MARK, "EXAMPLE_CONSTANT"), exception.getMessage());
	}

	@Test
	public void whenCallACheckConstantClassWellDefined_ThenReturnNothing() throws Exception {
		JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageConstantClass.class);
	}

}

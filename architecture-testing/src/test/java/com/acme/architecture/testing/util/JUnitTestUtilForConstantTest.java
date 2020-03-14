package com.acme.architecture.testing.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.acme.architecture.testing.util.constant.JUnitTestUtilConstant;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageProtectedConstructorClass;
import com.acme.architecture.testing.util.example.clazz.constant.ExampleCoverageConstantClass;
import com.acme.architecture.testing.util.example.clazz.constant.ExampleCoverageConstantInvalidClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageEmptyClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageMoreConstructorsClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageNoFinalClass;

public class JUnitTestUtilForConstantTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		exception.expect(IllegalStateException.class);
		
		new JUnitTestUtil();
	}

	public void whenCallACheckConstantClassWellDefinedWithNoFinal_ThenReturnAssertionErrorInClass() throws Exception {
    	exception.expect(AssertionError.class);
    	exception.expectMessage(JUnitTestUtilConstant.VALIDATION_CLASS_FINAL_MESSAGE);
    	
		JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageNoFinalClass.class);
	}
	
    @Test
	public void whenCallACheckConstantClassWellDefinedWithMoreConstructos_ThenReturnAssertionErrorInClass() throws Exception {
    	exception.expect(AssertionError.class);
    	exception.expectMessage(JUnitTestUtilConstant.VALIDATION_CLASS_ONE_CONSTRUCTOR_MESSAGE);
  	
    	JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageMoreConstructorsClass.class);
	}

	@Test
	public void whenCallACheckConstantClassWellDefinedWithProtectedConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {
		exception.expect(AssertionError.class);
		exception.expectMessage(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PRIVATE_MESSAGE);

		JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageProtectedConstructorClass.class);
	}
	
	@Test
	public void whenCallACheckConstantClassWellDefinedWithEmpty_ThenReturnNothing() throws Exception{
		JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageEmptyClass.class);
	}
	
	@Test
	public void whenCallACheckConstantClassWellDefinedWithPublicConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {
		exception.expect(AssertionError.class);
		exception.expectMessage(JUnitTestUtilConstant.VALIDATION_FIELD_NO_STATIC_MESSAGE
				.replace(JUnitTestUtilConstant.SUBSTITUTION_MARK, "EXAMPLE_CONSTANT"));

		JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageConstantInvalidClass.class);
	}
		
	@Test
	public void whenCallACheckConstantClassWellDefined_ThenReturnNothing() throws Exception{
		JUnitTestUtil.checkConstantClassWellDefined(ExampleCoverageConstantClass.class);
	}

}

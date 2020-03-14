package com.acme.architecture.testing.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.acme.architecture.testing.util.constant.JUnitTestUtilConstant;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageEmptyClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageMoreConstructorsClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageNoFinalClass;
import com.acme.architecture.testing.util.example.clazz.ExampleCoverageProtectedConstructorClass;
import com.acme.architecture.testing.util.example.clazz.util.ExampleCoverageUtilInvalidClass;

public class JUnitTestUtilForUtilTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		exception.expect(IllegalStateException.class);
		
		new JUnitTestUtil();
	}

	public void whenCallACheckUtilClassWellDefinedWithNoFinal_ThenReturnAssertionErrorInClass() throws Exception {
    	exception.expect(AssertionError.class);
    	exception.expectMessage(JUnitTestUtilConstant.VALIDATION_CLASS_FINAL_MESSAGE);
    	
		JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageNoFinalClass.class);
	}
	
    @Test
	public void whenCallACheckUtilClassWellDefinedWithMoreConstructos_ThenReturnAssertionErrorInClass() throws Exception {
    	exception.expect(AssertionError.class);
    	exception.expectMessage(JUnitTestUtilConstant.VALIDATION_CLASS_ONE_CONSTRUCTOR_MESSAGE);
  	
    	JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageMoreConstructorsClass.class);
	}

	@Test
	public void whenCallACheckUtilClassWellDefinedWithProtectedConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {
		exception.expect(AssertionError.class);
		exception.expectMessage(JUnitTestUtilConstant.VALIDATION_CONSTRUCTOR_NO_PRIVATE_MESSAGE);

		JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageProtectedConstructorClass.class);
	}
	
	@Test
	public void whenCallACheckUtilClassWellDefinedWithEmpty_ThenReturnNothing() throws Exception{
		JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageEmptyClass.class);
	}
	
	@Test
	public void whenCallACheckUtilClassWellDefinedWithPublicConstructor_ThenReturnAssertionErrorInClass()
			throws Exception {
		exception.expect(AssertionError.class);
		exception.expectMessage(JUnitTestUtilConstant.VALIDATION_METHOD_NO_STATIC_MESSAGE
				.replace(JUnitTestUtilConstant.SUBSTITUTION_MARK, "getValue"));

		JUnitTestUtil.checkUtilClassWellDefined(ExampleCoverageUtilInvalidClass.class);
	}
	

//		
//	@Test
//	public void whenCallACheckUtilClassWellDefined_ThenReturnNothing() throws Exception{
//		JUnitTestUtil.CheckUtilClassWellDefined(ExampleCoverageConstantClass.class);
//	}

}

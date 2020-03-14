package com.acme.architecture.testing.constant;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.acme.architecture.testing.util.JUnitTestUtil;
import com.acme.architecture.testing.util.constant.JUnitTestUtilConstant;

public class JUnitTestUtilConstantTest {
	
	@Test
	public void whenCallACheckConstantClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkConstantClassWellDefined(JUnitTestUtilConstant.class);
	}

}

package com.acme.architecture.testing.junit.constant;

public final class JUnitTestUtilConstant {

	private JUnitTestUtilConstant() {

	}

	public static final String SUBSTITUTION_MARK = "{}";
	
	public static final String VALIDATION_CLASS_FINAL_MESSAGE = "Class should be final";
	public static final String VALIDATION_CLASS_ONE_CONSTRUCTOR_MESSAGE = "Class should have one constructor";

	public static final String VALIDATION_CONSTRUCTOR_NO_PRIVATE_MESSAGE = "Constructor is not private";
	
	public static final String VALIDATION_CONSTRUCTOR_NO_PROTECTED_MESSAGE = "Constructor is not protected";

	public static final String VALIDATION_FIELD_NO_STATIC_MESSAGE = "There exists a non-static field : "+SUBSTITUTION_MARK;

	public static final String VALIDATION_METHOD_NO_STATIC_MESSAGE = "There exists a non-static method : "+SUBSTITUTION_MARK;
	
}

package com.acme.architecture.core.client.constant;


public final class GenericRestClientConstant {

	private GenericRestClientConstant() {

	}
	
	public static final String UUID_MESSAGE = "UUID = [{}] ";

	public static final String CALLING_MESSAGE = UUID_MESSAGE.concat("Calling {} method with URL: {}");

	public static final String ERROR_MSG = "Error response API functions - ";
	public static final String ERROR_MSG_LOG = UUID_MESSAGE.concat(
			"Error response API functions - URL = [{}] - Message = [{}] - HTTPStatus = [{}] - Exception Detail = [{}]");
	public static final String NONE_STATUS = "NONE";
	public static final String EXCEPTION_CLASS = "Exception class {}";
	
	public static final String ERROR_MSG_NO_CONTROLLED_ERROR = "Not controlled error - ";
	
	
}


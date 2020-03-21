package com.acme.architecture.core.util.example;

import org.springframework.stereotype.Component;

@Component
public class ExampleComponentWithParameterClass {
	
	private ExampleParameterClass param;
	
	public ExampleComponentWithParameterClass(ExampleParameterClass param) {
		super();
		this.param = param;
	}

	public String getValue() {
		return "test";
	}
	
	public String getParameterValue() {
		return param.getValue();
	}

	public ExampleParameterClass getParam() {
		return param;
	}

	public void setParam(ExampleParameterClass param) {
		this.param = param;
	}

}

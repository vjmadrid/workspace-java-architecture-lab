package com.acme.architecture.testing.example.factory;

public final class ExampleDataFactory {
	
	protected ExampleDataFactory() {
		throw new IllegalStateException("ExampleDataFactory");
	}
	
	public static String generateEntity() {
		return "";
	}

}

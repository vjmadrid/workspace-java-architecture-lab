package com.acme.architecture.testing.example.factory.dummy;

public final class DummyExampleDataFactory {
	
	protected DummyExampleDataFactory() {
		throw new IllegalStateException("DummyExampleDataFactory");
	}

	public static String generateEntityWithValue() {
		return "";
	}
	
}

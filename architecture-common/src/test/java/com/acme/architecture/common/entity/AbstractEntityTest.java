package com.acme.architecture.common.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.common.entity.example.ExampleCoverageEntitySerializableClass;
import com.acme.architecture.common.util.AcmeObjectUtil;

public class AbstractEntityTest {

	private ExampleCoverageEntitySerializableClass exampleCoverageEntitySerializableClass;

	@BeforeEach
	public void init() {

		exampleCoverageEntitySerializableClass = new ExampleCoverageEntitySerializableClass();
		exampleCoverageEntitySerializableClass.setIntCoverage(1);
	}

	@Test
	public void whenEntityExtend_thenImplementSerializable() throws Exception {

		byte[] serialized = AcmeObjectUtil.serialize(exampleCoverageEntitySerializableClass);
		Object deserialized = AcmeObjectUtil.deserialize(serialized);

		assertTrue(deserialized instanceof ExampleCoverageEntitySerializableClass);
		assertTrue(exampleCoverageEntitySerializableClass instanceof Serializable);
	}
}

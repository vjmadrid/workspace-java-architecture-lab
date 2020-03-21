package com.acme.architecture.common.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.common.entity.example.ExampleCoverageCustomEntitySerializableClass;
import com.acme.architecture.common.util.ObjectUtil;

public class AbstractCustomEntityTest {

	private ExampleCoverageCustomEntitySerializableClass exampleCoverageCustomEntitySerializableClass;

	@BeforeEach
	public void init() {

		exampleCoverageCustomEntitySerializableClass = new ExampleCoverageCustomEntitySerializableClass();
		exampleCoverageCustomEntitySerializableClass.setIntCoverage(1);
	}

	@Test
	public void whenEntityExtend_thenImplementSerializable() throws Exception {

		byte[] serialized = ObjectUtil.serialize(exampleCoverageCustomEntitySerializableClass);
		Object deserialized = ObjectUtil.deserialize(serialized);

		assertTrue(deserialized instanceof ExampleCoverageCustomEntitySerializableClass);
		assertTrue(exampleCoverageCustomEntitySerializableClass instanceof Serializable);
	}
}

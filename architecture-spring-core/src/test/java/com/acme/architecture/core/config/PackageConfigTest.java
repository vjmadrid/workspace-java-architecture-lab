package com.acme.architecture.core.config;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.core.constant.PackageConfigConstant;
import com.acme.architecture.core.dummy.factory.DummyPackageConfig;

public class PackageConfigTest {

	@BeforeEach
	public void init() {
		new DummyPackageConfig(DummyPackageConfig.DUMMY_PACKAGE);
	}

	@Test
	public void whenCallGetCommonPackage_thenReturnCommonPackage() {
		assertEquals(PackageConfigConstant.COMMON_PACKAGE, DummyPackageConfig.getCommonPackage());
	}

	@Test
	public void whenCallGetGenericPackage_thenReturnGenericPackage() {
		assertTrue(DummyPackageConfig.getGenericPackage().contains(DummyPackageConfig.DUMMY_PACKAGE));
	}

	@Test
	public void whenCallGetBasePackage_thenReturnBasePackage() {
		assertTrue(DummyPackageConfig.getBasePackage().contains(DummyPackageConfig.DUMMY_PACKAGE));
	}

	@Test
	public void whenCallGetEntityPackage_thenReturnEntityPackage() {
		assertTrue(DummyPackageConfig.getEntityPackage().contains(DummyPackageConfig.DUMMY_PACKAGE));
	}

	@Test
	public void whenCallGetDomainPackage_thenReturnDomainPackage() {
		assertTrue(DummyPackageConfig.getDomainPackage().contains(DummyPackageConfig.DUMMY_PACKAGE));
	}

	@Test
	public void whenCallGetRepositoryPackage_thenReturnRepositoryPackage() {
		assertTrue(DummyPackageConfig.getRepositoryPackage().contains(DummyPackageConfig.DUMMY_PACKAGE));
	}

	@Test
	public void whenCallGetServicePackage_thenReturnServicePackage() {
		assertTrue(DummyPackageConfig.getServicePackage().contains(DummyPackageConfig.DUMMY_PACKAGE));
	}

	@Test
	public void whenCallGetControllerPackage_thenReturnControllerPackage() {
		assertTrue(DummyPackageConfig.getControllerPackage().contains(DummyPackageConfig.DUMMY_PACKAGE));
	}

}

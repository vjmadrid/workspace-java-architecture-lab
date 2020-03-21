package com.acme.architecture.core.dummy.factory;

import com.acme.architecture.core.config.PackageConfig;

public class DummyPackageConfig extends PackageConfig {

	public static final String DUMMY_PACKAGE = "dummy.package";

	public DummyPackageConfig(String projectPackage) {
		super(projectPackage);
	}

}

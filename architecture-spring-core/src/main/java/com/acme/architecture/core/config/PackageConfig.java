package com.acme.architecture.core.config;

import com.acme.architecture.core.constant.PackageConfigConstant;

public abstract class PackageConfig {

	private static String projectPackage = "";

	protected PackageConfig(String projectPackage) {
		updateProjectPackage(projectPackage);
	}

	private static void updateProjectPackage(String projectPackage) {
		PackageConfig.projectPackage = projectPackage;
	}

	public static String getCommonPackage() {
		return PackageConfigConstant.COMMON_PACKAGE;
	}

	public static String getGenericPackage() {
		return PackageConfigConstant.COMMON_PACKAGE + "." + projectPackage;
	}

	public static String getBasePackage() {
		return getGenericPackage() + PackageConfigConstant.BASE_PACKAGE;
	}

	public static String getEntityPackage() {
		return getGenericPackage() + PackageConfigConstant.DEFAULT_ENTITY_PACKAGE;
	}

	public static String getDomainPackage() {
		return getGenericPackage() + PackageConfigConstant.DEFAULT_DOMAIN_PACKAGE;
	}

	public static String getRepositoryPackage() {
		return getGenericPackage() + PackageConfigConstant.DEFAULT_REPOSITORY_PACKAGE;
	}

	public static String getServicePackage() {
		return getGenericPackage() + PackageConfigConstant.DEFAULT_SERVICE_PACKAGE;
	}

	public static String getControllerPackage() {
		return getGenericPackage() + PackageConfigConstant.DEFAULT_CONTROLLER_PACKAGE;
	}
}

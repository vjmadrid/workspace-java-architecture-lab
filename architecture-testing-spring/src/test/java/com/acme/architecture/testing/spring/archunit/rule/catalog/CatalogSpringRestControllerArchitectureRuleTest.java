package com.acme.architecture.testing.spring.archunit.rule.catalog;

import com.acme.architecture.testing.spring.archunit.constant.TestingSpringArchUnitPackageConstant;
import com.acme.architecture.testing.spring.archunit.rule.catalog.CatalogSpringRestControllerArchitectureRule;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = TestingSpringArchUnitPackageConstant.PACKAGE_EXAMPLE, 
importOptions = { 
		ImportOption.DoNotIncludeArchives.class, 
		ImportOption.DoNotIncludeJars.class 
}
)
//Includes test classes
public class CatalogSpringRestControllerArchitectureRuleTest {
	
	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_be_in_spring_rest_controller_package = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_be_in_spring_rest_controller_package;

	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_have_names_ending_with_the_word_rest_controller = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_have_names_ending_with_the_word_rest_controller;
	
	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_be_annotated_with_rest_controller = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_be_annotated_with_rest_controller;

	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_be_public = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_be_public;

	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_depend_on_spring_service = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_depend_on_spring_service;

	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_no_depend_on_spring_rest_controller = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_no_depend_on_spring_rest_controller;

	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_no_depend_on_spring_repository = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_no_depend_on_spring_repository;

	@ArchTest
	public static final ArchRule spring_rest_controller_classes_should_no_depend_on_spring_service_impl = CatalogSpringRestControllerArchitectureRule.spring_rest_controller_classes_should_no_depend_on_spring_service_impl;

}

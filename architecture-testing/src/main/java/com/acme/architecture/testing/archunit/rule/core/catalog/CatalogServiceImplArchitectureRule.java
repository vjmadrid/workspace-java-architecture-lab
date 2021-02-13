package com.acme.architecture.testing.archunit.rule.core.catalog;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.acme.architecture.testing.archunit.condition.ImplementInterfaceWithSameNameArchitectureCondition;
import com.acme.architecture.testing.archunit.constant.ArchUnitNameConstant;
import com.acme.architecture.testing.archunit.constant.ArchUnitPackageConstant;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class CatalogServiceImplArchitectureRule {
	
	@ArchTest
	public static final ArchRule service_impl_classes_should_be_in_service_impl_package = 
		    classes()
		    .that().haveSimpleNameEndingWith(ArchUnitNameConstant.SUFFIX_NAME_SERVICE_IMPL_CLASS)
		    .should().resideInAnyPackage(ArchUnitPackageConstant.RESIDE_FINAL_PACKAGE_SERVICE_IMPL_CLASS);
	
	@ArchTest
	public static final ArchRule service_impl_classes_should_have_names_ending_with_the_word_service_impl = 
		    classes()
		    .that().resideInAnyPackage(ArchUnitPackageConstant.RESIDE_FINAL_PACKAGE_SERVICE_IMPL_CLASS)
		    .should().haveSimpleNameEndingWith(ArchUnitNameConstant.SUFFIX_NAME_SERVICE_IMPL_CLASS);
	
	@ArchTest
	public static final ArchRule service_impl_classes_classes_should_be_public = 
		    classes()
		    .that().resideInAPackage(ArchUnitPackageConstant.RESIDE_FINAL_PACKAGE_SERVICE_IMPL_CLASS)
		    .should().bePublic();
	
	@ArchTest
	public static final ArchRule service_impl_should_implement_service = 
		    classes()
		    .that().resideInAPackage(ArchUnitPackageConstant.RESIDE_FINAL_PACKAGE_SERVICE_IMPL_CLASS)
		    .should(new ImplementInterfaceWithSameNameArchitectureCondition());
	
}



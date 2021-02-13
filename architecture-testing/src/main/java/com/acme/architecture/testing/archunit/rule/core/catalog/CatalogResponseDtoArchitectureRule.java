package com.acme.architecture.testing.archunit.rule.core.catalog;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import java.io.Serializable;

import com.acme.architecture.testing.archunit.constant.ArchUnitNameConstant;
import com.acme.architecture.testing.archunit.constant.ArchUnitPackageConstant;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class CatalogResponseDtoArchitectureRule {
	
	@ArchTest
	public static final ArchRule response_dto_classes_should_be_in_response_dto_package = 
		    classes()
		    .that().haveSimpleNameEndingWith(ArchUnitNameConstant.SUFFIX_NAME_RESPONSE_DTO_CLASS)
		    .and().haveSimpleNameNotStartingWith(ArchUnitNameConstant.PREFIX_NAME_DUMMY_CLASS)
		    .should().resideInAPackage(ArchUnitPackageConstant.RESIDE_PACKAGE_RESPONSE_DTO_CLASS);

	@ArchTest
	public static final ArchRule response_dto_classes_should_be_public = 
		    classes()
		    .that().resideInAPackage(ArchUnitPackageConstant.RESIDE_PACKAGE_RESPONSE_DTO_CLASS)
		    .should().bePublic();
	
	@ArchTest
	public static final ArchRule response_dto_classes_should_implements_serializable = 
			classes()
			    .that().resideInAPackage(ArchUnitPackageConstant.RESIDE_PACKAGE_RESPONSE_DTO_CLASS)
			    .should().implement(Serializable.class);
}



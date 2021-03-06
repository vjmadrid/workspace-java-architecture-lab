package com.acme.architecture.event.driven.archunit;

import com.acme.architecture.testing.archunit.rule.core.group.CatalogCoreGroupArchitectureRule;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;

@AnalyzeClasses(packages = "com.acme.architecture.event.driven", 
	importOptions = { 
			ImportOption.DoNotIncludeTests.class,
			ImportOption.DoNotIncludeJars.class, 
			ImportOption.DoNotIncludeArchives.class 
	}
)
public class CheckArchitectureTest {
	
	@ArchTest
	public static final ArchRules global_core_architecture = ArchRules.in(CatalogCoreGroupArchitectureRule.class);
	
}

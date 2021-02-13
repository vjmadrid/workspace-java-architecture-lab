package com.acme.architecture.testing.api.support;

import java.io.IOException;

public class JsonSpringfoxSolutionResolverSupport {

	protected JsonSpringfoxSolutionResolverSupport() {
		throw new IllegalStateException("JsonSpringfoxSolutionResolver");
	}

	public static String applyAllJsonAdapterSolutions(String json) throws IOException {

		return new JsonSpringfoxResolverBuilderSupport(json).setDefaultMaxItems().setDefaultDateTimeConfig()
				.setIdsWithAditionalParameters().setPatternSortProperty().setAdditionalPropertiesToFalse().getJson();
	}


	public static String applyAllJsonAdapterWso2Solutions(String json) throws IOException {
		return new JsonSpringfoxResolverBuilderSupport(json).setDefaultMaxItems().setDefaultDateTimeConfig()
				.setIdsWithAditionalParameters().setPatternSortProperty().getJson();
	}

}

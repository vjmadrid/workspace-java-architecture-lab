package com.acme.architecture.testing.api.support;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.api.util.APITestUtil;
import com.jayway.jsonpath.JsonPath;

public class JsonSpringfoxSolutionResolverSupportTest {

	private static final String FILE_EXAMPLE = "/tech-api-project.json";

	private static final String JSON_API_ORIGIN = getEventJson(FILE_EXAMPLE);
	
	@BeforeEach
	public void setUp() {

	}

	private static String getEventJson(String file) {
		return APITestUtil.inputStreamToString(JsonSpringfoxSolutionResolverSupportTest.class.getResourceAsStream(file));
	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		
		assertThrows(IllegalStateException.class, ()->{
			new JsonSpringfoxSolutionResolverSupport();
		});

	}

	@Test
	public void whenCallAApplyAllJsonAdapterSolutions_thenReturnJsonAdapted()
			throws IOException, IllegalAccessException {

		// Given IdsWithAditionalParameters
		String jsonOriginDataIdsWithAditionalParameters = JsonPath.parse(JSON_API_ORIGIN)
				.read("$['paths']['/projects/list']['get']['parameters']").toString();

		assertFalse(jsonOriginDataIdsWithAditionalParameters.contains("\"pattern\":\"[0-9]*,\""));
		assertFalse(jsonOriginDataIdsWithAditionalParameters.contains("\"maxLength\":2147483647"));

		// Given SetPatternSortProperty
		String jsonOriginDataSetPatternSortProperty = JsonPath.parse(JSON_API_ORIGIN)
				.read("$['paths']['/projects/page']['get']['parameters'][?(@.name=='sortProperty')]").toString();
		
		assertFalse(jsonOriginDataSetPatternSortProperty.contains("\"pattern\":\"[a-zA-Z]+\""));

		// When
		String JSON_API_RESULT = JsonSpringfoxSolutionResolverSupport.applyAllJsonAdapterSolutions(JSON_API_ORIGIN);

		// Then IdsWithAditionalParameters
		String jsonResultDataIdsWithAditionalParameters = JsonPath.parse(JSON_API_RESULT)
				.read("$['paths']['/projects/list']['get']['parameters']").toString();

		assertTrue(jsonResultDataIdsWithAditionalParameters.contains("\"pattern\":\"[0-9]*,\""));
		assertTrue(jsonResultDataIdsWithAditionalParameters.contains("\"maxLength\":2147483647"));
		
		// Then SetPatternSortProperty
		String jsonResultData = JsonPath.parse(JSON_API_RESULT)
				.read("$['paths']['/projects/page']['get']['parameters'][?(@.name=='sortProperty')]").toString();
		
		assertTrue(jsonResultData.contains("\"pattern\":\"[a-zA-Z]+\""));
	}

}

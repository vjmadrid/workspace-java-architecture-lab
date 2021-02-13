package com.acme.architecture.testing.api.support;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.testing.api.util.APITestUtil;
import com.jayway.jsonpath.JsonPath;

public class JsonSpringfoxResolverBuilderSupportTest {

	private static final String FILE_EXAMPLE = "/tech-api-project.json";

	private static final String JSON_API_ORIGIN = getEventJson(FILE_EXAMPLE);

	@BeforeEach
	public void setUp() {

	}

	private static String getEventJson(String file) {
		return APITestUtil.inputStreamToString(JsonSpringfoxResolverBuilderSupportTest.class.getResourceAsStream(file));
	}

	@Test
	public void whenCallASetIdsWithAditionalParameters_thenReturnJsonAdapted()
			throws IOException, IllegalAccessException {

		// Given
		String jsonOriginData = JsonPath.parse(JSON_API_ORIGIN)
				.read("$['paths']['/projects/list']['get']['parameters']").toString();

		assertFalse(jsonOriginData.contains("\"pattern\":\"[0-9]*,\""));
		assertFalse(jsonOriginData.contains("\"maxLength\":2147483647"));

		// When
		String JSON_API_RESULT = new JsonSpringfoxResolverBuilderSupport(JSON_API_ORIGIN)
				.setIdsWithAditionalParameters().getJson();

		// Then
		String jsonResultData = JsonPath.parse(JSON_API_RESULT)
				.read("$['paths']['/projects/list']['get']['parameters']").toString();

		assertTrue(jsonResultData.contains("\"pattern\":\"[0-9]*,\""));
		assertTrue(jsonResultData.contains("\"maxLength\":2147483647"));
	}

	@Test
	public void whenCallASetDefaultMaxItems_thenReturnJsonAdapted() throws IOException, IllegalAccessException {

		// Given
		String jsonOriginData = JsonPath.parse(JSON_API_ORIGIN)
				.read("$['paths']['/projects']['get']['responses']['200']['content']['*/*']['schema']").toString();

		assertFalse(jsonOriginData.contains("\"maxLength\":1000"));

		// When
		String JSON_API_RESULT = new JsonSpringfoxResolverBuilderSupport(JSON_API_ORIGIN).setDefaultMaxItems()
				.getJson();

		// Then
		String jsonResultData = JsonPath.parse(JSON_API_RESULT)
				.read("$['paths']['/projects']['get']['responses']['200']['content']['*/*']['schema']").toString();

		assertTrue(jsonResultData.contains("maxItems=2147483647"));
	}

	@Test
	public void whenCallAsetPatternSortProperty_thenReturnJsonAdapted() throws IOException, IllegalAccessException {

		// Given
		String jsonOriginData = JsonPath.parse(JSON_API_ORIGIN)
				.read("$['paths']['/projects/page']['get']['parameters'][?(@.name=='sortProperty')]").toString();

		// When
		String JSON_API_RESULT = new JsonSpringfoxResolverBuilderSupport(JSON_API_ORIGIN).setPatternSortProperty()
				.getJson();

		assertFalse(jsonOriginData.contains("\"pattern\":\"[a-zA-Z]+\""));

		// Then
		String jsonResultData = JsonPath.parse(JSON_API_RESULT)
				.read("$['paths']['/projects/page']['get']['parameters'][?(@.name=='sortProperty')]").toString();

		assertTrue(jsonResultData.contains("\"pattern\":\"[a-zA-Z]+\""));
	}

	@Test
	public void whenCallASetDefaultDateTimeConfig_thenReturnJsonAdapted() throws IOException, IllegalAccessException {

		// Given
		String jsonOriginData = JsonPath.parse(JSON_API_ORIGIN)
				.read("$['components']['schemas']['AuditSearchRequest']['properties']['dateFrom']").toString();

		// When
		String JSON_API_RESULT = new JsonSpringfoxResolverBuilderSupport(JSON_API_ORIGIN).setDefaultDateTimeConfig()
				.getJson();

		assertFalse(jsonOriginData.contains("maxLength=21"));

		// Then
		String jsonResultData = JsonPath.parse(JSON_API_RESULT)
				.read("$['components']['schemas']['AuditSearchRequest']['properties']['dateFrom']").toString();

		assertTrue(jsonResultData.contains("maxLength=21"));
	}

	@Test
	public void whenCallSetAdditionalPropertiesToFalse_thenReturnJsonAdapted()
			throws IOException, IllegalAccessException {

		// Given
		String jsonOriginData = JsonPath.parse(JSON_API_ORIGIN).read("$['components']['schemas']['AuditSearchRequest']")
				.toString();

		assertFalse(jsonOriginData.contains("\"additionalProperties\":false"));

		// When
		String JSON_API_RESULT = new JsonSpringfoxResolverBuilderSupport(JSON_API_ORIGIN)
				.setAdditionalPropertiesToFalse().getJson();

		// Then
		String jsonResultData = JsonPath.parse(JSON_API_RESULT).read("$['components']['schemas']['AuditSearchRequest']")
				.toString();

		assertTrue(jsonResultData.contains("additionalProperties=false"));

	}

}

package com.acme.architecture.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.acme.architecture.common.util.example.ExampleJsonClass;

public class AcmeRestUtilTest {

	private String EXAMPLE_JSON = "{" + System.lineSeparator() + "  \"field1\" : \"1\"," + System.lineSeparator()
			+ "  \"field2\" : 2" + System.lineSeparator() + "}";

	private String EXAMPLE_YAML = "---" + System.lineSeparator() + "field1: \"1\"" + System.lineSeparator()
			+ "field2: 2" + System.lineSeparator();

	private String EMPTY_RESULT = "";

	private String NEW_LINE_WINDOWS = "\r\n";

	private String NEW_LINE_LINUX = "\n";
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new AcmeRestUtil();
		});
	}

	@Test
	public void whenCallACovertToJsonResponse_thenReturnObjectAsJson() throws Exception {
		assertEquals(EXAMPLE_JSON, AcmeRestUtil.covertToJsonResponse(new ExampleJsonClass()));
	}

	@Test
	public void whenCallAAsYamlWithNull_thenReturnYaml() throws Exception {
		String resultJSON = AcmeRestUtil.covertJsonToYamlResponse(null);

		assertEquals(EMPTY_RESULT, resultJSON);
	}

	@Test
	public void whenCallAAsYamlWithEmpty_thenReturnYaml() throws Exception {
		String resultJSON = AcmeRestUtil.covertJsonToYamlResponse(EMPTY_RESULT);

		assertEquals(EMPTY_RESULT, resultJSON);
	}

	@Test
	public void whenCallAAsYaml_thenReturnYaml() throws Exception {

		String resultJSON = AcmeRestUtil
				.covertJsonToYamlResponse(EXAMPLE_JSON.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX));

		assertEquals(EXAMPLE_YAML.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX), resultJSON);
	}

}

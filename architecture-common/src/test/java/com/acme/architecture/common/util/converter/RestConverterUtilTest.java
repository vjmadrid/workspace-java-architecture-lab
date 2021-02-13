package com.acme.architecture.common.util.converter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RestConverterUtilTest {

	private String EXAMPLE_JSON = "{" + System.lineSeparator() + "  \"field1\" : \"1\"," + System.lineSeparator()
			+ "  \"field2\" : 2" + System.lineSeparator() + "}";

	private String EXAMPLE_YAML = "---" + System.lineSeparator() + "field1: \"1\"" + System.lineSeparator()
			+ "field2: 2" + System.lineSeparator();

	private String EMPTY_RESULT = "";

	private String NEW_LINE_WINDOWS = "\r\n";

	private String NEW_LINE_LINUX = "\n";
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, ()->{
			new RestConverterUtil();
		});
	}

	@Test
	public void whenCallACovertToJsonResponse_thenReturnObjectAsJson() throws Exception {
		assertEquals(EXAMPLE_JSON, RestConverterUtil.covertToJsonResponse(new JsonTest()));
	}

	@Test
	public void whenCallAAsYamlWithNull_thenReturnYaml() throws Exception {
		String resultJSON = RestConverterUtil.covertJsonToYamlResponse(null);

		assertEquals(EMPTY_RESULT, resultJSON);
	}

	@Test
	public void whenCallAAsYamlWithEmpty_thenReturnYaml() throws Exception {
		String resultJSON = RestConverterUtil.covertJsonToYamlResponse(EMPTY_RESULT);

		assertEquals(EMPTY_RESULT, resultJSON);
	}

	@Test
	public void whenCallAAsYaml_thenReturnYaml() throws Exception {

		String resultJSON = RestConverterUtil
				.covertJsonToYamlResponse(EXAMPLE_JSON.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX));

		assertEquals(EXAMPLE_YAML.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX), resultJSON);
	}

	protected class JsonTest {

		private String field1 = "1";
		private Integer field2 = 2;

		public String getField1() {
			return field1;
		}

		public Integer getField2() {
			return field2;
		}
	}
}


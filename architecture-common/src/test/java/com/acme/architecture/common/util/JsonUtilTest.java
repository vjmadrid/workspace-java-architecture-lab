package com.acme.architecture.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.acme.architecture.common.util.example.ExampleJsonClass;
import com.acme.architecture.testing.util.JUnitTestUtil;

public final class JsonUtilTest {

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
			new JsonUtil();
		});
	}
	
	@Test
	public void whenCallACheckUtilClassWellDefined()
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		JUnitTestUtil.checkUtilClassWellDefined(JsonUtil.class);
	}
	
	@Test
	public void whenCallAConvertObjectToJsonWithNull_thenReturnNull() throws Exception {
		assertNull(JsonUtil.convertObjectToJson(null));
	}

	@Test
	public void whenCallAConvertObjectToJson_thenReturnObjectAsJson() throws Exception {
		assertEquals(EXAMPLE_JSON, JsonUtil.convertObjectToJson(new ExampleJsonClass()));
	}

	@Test
	public void whenCallAConvertJsonToYamlWithNull_thenReturnNull() throws Exception {
		String resultJSON = JsonUtil.convertJsonToYaml(null);

		assertNull(resultJSON);
	}

	@Test
	public void whenCallAConvertJsonToYamlWithEmpty_thenReturnNull() throws Exception {
		String resultJSON = JsonUtil.convertJsonToYaml(EMPTY_RESULT);

		assertNull(resultJSON);
	}

	@Test
	public void whenCallAConvertJsonToYaml_thenReturnYaml() throws Exception {
		String resultJSON = JsonUtil
				.convertJsonToYaml(EXAMPLE_JSON.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX));

		assertEquals(EXAMPLE_YAML.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX), resultJSON);
	}
	
	@Test
	public void whenCallAConvertJsonToObjectWithNull_thenReturnNull() throws Exception {
		assertNull(JsonUtil.convertJsonToObject(null, ExampleJsonClass.class));
	}
	
	@Test
	public void whenCallAConvertJsonToObjectWithEmpty_thenReturnNull() throws Exception {
		assertNull(JsonUtil.convertJsonToObject("", ExampleJsonClass.class));
	}
	
	@Test
	public void whenCallAConvertJsonToObjectWithNullClass_thenReturnNull() throws Exception {
		String resultJSON = JsonUtil
				.convertJsonToYaml(EXAMPLE_JSON.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX));
		
		assertNull(JsonUtil.convertJsonToObject(resultJSON, null));
	}
	
	@Test
	public void whenCallAConvertJsonToObject_thenReturnObject() throws Exception {
		String resultJSON = JsonUtil
				.convertJsonToYaml(EXAMPLE_JSON.replaceAll(NEW_LINE_WINDOWS, NEW_LINE_LINUX));
		
		Class<?> resultObject = JsonUtil.convertJsonToObject(resultJSON, ExampleJsonClass.class);
		
		assertNull(resultObject);
	}

}

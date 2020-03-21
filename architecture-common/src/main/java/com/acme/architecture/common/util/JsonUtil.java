package com.acme.architecture.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class JsonUtil {

	protected JsonUtil() {
		throw new IllegalStateException("AcmeRestUtil");
	}

	public static String convertObjectToJson(Object object) throws JsonProcessingException {

		if (object != null) {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(object);
		}
		
		return null;
	}

	public static String convertJsonToYaml(String jsonString) throws IOException {

		if ((jsonString == null) || "".equals(jsonString)) {
			return null;
		}

		// parse JSON
		JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
		// save it as YAML
		return new YAMLMapper().writeValueAsString(jsonNodeTree);
	}
	
	public static Class<?> convertJsonToObject(final String jsonString , Class<?> valueClass) throws JsonParseException, JsonMappingException, IOException {
		
		if (((jsonString == null) || "".equals(jsonString)) || (valueClass == null)) {
			return null;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		return (Class<?>) objectMapper.readValue(jsonString, valueClass); 
	}

}

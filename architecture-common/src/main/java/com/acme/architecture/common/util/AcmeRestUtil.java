package com.acme.architecture.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class AcmeRestUtil {

	protected AcmeRestUtil() {
		throw new IllegalStateException("AcmeRestUtil");
	}

	public static String covertToJsonResponse(Object object) throws JsonProcessingException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(object);
	}

	public static String covertJsonToYamlResponse(String jsonString) throws IOException {

		if ((jsonString == null) || "".equals(jsonString)) {
			return "";
		}

		// parse JSON
		JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
		// save it as YAML
		return new YAMLMapper().writeValueAsString(jsonNodeTree);
	}

}

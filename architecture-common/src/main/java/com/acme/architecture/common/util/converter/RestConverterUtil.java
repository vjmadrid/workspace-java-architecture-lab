package com.acme.architecture.common.util.converter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class RestConverterUtil {

	protected RestConverterUtil() {
		throw new IllegalStateException("RestUtil");
	}

	public static String covertToJsonResponse(Object object) throws JsonProcessingException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(object);
	}

	public static String covertJsonToYamlResponse(String jsonString) throws IOException {

		if ((jsonString == null) || StringUtils.EMPTY.equals(jsonString)) {
			return StringUtils.EMPTY;
		}

		// parse JSON
		JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
		// save it as YAML
		return new YAMLMapper().writeValueAsString(jsonNodeTree);
	}

}

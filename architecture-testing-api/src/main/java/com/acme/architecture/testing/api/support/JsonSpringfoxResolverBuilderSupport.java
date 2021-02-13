package com.acme.architecture.testing.api.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.architecture.testing.api.constant.JsonAPILocatorConstant;
import com.acme.architecture.testing.api.constant.JsonAPIValueConstant;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class JsonSpringfoxResolverBuilderSupport extends JsonSpringfoxResolverToolsSupport {

	private Logger logger = LoggerFactory.getLogger(JsonSpringfoxResolverBuilderSupport.class);

	public JsonSpringfoxResolverBuilderSupport(String json) throws IOException {

		factory = new JsonFactory();
		jsonNodeFactory = new JsonNodeFactory(true);
		mapper = new ObjectMapper();

		logger.info("Updating springfox JSON");
		logger.info(json);

		fullJsonNode = mapper.readTree(json);

		this.json = mapper.writeValueAsString(fullJsonNode);

		logger.info(this.json);
		logger.info("Updated springfox JSON");

	}

	public JsonSpringfoxResolverBuilderSupport setDefaultDateTimeConfig() throws JsonProcessingException {

		String searchNodeKey = "format";
		String searchNodeValue = "date-time";

		String newNodeKey = "maxLength";
		Integer newNodeValue = 21;

		searchNodeByKeyAndValue(searchNodeKey, searchNodeValue, fullJsonNode.elements());

		Map<String, Object> newNodesMap = new HashMap<>();
		newNodesMap.put(newNodeKey, newNodeValue);

		addValueIntoNode(newNodesMap);

		this.json = mapper.writeValueAsString(fullJsonNode);
		return this;
	}

	public JsonSpringfoxResolverBuilderSupport setDefaultMaxItems() throws JsonProcessingException {

		String searchNodeKey = "items";

		String newNodeKey = "maxItems";
		Integer newNodeValue = Integer.MAX_VALUE;

		searchNodeByKey(searchNodeKey, fullJsonNode.elements());

		Map<String, Object> newNodesMap = new HashMap<>();
		newNodesMap.put(newNodeKey, newNodeValue);

		addValueIntoNode(newNodesMap);

		this.json = mapper.writeValueAsString(fullJsonNode);
		return this;
	}

	public JsonSpringfoxResolverBuilderSupport setIdsWithAditionalParameters() throws JsonProcessingException {

		String searchNodeKey = "name";
		String searchNodeValue = "ids";

		searchNodeByKeyAndValue(searchNodeKey, searchNodeValue, fullJsonNode.elements());

		Map<String, Object> newNodesMap = new HashMap<>();
		newNodesMap.put(JsonAPILocatorConstant.LOCATOR_KEY_PATTERN, JsonAPIValueConstant.VALUE_KEY_PATTERN);
		newNodesMap.put(JsonAPILocatorConstant.LOCATOR_KEY_MAX_LENGTH, Integer.MAX_VALUE);

		addValueIntoNodeSchema(newNodesMap);

		this.json = mapper.writeValueAsString(fullJsonNode);
		return this;
	}

	public JsonSpringfoxResolverBuilderSupport setAdditionalPropertiesToFalse() throws JsonProcessingException {

		String searchNodeKey = "type";
		String searchNodeValue = "object";

		searchNodeByKeyAndValue(searchNodeKey, searchNodeValue, fullJsonNode.elements());

		Map<String, Object> newNodesMap = new HashMap<>();
		newNodesMap.put(JsonAPILocatorConstant.LOCATOR_KEY_ADDITIONAL_PROPERTIES, Boolean.FALSE);

		addValueIntoNode(newNodesMap);

		this.json = mapper.writeValueAsString(fullJsonNode);
		return this;
	}

	public JsonSpringfoxResolverBuilderSupport setPatternSortProperty() throws JsonProcessingException {

		String searchNodeKey = JsonAPILocatorConstant.LOCATOR_KEY_NAME;
		String searchNodeValue = JsonAPILocatorConstant.LOCATOR_KEY_SORT_PROPERTY;

		searchNodeByKeyAndValue(searchNodeKey, searchNodeValue, fullJsonNode.elements());

		Map<String, Object> newNodesMap = new HashMap<>();
		newNodesMap.put(JsonAPILocatorConstant.LOCATOR_KEY_PATTERN, JsonAPIValueConstant.VALUE_ALPHABETIC_PATTERN);

		addValueIntoNodeSchema(newNodesMap);

		this.json = mapper.writeValueAsString(fullJsonNode);
		return this;
	}

}

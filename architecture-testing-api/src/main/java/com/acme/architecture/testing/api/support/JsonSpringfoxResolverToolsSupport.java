package com.acme.architecture.testing.api.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonSpringfoxResolverToolsSupport {

	protected JsonFactory factory;

	protected JsonNodeFactory jsonNodeFactory;

	protected ObjectMapper mapper;

	protected JsonParser parser;

	protected JsonNode jsonNode;

	protected JsonNode fullJsonNode;

	protected String json;

	private List<JsonNode> nodeSearchList;

	public String getJson() {
		return this.json;
	}

	protected void searchNodeByKey(String searchNodeKey, Iterator<JsonNode> iteratorJsonNode) {

		searchNodeByKey(searchNodeKey, iteratorJsonNode, true);
	}

	private void searchNodeByKey(String searchNodeKey, Iterator<JsonNode> iteratorJsonNode,
			boolean initNodeSearchList) {

		if (initNodeSearchList || nodeSearchList == null) {
			nodeSearchList = new ArrayList<>();
		}

		while (iteratorJsonNode.hasNext()) {

			JsonNode node = iteratorJsonNode.next();

			if (node.isContainerNode()) {

				if (nodeContainsKey(node.iterator(), searchNodeKey)) {
					nodeSearchList.addAll(node.findParents(searchNodeKey));
				}

				searchNodeByKey(searchNodeKey, node.elements(), false);
			}

		}
	}

	protected void searchNodeByKeyAndValue(String searchNodeKey, String searchNodeValue,
			Iterator<JsonNode> iteratorJsonNode) {

		searchNodeByKey(searchNodeKey, iteratorJsonNode, true);
		filterNodeByKeyAndValue(searchNodeKey, searchNodeValue);
	}

	protected void addValueIntoNode(Map<String, Object> newNodesMap) {

		nodeSearchList.forEach(node -> newNodesMap.forEach((k, v) -> createNewNode((ObjectNode) node, k, v)));
	}

	protected void addValueIntoNodeSchema(Map<String, Object> newNodesMap) {

		nodeSearchList
				.forEach(node -> newNodesMap.forEach((k, v) -> createNewNodeIntoNodeSchema((ObjectNode) node, k, v)));
	}

	private ObjectNode createNewNode(ObjectNode objectNode, String k, Object v) {

		ObjectNode addObjectNode = new ObjectNode(jsonNodeFactory);
		putNodeValue(objectNode, k, v);
		return addObjectNode;
	}

	private ObjectNode createNewNodeIntoNodeSchema(ObjectNode objectNode, String k, Object v) {

		ObjectNode addObjectNode = new ObjectNode(jsonNodeFactory);
		JsonNode shcemaNode = objectNode.findValue("schema");
		putNodeValue((ObjectNode) shcemaNode, k, v);
		return addObjectNode;
	}

	private void filterNodeByKeyAndValue(String searchNodeKey, String searchNodeValue) {

		List<JsonNode> removeJsonNodeList = new ArrayList<>();

		for (JsonNode node : nodeSearchList) {

			if (!node.get(searchNodeKey).asText().equals(searchNodeValue)) {
				removeJsonNodeList.add(node);
			}
		}

		nodeSearchList.removeAll(removeJsonNodeList);
	}

	private void putNodeValue(ObjectNode objectNode, String k, Object v) {

		if (v instanceof Integer) {
			objectNode.put(k, (Integer) v);
		}

		if (v instanceof Short) {
			objectNode.put(k, (Short) v);
		}

		if (v instanceof String) {
			objectNode.put(k, (String) v);
		}
		if (v instanceof Boolean) {
			objectNode.put(k, (Boolean) v);
		}
	}

	private boolean nodeContainsKey(Iterator<JsonNode> iteratorJsonNode, String locatorKey) {

		while (iteratorJsonNode.hasNext()) {

			JsonNode node = iteratorJsonNode.next();

			if (node.get(locatorKey) != null) {
				return true;
			}
		}

		return false;
	}

}

package com.acme.architecture.event.driven.converter;

import java.io.IOException;

import com.acme.architecture.event.driven.entity.GenericEvent;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum GenericEventConverter {

	INSTANCE;
	
	public String fromObjectToJSON(Object object, boolean activePretty){
		try {
				if (activePretty) {
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
				} else {
					return new ObjectMapper().writeValueAsString(object);
				}
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static GenericEvent fromJSONToObject(final String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, GenericEvent.class); 
	}
	
}

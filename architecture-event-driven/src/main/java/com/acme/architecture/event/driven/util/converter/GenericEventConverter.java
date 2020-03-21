package com.acme.architecture.event.driven.util.converter;

import java.io.IOException;

import com.acme.architecture.common.util.converter.JsonConverter;
import com.acme.architecture.event.driven.entity.GenericEvent;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public final class GenericEventConverter {

	protected GenericEventConverter() {
		throw new IllegalStateException("GenericEventConverter");
	}
	
	public static String convertGenericEventToJson(GenericEvent event) throws JsonProcessingException{
	    return JsonConverter.convertObjectToJsonDefault(event);
	}
	
	public static GenericEvent fromJsonToGenericEvent(final String jsonString) throws JsonParseException, JsonMappingException, IOException  {
		return (GenericEvent) JsonConverter.convertJsonToObject(jsonString, GenericEvent.class);	
	}
	
}
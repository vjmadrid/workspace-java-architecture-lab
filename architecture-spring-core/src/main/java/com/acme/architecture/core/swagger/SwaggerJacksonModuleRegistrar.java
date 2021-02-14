package com.acme.architecture.core.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.util.ReferenceSerializationConfigurer;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;

public class SwaggerJacksonModuleRegistrar implements JacksonModuleRegistrar {

	@Override
	public void maybeRegisterModule(ObjectMapper objectMapper) {
		ReferenceSerializationConfigurer.serializeAsComputedRef(objectMapper);
	}
}


package com.acme.architecture.core.swagger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import io.swagger.models.RefModel;
import io.swagger.models.RefPath;
import io.swagger.models.RefResponse;
import io.swagger.models.parameters.RefParameter;
import io.swagger.models.properties.RefProperty;

public class SwaggerJacksonModuleRegistrarTest {

	private SwaggerJacksonModuleRegistrar swaggerJacksonModuleRegistrar = new SwaggerJacksonModuleRegistrar();

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void whenCallaybeRegisterModule_thenReferenceSerializationConfigurerAsComputedRef() {

		SerializationConfig serializationConfig = objectMapper.getSerializationConfig();

		assertNull(serializationConfig.findMixInClassFor(RefModel.class));
		assertNull(serializationConfig.findMixInClassFor(RefModel.class));
		assertNull(serializationConfig.findMixInClassFor(RefProperty.class));
		assertNull(serializationConfig.findMixInClassFor(RefPath.class));
		assertNull(serializationConfig.findMixInClassFor(RefParameter.class));
		assertNull(serializationConfig.findMixInClassFor(RefResponse.class));

		swaggerJacksonModuleRegistrar.maybeRegisterModule(objectMapper);

		assertNotNull(serializationConfig.findMixInClassFor(RefModel.class));
		assertNotNull(serializationConfig.findMixInClassFor(RefProperty.class));
		assertNotNull(serializationConfig.findMixInClassFor(RefPath.class));
		assertNotNull(serializationConfig.findMixInClassFor(RefParameter.class));
		assertNotNull(serializationConfig.findMixInClassFor(RefResponse.class));

	}

}

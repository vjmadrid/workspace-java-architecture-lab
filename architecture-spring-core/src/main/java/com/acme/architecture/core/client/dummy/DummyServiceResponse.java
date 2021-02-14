package com.acme.architecture.core.client.dummy;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acme.architecture.common.annotation.response.CreateDummyResponse;
import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DummyServiceResponse {

	private Logger logger = LoggerFactory.getLogger(DummyServiceResponse.class);

	@Autowired
	private ResourceLoader resourceLoader;

	private ObjectMapper mapper;

	private PathMatchingResourcePatternResolver resolver;

	private ClassPathScanningCandidateComponentProvider provider;

	private Map<String, Object> jsonResponseMap = new HashMap<>();

	private Map<Class<?>, Object> instanceReponseMap = new HashMap<>();

	private static final String FOLDER_NAME = "dummy";

	public void load(final String commonPackage) {
		
		readAllAzureResponses();
		findAnnotatedClassesWithCreateDummyResponse(commonPackage);
		createInstanceOfResponses();
	}

	public <T> T getDummyResponseEntityBody(Class<T> clzzResponse) {

		ResponseEntity<T> dummyResonse = getDummyResponseEntity(clzzResponse);
		return dummyResonse.getBody();
	}

	public <T> String getDummyResponseEntityJsonBody(Class<T> clzzResponse) throws JsonProcessingException {

		ResponseEntity<T> dummyResonse = getDummyResponseEntity(clzzResponse);
		return mapper.writeValueAsString(dummyResonse.getBody());
	}

	public <T> ResponseEntity<T> getDummyResponseEntity(Class<T> clzzResponse) {

		Object dummyResonse = getDummyResponse(clzzResponse);
		return ResponseEntity.ok(clzzResponse.cast(dummyResonse));
	}

	public <T> T getDummyResponse(Class<T> clzzResponse) {

		final String error = "Dummy response not found for class ";

		if (!instanceReponseMap.containsKey(clzzResponse)) {
			throw new AcmeInvokeRuntimeException(error.concat(clzzResponse.getName()), HttpStatus.INTERNAL_SERVER_ERROR,
					error);
		}

		Object dummyResonse = instanceReponseMap.get(clzzResponse);

		if (dummyResonse == null) {
			throw new AcmeInvokeRuntimeException(error.concat(clzzResponse.getName()), HttpStatus.INTERNAL_SERVER_ERROR,
					error);
		}

		return clzzResponse.cast(dummyResonse);
	}

	public void readAllAzureResponses() {

		final String resourcePath = "classpath:/".concat(FOLDER_NAME);

		try {

			Resource dummyResource = resolver.getResource(resourcePath);

			if (dummyResource.exists()) {

				Resource[] allResources = resolver.getResources(resourcePath.concat("/*/*.json"));

				for (Resource resource : allResources) {
					byte[] targetArray = ByteStreams.toByteArray(resource.getInputStream());
					jsonResponseMap.put(resource.getFilename(), new String(targetArray));
				}

			}

		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	public void createInstanceOfResponses() {

		for (Map.Entry<Class<?>, Object> entry : instanceReponseMap.entrySet()) {

			try {

				Class<?> clzz = entry.getKey();

				Object jsonObject = jsonResponseMap.get(clzz.getAnnotation(CreateDummyResponse.class).fileName());

				if (jsonObject != null) {
					instanceReponseMap.put(clzz, clzz.cast(mapper.readValue(jsonObject.toString(), clzz)));
				}

			} catch (Exception e) {
				logger.warn("Error creating dummy response ", e);
			}

		}

	}

	public void findAnnotatedClassesWithCreateDummyResponse(final String commonPackage) {

		for (BeanDefinition beanDef : provider.findCandidateComponents(commonPackage)) {

			try {

				instanceReponseMap.put(Class.forName(beanDef.getBeanClassName()), null);

			} catch (ClassNotFoundException e) {
				logger.warn(e.getMessage());
			}
		}

	}

}

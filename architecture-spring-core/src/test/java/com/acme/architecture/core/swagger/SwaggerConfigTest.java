package com.acme.architecture.core.swagger;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigTest {

	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String VERSION = "1.0";
	private static final String TERMS = "terms";
	private static final String CONTACT_NAME = "contact name";
	private static final String CONTACT_URL = "contact url";
	private static final String LICENSE = "license";
	private static final String URL = "www.acme.com";
	private static final Set<String> PRODUCE_CONSUME = Collections.singleton("application/json");
	private static final Set<String> PROTOCOLS = Collections.singleton("protocol1");
	private static final String RESPONSE_401 = "401";
	private static final String RESPONSE_403 = "403";
	private static final String RESPONSE_404 = "404";
	private static final String RESPONSE_405 = "405";
	private static final String RESPONSE_409 = "409";
	private static final String RESPONSE_415 = "415";
	private static final String RESPONSE_429 = "429";
	private static final String RESPONSE_500 = "500";

	private static final String ERROR_REPONSE_CLASS_NAME = "CoreExceptionResponse";

	private static List<String> getResponseConfig;
	private static List<String> postPutResponseConfig;

	private SwaggerConfig swaggerConfig;

	private SwaggerProperties swaggerProperties;

	private TypeResolver typeResolver = new TypeResolver();

	@BeforeEach
	public void init() {

		swaggerProperties = new SwaggerProperties();
		swaggerProperties.setTitle(TITLE);
		swaggerProperties.setDescription(DESCRIPTION);
		swaggerProperties.setVersion(VERSION);
		swaggerProperties.setTerms(TERMS);
		swaggerProperties.setContactName(CONTACT_NAME);
		swaggerProperties.setContactUrl(CONTACT_URL);
		swaggerProperties.setLicense(LICENSE);
		swaggerProperties.setLicenseUrl(URL);
		swaggerProperties.setProduceConsume(PRODUCE_CONSUME);
		swaggerProperties.setProtocols(PROTOCOLS);
		swaggerProperties.setResponse401(RESPONSE_401);
		swaggerProperties.setResponse403(RESPONSE_403);
		swaggerProperties.setResponse404(RESPONSE_404);
		swaggerProperties.setResponse405(RESPONSE_405);
		swaggerProperties.setResponse409(RESPONSE_409);
		swaggerProperties.setResponse415(RESPONSE_415);
		swaggerProperties.setResponse429(RESPONSE_429);
		swaggerProperties.setResponse500(RESPONSE_500);

		getResponseConfig = Arrays.asList(RESPONSE_401, RESPONSE_403, RESPONSE_404, RESPONSE_415, RESPONSE_429,
				RESPONSE_500);
		postPutResponseConfig = Arrays.asList(RESPONSE_401, RESPONSE_405, RESPONSE_409, RESPONSE_401, RESPONSE_403,
				RESPONSE_404, RESPONSE_415, RESPONSE_429, RESPONSE_500);

		swaggerConfig = new SwaggerConfig();
		swaggerConfig.setSwaggerProperties(swaggerProperties);
		swaggerConfig.setTypeResolver(typeResolver);

	}

	@Test
	public void whenCallBuildApiInfo_thenReturnApiInfo() {

		ApiInfo apiInfo = swaggerConfig.configApiInfo();

		assertEquals(TITLE, apiInfo.getTitle());
		assertEquals(DESCRIPTION, apiInfo.getDescription());
		assertEquals(VERSION, apiInfo.getVersion());
		assertEquals(TERMS, apiInfo.getTermsOfServiceUrl());
		assertEquals(CONTACT_NAME, apiInfo.getContact().getName());
		assertEquals(CONTACT_URL, apiInfo.getContact().getUrl());
		assertEquals(LICENSE, apiInfo.getLicense());
		assertEquals(URL, apiInfo.getLicenseUrl());
	}

	@SuppressWarnings("null")
	@Test
	public void whenCallConfigCommonResponseMessagesWithNullList_thenDoNothing() {

		List<ResponseMessage> responseMessageList = null;

		swaggerConfig.configCommonResponseMessages(responseMessageList);

		assertNull(responseMessageList);

	}

	@Test
	public void whenCallConfigCommonResponseMessagesWithList_thenAddNewResponseMessageList() {

		List<ResponseMessage> responseMessageList = new ArrayList<>();

		swaggerConfig.configCommonResponseMessages(responseMessageList);

		assertTrue(responseMessageList.size() == 1);
		validateCommonResponseMessages(responseMessageList.get(0));

	}

	@Test
	public void whenCallConfigPostPutResponseMessages() {

		List<ResponseMessage> responseMessageList = swaggerConfig.configPostPutResponseMessages();
		assertEquals(postPutResponseConfig.size(), responseMessageList.size());
		responseMessageList.stream().forEach(r -> {
			assertTrue(postPutResponseConfig.contains(r.getMessage()));
			assertEquals(ERROR_REPONSE_CLASS_NAME, r.getResponseModel().getType());
		});
	}

	@Test
	public void whenCallConfigGetResponseMessages() {

		List<ResponseMessage> responseMessageList = swaggerConfig.configGetResponseMessages();
		assertEquals(getResponseConfig.size(), responseMessageList.size());
		responseMessageList.stream().forEach(r -> {
			assertTrue(getResponseConfig.contains(r.getMessage()));
			assertEquals(ERROR_REPONSE_CLASS_NAME, r.getResponseModel().getType());
		});
	}

	@Test
	public void whenCallSwagger_thenReturnDocket() {

		Docket docket = swaggerConfig.swagger();
		assertNotNull(docket);
	}

	private void validateCommonResponseMessages(ResponseMessage responseMessage) {

		assertEquals(ERROR_REPONSE_CLASS_NAME, responseMessage.getResponseModel().getType());
		assertEquals(RESPONSE_401, responseMessage.getMessage());
	}

}

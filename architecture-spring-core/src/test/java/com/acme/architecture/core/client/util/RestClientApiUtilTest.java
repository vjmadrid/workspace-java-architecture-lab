package com.acme.architecture.core.client.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.acme.architecture.core.client.GenericRestClient;

public class RestClientApiUtilTest {

	@Mock
	private RestTemplateBuilder restTemplateBuilder;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		when(restTemplateBuilder.setConnectTimeout(any())).thenReturn(restTemplateBuilder);
		when(restTemplateBuilder.setReadTimeout(any())).thenReturn(restTemplateBuilder);
		when(restTemplateBuilder.interceptors(anyCollection())).thenReturn(restTemplateBuilder);
		when(restTemplateBuilder.defaultHeader(anyString(), anyString())).thenReturn(restTemplateBuilder);
		when(restTemplateBuilder.additionalMessageConverters(any(HttpMessageConverter.class)))
				.thenReturn(restTemplateBuilder);
		when(restTemplateBuilder.build()).thenReturn(restTemplate);
	}

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, () -> {
			new RestClientApiUtil();
		});
	}

	@Test
	public void whenhCallRestTemplate_ThenGenericRestClient() {
		GenericRestClient genericRestClient = RestClientApiUtil.restTemplate(restTemplateBuilder);

		assertNotNull(genericRestClient.getRestTemplate());
	}

	@Test
	public void whenhCallCloudRestTemplate_ThenGenericRestClient() {
		GenericRestClient genericRestClient = RestClientApiUtil.cloudRestTemplate(restTemplateBuilder);

		assertNotNull(genericRestClient.getRestTemplate());
	}

}

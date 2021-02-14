package com.acme.architecture.core.client.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.acme.architecture.core.client.GenericRestClient;
import com.acme.architecture.core.client.constant.RestClientApiConstant;
import com.acme.architecture.core.interceptor.RestTemplateInterceptor;

public final class RestClientApiUtil {

	protected RestClientApiUtil() {
		throw new IllegalStateException("RestClientApiUtil");
	}

	public static final GenericRestClient restTemplate(RestTemplateBuilder builder) {

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new RestTemplateInterceptor());
		Duration connectionTimeout = Duration.ofSeconds(RestClientApiConstant.DEFAULT_TIME);
		Duration readTimeout = Duration.ofSeconds(RestClientApiConstant.DEFAULT_TIME);

		RestTemplate restTemplate = builder.setConnectTimeout(connectionTimeout).setReadTimeout(readTimeout)
				.interceptors(interceptors).defaultHeader("Accept", MediaType.APPLICATION_JSON.toString())
				.defaultHeader("Content-Type", MediaType.APPLICATION_JSON.toString())
				.additionalMessageConverters(new MappingJackson2HttpMessageConverter()).build();

		return new GenericRestClient(restTemplate);

	}

	public static final GenericRestClient cloudRestTemplate(RestTemplateBuilder builder) {

		Duration connectionTimeout = Duration.ofSeconds(RestClientApiConstant.DEFAULT_TIME);
		Duration readTimeout = Duration.ofSeconds(RestClientApiConstant.DEFAULT_TIME);

		builder.setConnectTimeout(connectionTimeout);
		builder.setReadTimeout(readTimeout);
		builder.defaultHeader("Accept", MediaType.APPLICATION_JSON.toString());
		builder.defaultHeader("Content-Type", MediaType.APPLICATION_JSON.toString());

		RestTemplate restTemplate = builder.build();

		return new GenericRestClient(restTemplate);
	}
}

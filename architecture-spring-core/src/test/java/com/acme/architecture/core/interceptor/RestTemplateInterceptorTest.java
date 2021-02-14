package com.acme.architecture.core.interceptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

import com.acme.architecture.core.client.dummy.entity.CreateDummyEntity;
import com.acme.architecture.core.constant.HeaderConstant;

public class RestTemplateInterceptorTest {

	private RestTemplateInterceptor restTemplateInterceptor = new RestTemplateInterceptor();

	private ClientHttpRequestExecution execution;

	private byte[] body = CreateDummyEntity.FILE_RESOURCE.getBytes();

	private HttpRequest request;

	private final String token = "token";
	private final UUID uuid = UUID.randomUUID();
	private HttpHeaders headers;

	@BeforeEach
	public void setUp() {

		restTemplateInterceptor = spy(RestTemplateInterceptor.class);
		execution = mock(ClientHttpRequestExecution.class);
		request = mock(HttpRequest.class);
		headers = new HttpHeaders();

		when(request.getHeaders()).thenReturn(headers);

		when(restTemplateInterceptor.getTokenFromContext()).thenReturn(token);
		when(restTemplateInterceptor.getUuidFromContext()).thenReturn(uuid);
	}

	@Test
	public void whenCallIntercept_thenReturnClientHttpResponse() throws IOException {

		restTemplateInterceptor.intercept(request, body, execution);

		assertEquals(headers.get(HeaderConstant.HEADER_AUTHORIZATION).get(0), token);
		assertEquals(headers.get(HeaderConstant.HEADER_UUID).get(0).toString(), uuid.toString());
	}

	@Test
	public void whenCallIntercept_thenReturnClientHttpResponseWithouToken() throws IOException {

		when(restTemplateInterceptor.getTokenFromContext()).thenReturn(Strings.EMPTY);

		restTemplateInterceptor.intercept(request, body, execution);

		assertFalse(headers.containsKey(HeaderConstant.HEADER_AUTHORIZATION));
		assertEquals(headers.get(HeaderConstant.HEADER_UUID).get(0).toString(), uuid.toString());
	}

	@Test
	public void whenCallIntercept_thenReturnClientHttpResponseWithouUuid() throws IOException {

		when(restTemplateInterceptor.getUuidFromContext()).thenReturn(null);

		restTemplateInterceptor.intercept(request, body, execution);

		assertEquals(headers.get(HeaderConstant.HEADER_AUTHORIZATION).get(0), token);
		assertFalse(headers.containsKey(HeaderConstant.HEADER_UUID));
	}

}

package com.acme.architecture.core.client.dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import com.acme.architecture.core.client.dummy.entity.CreateDummyEntity;

public class DummyHeaderRequestInterceptorTest {

	private DummyHeaderRequestInterceptor dummyHeaderRequestInterceptor = new DummyHeaderRequestInterceptor();

	private InputStream targetStream;

	private ClientHttpRequestExecution execution;

	private byte[] body;

	private HttpRequest request;

	@BeforeEach
	public void setUp() {

		execution = mock(ClientHttpRequestExecution.class);
		request = mock(HttpRequest.class);

		body = CreateDummyEntity.FILE_RESOURCE.getBytes();
		targetStream = new ByteArrayInputStream(body);

		DummyClientHttpResponse.buildDummyClientHttpResponse(targetStream);
	}

	@Test
	public void whenCallIntercept_thenReturnDummyClientHttpResponse() throws IOException {

		ClientHttpResponse clientResponse = dummyHeaderRequestInterceptor.intercept(request, body, execution);

		assertEquals(targetStream, clientResponse.getBody());
		assertEquals(HttpStatus.OK, clientResponse.getStatusCode());
	}

}

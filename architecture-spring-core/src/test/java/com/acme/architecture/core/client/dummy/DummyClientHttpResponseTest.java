package com.acme.architecture.core.client.dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.acme.architecture.core.client.dummy.entity.CreateDummyEntity;

public class DummyClientHttpResponseTest {

	@Test
	public void whenCallNextDummyResponse_thenReturnClientHttpResponseDefault() throws IOException {

		HttpHeaders expectedHeaders = new HttpHeaders();
		expectedHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		InputStream targetStream = new ByteArrayInputStream(CreateDummyEntity.FILE_RESOURCE.getBytes());
		DummyClientHttpResponse.buildDummyClientHttpResponse(targetStream);

		ClientHttpResponse clientResponse = DummyClientHttpResponse.nextDummyResponse();

		assertEquals(expectedHeaders, clientResponse.getHeaders());
		assertEquals(targetStream, clientResponse.getBody());
		assertEquals(HttpStatus.OK.name(), clientResponse.getStatusText());
		assertEquals(HttpStatus.OK, clientResponse.getStatusCode());
		assertEquals(HttpStatus.OK.value(), clientResponse.getRawStatusCode());
		
		clientResponse.close();
	}

}

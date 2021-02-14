package com.acme.architecture.core.client.dummy;

import java.io.InputStream;

import org.springframework.http.client.ClientHttpResponse;

import com.acme.architecture.core.client.response.CustomClientHttpResponse;

public class DummyClientHttpResponse {

	private static ClientHttpResponse response = null;

	private DummyClientHttpResponse() {

	}

	public static ClientHttpResponse nextDummyResponse() {
		return response;
	}

	public static void buildDummyClientHttpResponse(InputStream targetStream) {

		response = new CustomClientHttpResponse(targetStream);
	}

}

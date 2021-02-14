package com.acme.architecture.core.client.response;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

public class CustomClientHttpResponse implements ClientHttpResponse, Serializable {

	private static final long serialVersionUID = -8888118762518330885L;
	
	private InputStream targetStream;
	
	public CustomClientHttpResponse(InputStream targetStream) {
		super();
		this.targetStream = targetStream;
	}

	@Override
	public InputStream getBody() throws IOException {
		return targetStream;
	}

	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	@Override
	public HttpStatus getStatusCode() throws IOException {
		return HttpStatus.OK;
	}

	@Override
	public int getRawStatusCode() throws IOException {
		return HttpStatus.OK.value();
	}

	@Override
	public String getStatusText() throws IOException {
		return HttpStatus.OK.name();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}

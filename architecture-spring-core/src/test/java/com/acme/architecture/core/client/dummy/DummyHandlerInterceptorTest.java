package com.acme.architecture.core.client.dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import com.acme.architecture.core.client.dummy.entity.CreateDummyEntity;
import com.acme.architecture.core.client.dummy.DummyClientHttpResponse;
import com.acme.architecture.core.client.dummy.DummyHandlerInterceptor;
import com.acme.architecture.core.client.dummy.DummyServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DummyHandlerInterceptorTest {

	private DummyHandlerInterceptor dummyHandlerInterceptor = new DummyHandlerInterceptor();

	private JoinPoint jp;

	private Signature signature;

	private DummyServiceResponse serviceDummyResponse;

	@BeforeEach
	public final void setUp() throws JsonProcessingException {

		jp = mock(JoinPoint.class);
		signature = mock(Signature.class);
		serviceDummyResponse = mock(DummyServiceResponse.class);

		dummyHandlerInterceptor.setServiceDummyResponse(serviceDummyResponse);

		when(jp.getSignature()).thenReturn(signature);

		when(signature.getDeclaringTypeName())
				.thenReturn("com.acme.architecture.core.client.dummy.DummyHandlerInterceptor");
		when(signature.getName()).thenReturn("buildDummyClientHttpResponseForService");

		when(serviceDummyResponse.getDummyResponseEntityJsonBody(any())).thenReturn(CreateDummyEntity.FILE_RESOURCE);
	}
	
	@Test
	public void checkCreateDummyServiceExist() throws ClassNotFoundException {
		
		assertNotNull(Class.forName("com.acme.architecture.common.annotation.service.CreateDummyService"));
	}

	@Test
	public void whenCallBuildDummyClientHttpResponseForService_thenCreateDummyClientHttpResponse() throws Exception {

		dummyHandlerInterceptor.buildDummyClientHttpResponseForService(jp);

		ClientHttpResponse clientResponse = DummyClientHttpResponse.nextDummyResponse();

		assertNotNull(clientResponse.getBody());
		assertEquals(HttpStatus.OK, clientResponse.getStatusCode());

	}

	@Test
	public void whenCallBuildDummyClientHttpResponseForService_thenThrowClassNotFoundException() throws Exception {

		when(signature.getDeclaringTypeName()).thenReturn("InvalidClass");
		
		assertThrows(ClassNotFoundException.class, ()->{
			dummyHandlerInterceptor.buildDummyClientHttpResponseForService(jp);
		});

	}

}

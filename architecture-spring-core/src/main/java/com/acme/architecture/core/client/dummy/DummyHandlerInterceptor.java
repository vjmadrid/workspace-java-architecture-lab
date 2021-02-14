package com.acme.architecture.core.client.dummy;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.acme.architecture.core.constant.DefaultSpringConfigConstant;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Setter;

@Aspect
@Component
@Profile(value = { DefaultSpringConfigConstant.SPRING_PROFILE_LOCAL,
		DefaultSpringConfigConstant.SPRING_PROFILE_DEVELOPMENT })
public class DummyHandlerInterceptor {

	@Setter
	@Autowired
	private DummyServiceResponse serviceDummyResponse;

	@Before("@annotation(com.acme.architecture.common.annotation.service.CreateDummyService)")
	public void buildDummyClientHttpResponseForService(JoinPoint jp)
			throws ClassNotFoundException, JsonProcessingException {

		Class<?> c = Class.forName(jp.getSignature().getDeclaringTypeName());
		Method[] methods = c.getMethods();
		List<Method> methodList = Arrays.asList(methods);

		for (Method method : methodList) {

			if (method.getName().equalsIgnoreCase(jp.getSignature().getName())) {
				String jsonResponse = serviceDummyResponse.getDummyResponseEntityJsonBody(method.getReturnType());
				DummyClientHttpResponse.buildDummyClientHttpResponse(new ByteArrayInputStream(jsonResponse.getBytes()));
			}
		}
	}

}

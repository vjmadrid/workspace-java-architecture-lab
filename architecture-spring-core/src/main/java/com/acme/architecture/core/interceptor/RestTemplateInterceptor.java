package com.acme.architecture.core.interceptor;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.acme.architecture.core.constant.HeaderConstant;
import com.acme.architecture.core.context.RequestContext;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	private static Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		String token = getTokenFromContext();
		UUID uuid = getUuidFromContext();

		if (StringUtils.isNotEmpty(token)) {
			request.getHeaders().add(HeaderConstant.HEADER_AUTHORIZATION, token);
		}

		if (uuid != null) {
			request.getHeaders().add(HeaderConstant.HEADER_UUID, uuid.toString());
		}

		return execution.execute(request, body);

	}

	protected UUID getUuidFromContext() {
		UUID uuid = RequestContext.getContext().getUuid();
		logger.debug("Get uuid from context {}", uuid);
		return uuid;
	}

	protected String getTokenFromContext() {
		String token = RequestContext.getContext().getToken();
		logger.debug("Get token from context {}", token);
		return token;
	}

}

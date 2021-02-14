package com.acme.architecture.core.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.acme.architecture.core.context.RequestContext;
import com.acme.architecture.core.util.HeaderUtil;

@Component
public class RequestFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("Initializing filter :{}", this);
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		LOGGER.trace("Set request configuration for URI: {}", httpServletRequest.getRequestURI());

		String token = HeaderUtil.checkAuthorization(httpServletRequest);

		UUID uuid = HeaderUtil.checkUuid(httpServletRequest);

		RequestContext.getContext().setToken(token);
		RequestContext.getContext().setUuid(uuid);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		LOGGER.warn("Destructing filter :{}", this);
	}
}

package com.acme.architecture.core.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acme.architecture.core.constant.HeaderConstant;
import com.acme.architecture.core.context.RequestContext;

public class RequestFilterTest {

	HttpServletRequest httpServletRequest;
	HttpServletResponse httpServletResponse;
	FilterChain filterChain;
	FilterConfig filterConfig;

	private static final String HEADER_AUTHORIZATION_VALUE = "12346";
	private static final String HEADER_UUID_VALUE = "2bb0d02d-429c-4cc5-a134-bd09a8658f91";

	@BeforeEach
	public void setUp() {
		httpServletRequest = mock(HttpServletRequest.class);
		httpServletResponse = mock(HttpServletResponse.class);
		filterChain = mock(FilterChain.class);
		filterConfig = mock(FilterConfig.class);

		// mock the getRemoteAddr() response
		when(httpServletRequest.getRequestURI()).thenReturn("/");
		when(httpServletRequest.getHeader(HeaderConstant.HEADER_AUTHORIZATION)).thenReturn(HEADER_AUTHORIZATION_VALUE);
		when(httpServletRequest.getHeader(HeaderConstant.HEADER_UUID)).thenReturn(HEADER_UUID_VALUE);
	}

	@Test
	public void whenDoFilter_thenCheckRequestContextValues() throws IOException, ServletException {

		RequestFilter requestFilter = new RequestFilter();

		requestFilter.init(filterConfig);
		requestFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
		requestFilter.destroy();

		assertEquals(HEADER_AUTHORIZATION_VALUE, RequestContext.getContext().getToken());
		assertEquals(HEADER_UUID_VALUE, RequestContext.getContext().getUuid().toString());

	}
}

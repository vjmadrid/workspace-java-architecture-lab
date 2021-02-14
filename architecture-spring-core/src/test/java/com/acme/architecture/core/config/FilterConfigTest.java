package com.acme.architecture.core.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import com.acme.architecture.core.filter.RequestFilter;

public class FilterConfigTest {

	FilterConfig filterConfig = new FilterConfig();
	String urlPattern = "/*";

	@Test
	public void whenConfigRequestFilter_thenReturnFilterRegistrationBean() {

		FilterRegistrationBean<RequestFilter> filterRegistrationBean = filterConfig.requestFilter();

		assertNotNull(filterRegistrationBean);
		assertTrue(filterRegistrationBean.getUrlPatterns().contains(urlPattern));

	}
}

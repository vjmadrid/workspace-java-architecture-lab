package com.acme.architecture.core.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import com.acme.architecture.core.filter.RequestFilter;

@Configuration
public class FilterConfig {

	// uncomment this and comment the @Component in the filter class definition to
	// register only for a url pattern
	// @Bean
	public FilterRegistrationBean<RequestFilter> requestFilter() {

		FilterRegistrationBean<RequestFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new RequestFilter());

		registrationBean.addUrlPatterns("/*");

		return registrationBean;

	}

}
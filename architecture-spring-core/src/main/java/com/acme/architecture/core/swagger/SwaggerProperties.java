package com.acme.architecture.core.swagger;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("swagger.info")
public class SwaggerProperties {

	private String title;

	private String description;

	private String version;

	private String terms;

	private String contactName;

	private String contactUrl;

	private String contactEmail;

	private String license;

	private String licenseUrl;

	private Set<String> produceConsume;

	private Set<String> protocols;

	private String host;

	private String response401;

	private String response403;

	private String response404;

	private String response405;

	private String response409;

	private String response415;

	private String response429;

	private String response500;

}

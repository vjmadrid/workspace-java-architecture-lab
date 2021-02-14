package com.acme.architecture.core.swagger.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.acme.architecture.core.swagger.SwaggerJacksonModuleRegistrar;
import com.acme.architecture.core.swagger.SwaggerProperties;
import com.acme.architecture.core.response.CoreExceptionResponse;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;

import lombok.Setter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Setter
@Configuration
@EnableOpenApi
@Import({ springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class })
public class SwaggerConfig {

	private static final String ERROR_REPONSE_CLASS_NAME = "CoreExceptionResponse";

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Autowired
	private TypeResolver typeResolver;

	@Bean
	public SwaggerJacksonModuleRegistrar swaggerJacksonModuleRegistrar() {
		return new SwaggerJacksonModuleRegistrar();
	}

	@Bean
	public Docket swagger() {

		return new Docket(DocumentationType.OAS_30).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/error").negate()).build().apiInfo(configApiInfo())
				.protocols(Sets.newHashSet(swaggerProperties.getProtocols()))
				.consumes(swaggerProperties.getProduceConsume()).produces(swaggerProperties.getProduceConsume())
				.useDefaultResponseMessages(false).host(swaggerProperties.getHost())
				.globalResponses(HttpMethod.GET, configGetResponseMessages())
				.globalResponses(HttpMethod.POST, configPostPutResponseMessages())
				.additionalModels(configCommonErrorResponseModel())
				.globalResponses(HttpMethod.PUT, configPostPutResponseMessages())
				.additionalModels(configCommonErrorResponseModel())
				.globalResponses(HttpMethod.DELETE, configPostPutResponseMessages())
				.additionalModels(configCommonErrorResponseModel());

	}

	public ApiInfo configApiInfo() {

		return new ApiInfo(swaggerProperties.getTitle(), swaggerProperties.getDescription(),
				swaggerProperties.getVersion(), swaggerProperties.getTerms(),
				new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(),
						swaggerProperties.getContactEmail()),
				swaggerProperties.getLicense(), swaggerProperties.getLicenseUrl(), Collections.emptyList());
	}

	public ResolvedType configCommonErrorResponseModel() {
		return typeResolver.resolve(CoreExceptionResponse.class);
	}

	public List<Response> configPostPutResponseMessages() {

		List<Response> responseMessageList = new ArrayList<>();

		responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value())).description(swaggerProperties.getResponse405())
				.representation(MediaType.APPLICATION_JSON)
				.apply(r ->
                r.model(m ->
                    m.referenceModel(ref ->
                        ref.key(k ->
                            k.qualifiedModelName(q ->
                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
				.build());
		
		responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.CONFLICT.value())).description(swaggerProperties.getResponse409())
				.representation(MediaType.APPLICATION_JSON)
				.apply(r ->
                r.model(m ->
                    m.referenceModel(ref ->
                        ref.key(k ->
                            k.qualifiedModelName(q ->
                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
				.build());
		
		responseMessageList.addAll(configGetResponseMessages());

		return responseMessageList;
	}

	public List<Response> configGetResponseMessages() {

		List<Response> responseMessageList = new ArrayList<>();

		responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.NOT_FOUND.value()))
				.description(swaggerProperties.getResponse404())
				.representation(MediaType.APPLICATION_JSON)
				.apply(r ->
                r.model(m ->
                    m.referenceModel(ref ->
                        ref.key(k ->
                            k.qualifiedModelName(q ->
                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
				.build());
		responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
				.description(swaggerProperties.getResponse415())
				.representation(MediaType.APPLICATION_JSON)
				.apply(r ->
                r.model(m ->
                    m.referenceModel(ref ->
                        ref.key(k ->
                            k.qualifiedModelName(q ->
                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
				.build());
		responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.TOO_MANY_REQUESTS.value()))
				.description(swaggerProperties.getResponse429())
				.representation(MediaType.APPLICATION_JSON)
				.apply(r ->
                r.model(m ->
                    m.referenceModel(ref ->
                        ref.key(k ->
                            k.qualifiedModelName(q ->
                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
				.build());
		responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.FORBIDDEN.value()))
				.description(swaggerProperties.getResponse403())
				.representation(MediaType.APPLICATION_JSON)
				.apply(r ->
                r.model(m ->
                    m.referenceModel(ref ->
                        ref.key(k ->
                            k.qualifiedModelName(q ->
                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
				.build());
		responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.description(swaggerProperties.getResponse500())
				.representation(MediaType.APPLICATION_JSON)
				.apply(r ->
                r.model(m ->
                    m.referenceModel(ref ->
                        ref.key(k ->
                            k.qualifiedModelName(q ->
                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
				.build());

		configCommonResponseMessages(responseMessageList);

		return responseMessageList;
	}

	public void configCommonResponseMessages(List<Response> responseMessageList) {

		if (responseMessageList != null) {

			responseMessageList.add(new ResponseBuilder().code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
					.description(swaggerProperties.getResponse401())
					.representation(MediaType.APPLICATION_JSON)
					.apply(r ->
	                r.model(m ->
	                    m.referenceModel(ref ->
	                        ref.key(k ->
	                            k.qualifiedModelName(q ->
	                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
					.build());
			// Response used to set a default response modifying it in jackson utils method
			// to generate a valid JSON in 42crunch
			
			responseMessageList.add(new ResponseBuilder().code("default").description("Default response")
					.representation(MediaType.APPLICATION_JSON)
					.apply(r ->
	                r.model(m ->
	                    m.referenceModel(ref ->
	                        ref.key(k ->
	                            k.qualifiedModelName(q ->
	                                q.name(ERROR_REPONSE_CLASS_NAME).namespace("com.acme.architecture.core.response"))))))
					.build());
		}
	}

}

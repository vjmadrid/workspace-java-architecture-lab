package com.acme.architecture.core.swagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acme.architecture.core.response.CoreExceptionResponse;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import lombok.Setter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Setter
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String ERROR_REPONSE_CLASS_NAME = "CoreExceptionResponse";

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Autowired
	private TypeResolver typeResolver;

	@Bean
	public Docket swagger() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error"))).build().apiInfo(configApiInfo())
				.protocols(Sets.newHashSet(swaggerProperties.getProtocols()))
				.consumes(swaggerProperties.getProduceConsume()).produces(swaggerProperties.getProduceConsume())
				.useDefaultResponseMessages(false).host(swaggerProperties.getHost())
				.globalResponseMessage(RequestMethod.GET, configGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, configPostPutResponseMessages())
				.additionalModels(configCommonErrorResponseModel())
				.globalResponseMessage(RequestMethod.PUT, configPostPutResponseMessages())
				.additionalModels(configCommonErrorResponseModel());
	}

	protected ApiInfo configApiInfo() {

		return new ApiInfo(swaggerProperties.getTitle(), swaggerProperties.getDescription(),
				swaggerProperties.getVersion(), swaggerProperties.getTerms(),
				new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(),
						swaggerProperties.getContactEmail()),
				swaggerProperties.getLicense(), swaggerProperties.getLicenseUrl(), Collections.emptyList());
	}

	protected ResolvedType configCommonErrorResponseModel() {
		return typeResolver.resolve(CoreExceptionResponse.class);
	}

	protected List<ResponseMessage> configPostPutResponseMessages() {

		List<ResponseMessage> responseMessageList = new ArrayList<>();

		responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.METHOD_NOT_ALLOWED.value())
				.message(swaggerProperties.getResponse405()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
				.build());
		responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.CONFLICT.value())
				.message(swaggerProperties.getResponse409()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
				.build());
		responseMessageList.addAll(configGetResponseMessages());

		configCommonResponseMessages(responseMessageList);

		return responseMessageList;
	}

	protected List<ResponseMessage> configGetResponseMessages() {

		List<ResponseMessage> responseMessageList = new ArrayList<>();

		responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
				.message(swaggerProperties.getResponse404()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
				.build());
		responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.message(swaggerProperties.getResponse415()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
				.build());
		responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.TOO_MANY_REQUESTS.value())
				.message(swaggerProperties.getResponse429()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
				.build());
		responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.FORBIDDEN.value())
				.message(swaggerProperties.getResponse403()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
				.build());
		responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(swaggerProperties.getResponse500()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
				.build());

		configCommonResponseMessages(responseMessageList);

		return responseMessageList;
	}

	protected void configCommonResponseMessages(List<ResponseMessage> responseMessageList) {

		if (responseMessageList != null) {

			responseMessageList.add(new ResponseMessageBuilder().code(HttpStatus.UNAUTHORIZED.value())
					.message(swaggerProperties.getResponse401()).responseModel(new ModelRef(ERROR_REPONSE_CLASS_NAME))
					.build());
		}
	}

}

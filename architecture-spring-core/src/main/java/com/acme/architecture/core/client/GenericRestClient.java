package com.acme.architecture.core.client;


import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.acme.architecture.core.client.constant.GenericRestClientConstant;
import com.acme.architecture.core.context.RequestContext;
import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;
import com.acme.architecture.core.exception.ExternalInvokeException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GenericRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericRestClient.class);

	@Getter
	private RestTemplate restTemplate;

	public <T> T post(String url, HttpEntity<?> httpEntity, Class<T> responseClass, Object... params) {

		T response = null;

		try {
			LOGGER.info(GenericRestClientConstant.CALLING_MESSAGE, RequestContext.getContext().getUuid(), "post", url);
			response = exchange(url, HttpMethod.POST, httpEntity, responseClass, HttpStatus.OK, params).getBody();

		} catch (ExternalInvokeException e) {
			throw new AcmeInvokeRuntimeException(GenericRestClientConstant.ERROR_MSG.concat(e.getMessage()), e.getStatus());
		}

		return response;
	}

	public <T> T create(String url, HttpEntity<?> httpEntity, Class<T> responseClass, Object... params)
			throws ExternalInvokeException {

		LOGGER.info(GenericRestClientConstant.CALLING_MESSAGE, RequestContext.getContext().getUuid(), "create", url);

		return exchange(url, HttpMethod.POST, httpEntity, responseClass, HttpStatus.CREATED, params).getBody();
	}

	public <T> T remove(String url, HttpEntity<?> httpEntity, Class<T> responseClass, Object... params)
			throws ExternalInvokeException {

		LOGGER.info(GenericRestClientConstant.CALLING_MESSAGE, RequestContext.getContext().getUuid(), "delete", url);

		return exchange(url, HttpMethod.DELETE, httpEntity, responseClass, HttpStatus.OK, params).getBody();

	}

	public <T> T update(String url, HttpEntity<?> httpEntity, Class<T> responseClass, Object... params)
			throws ExternalInvokeException {

		LOGGER.info(GenericRestClientConstant.CALLING_MESSAGE, RequestContext.getContext().getUuid(), "update", url);

		return exchange(url, HttpMethod.PUT, httpEntity, responseClass, HttpStatus.OK, params).getBody();

	}

	public <T> T get(String url, HttpEntity<?> httpEntity, Class<T> responseClass, Object... params)
			throws ExternalInvokeException {

		LOGGER.info(GenericRestClientConstant.CALLING_MESSAGE, RequestContext.getContext().getUuid(), "get", url);

		return exchange(url, HttpMethod.GET, httpEntity, responseClass, HttpStatus.OK, params).getBody();

	}

	public <T> ResponseEntity<T> exchange(String url, HttpMethod httpMethod, HttpEntity<?> httpEntity,
			Class<T> clasz, HttpStatus expectedHttpStatus, Object... params) throws ExternalInvokeException {

		ResponseEntity<T> response = null;
		String errorMsg = GenericRestClientConstant.ERROR_MSG.concat(url);

		try {

			LOGGER.debug(GenericRestClientConstant.UUID_MESSAGE + "HttpRequest URI: {}", RequestContext.getContext().getUuid(), url);
			LOGGER.debug(GenericRestClientConstant.UUID_MESSAGE + "HttpHeaders: {}", RequestContext.getContext().getUuid(),
					(httpEntity != null) ? httpEntity.getHeaders() : "N/A");
			LOGGER.debug(GenericRestClientConstant.UUID_MESSAGE + "HttpRequest METHOD: {}", RequestContext.getContext().getUuid(), httpMethod);
			Instant start = Instant.now();

			response = restTemplate.exchange(url, httpMethod, httpEntity, clasz, params);

			if (expectedHttpStatus.value() != response.getStatusCode().value()) {
				LOGGER.error(errorMsg);
				LOGGER.error(GenericRestClientConstant.ERROR_MSG_LOG, RequestContext.getContext().getUuid(), url, "Unexpected status code",
						response.getStatusCode(), response.getBody());
				throw new ExternalInvokeException(response.getStatusCode(), errorMsg);
			}

			LOGGER.debug(GenericRestClientConstant.UUID_MESSAGE + "HttpHeaders: {}", RequestContext.getContext().getUuid(),
					response.getHeaders());
			LOGGER.debug(GenericRestClientConstant.UUID_MESSAGE + "ResponseStatus: {}", RequestContext.getContext().getUuid(),
					response.getStatusCode());
			LOGGER.debug(GenericRestClientConstant.UUID_MESSAGE + "ResponseTime: {} milliseconds", RequestContext.getContext().getUuid(),
					Duration.between(start, Instant.now()).toMillis());
			LOGGER.debug(GenericRestClientConstant.UUID_MESSAGE + "Response body {}", RequestContext.getContext().getUuid(), response.getBody());

		} catch (HttpClientErrorException | HttpServerErrorException exception) {

			LOGGER.error(GenericRestClientConstant.UUID_MESSAGE + GenericRestClientConstant.EXCEPTION_CLASS, exception.getClass());

			ExternalInvokeException externalInvokeException = new ExternalInvokeException(exception.getStatusCode(),
					errorMsg + exception.getMessage() + exception.getResponseBodyAsString());

			LOGGER.error(GenericRestClientConstant.ERROR_MSG_LOG, RequestContext.getContext().getUuid(), url, exception.getMessage(),
					exception.getStatusCode(), exception.getResponseBodyAsString());
			throw externalInvokeException;

		} catch (ResourceAccessException exception) {

			LOGGER.error(GenericRestClientConstant.UUID_MESSAGE + GenericRestClientConstant.EXCEPTION_CLASS, exception.getClass());

			ExternalInvokeException externalInvokeException = new ExternalInvokeException(
					HttpStatus.SERVICE_UNAVAILABLE,
					errorMsg + " " + exception.getMessage() + " " + exception.getMostSpecificCause());
			LOGGER.error(GenericRestClientConstant.ERROR_MSG_LOG, RequestContext.getContext().getUuid(), url, exception.getMessage(), GenericRestClientConstant.NONE_STATUS,
					exception.getMostSpecificCause());
			throw externalInvokeException;

		} catch (ExternalInvokeException exception) {
			// Se captura y se devuelve la propia ExternalInvokeException
			// para evitar que sea capturada por el catch de Exception
			throw exception;
		} catch (Exception exception) {

			LOGGER.error(GenericRestClientConstant.UUID_MESSAGE + GenericRestClientConstant.EXCEPTION_CLASS, exception.getClass());

			LOGGER.error(GenericRestClientConstant.ERROR_MSG_LOG, RequestContext.getContext().getUuid(), url, exception.getMessage(), GenericRestClientConstant.NONE_STATUS,
					exception.getCause());
			throw new ExternalInvokeException(HttpStatus.INTERNAL_SERVER_ERROR,
					GenericRestClientConstant.ERROR_MSG_NO_CONTROLLED_ERROR.concat(errorMsg));
		}

		return response;

	}

}

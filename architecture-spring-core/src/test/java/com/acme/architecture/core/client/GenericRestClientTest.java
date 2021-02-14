package com.acme.architecture.core.client;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.acme.architecture.core.client.constant.GenericRestClientConstant;
import com.acme.architecture.core.client.response.GenericRestClientResponse;
import com.acme.architecture.core.exception.ExternalInvokeException;




public class GenericRestClientTest {

	@Mock
	private RestTemplate restTemplate;

	private GenericRestClient genericRestClient;

	Object request;

	private static final String CREATE_URL = "http://localhost:8104/vm/create";
	private static final String REMOVE_URL = "http://localhost:8104/vm/remove";
	private static final String UPDATE_URL = "http://localhost:8104/vm/update";
	private static final String GET_URL = "http://localhost:8104/vm";
	private static final String POST_URL = "http://localhost:8104/vm/post";

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		restTemplate = mock(RestTemplate.class);
		genericRestClient = new GenericRestClient(restTemplate);

		assertNotNull(genericRestClient.getRestTemplate());
	}

	@Test
	public void whenCallExchange_thenReturnResponse() throws ExternalInvokeException {

		when(restTemplate.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(HttpStatus.CREATED));

		ResponseEntity<GenericRestClientResponse> response = genericRestClient.exchange(CREATE_URL, HttpMethod.POST,
				new HttpEntity<Object>(request), GenericRestClientResponse.class, HttpStatus.CREATED);

		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());

	}

	@Test
	public void whenCallExchange_thenThrowHttpClientException() throws ExternalInvokeException {

		when(restTemplate.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND,
								"Test HttpClientErrorException error " + HttpStatus.NOT_FOUND));

		ExternalInvokeException exception = assertThrows(ExternalInvokeException.class, ()->{
			genericRestClient.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
					GenericRestClientResponse.class, HttpStatus.CREATED);
		});
		
		assertThat(exception.getMessage(),containsString("Error response API functions - ".concat(CREATE_URL)));
	
	}

	@Test
	public void whenCallExchange_thenThrowHttpServerException() throws ExternalInvokeException {

		when(restTemplate.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenThrow(new HttpServerErrorException(HttpStatus.NOT_FOUND,
								"Test HttpServerErrorException error " + HttpStatus.NOT_FOUND));

		ExternalInvokeException exception = assertThrows(ExternalInvokeException.class, ()->{
			genericRestClient.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
					GenericRestClientResponse.class, HttpStatus.CREATED);
		});
		
		assertThat(exception.getMessage(),containsString("Error response API functions - ".concat(CREATE_URL)));

	}

	@Test
	public void whenCallExchange_thenThrowResourceAccessException() throws ExternalInvokeException {

		when(restTemplate.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class)).thenThrow(
						new ResourceAccessException("Test ResourceAccessException error " + HttpStatus.NOT_FOUND));

		Exception exception = assertThrows(ExternalInvokeException.class, ()->{
			genericRestClient.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
					GenericRestClientResponse.class, HttpStatus.CREATED);
		});
		
		assertThat(exception.getMessage(),containsString("Error response API functions - ".concat(CREATE_URL)));
	}

	@Test
	public void whenCallExchange_thenThrowException() throws ExternalInvokeException {

		when(restTemplate.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class)).thenThrow(new NullPointerException("Null pointer"));

		ExternalInvokeException exception = assertThrows(ExternalInvokeException.class, ()->{
			genericRestClient.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
					GenericRestClientResponse.class, null);
		});
		
		assertThat(exception.getMessage(),containsString(GenericRestClientConstant.ERROR_MSG_NO_CONTROLLED_ERROR+"Error response API functions - ".concat(CREATE_URL)));

	}

	@Test
	public void whenCallExchange_thenThrowExternalInvokeException() throws ExternalInvokeException {

		when(restTemplate.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(HttpStatus.ACCEPTED));

		ExternalInvokeException exception = assertThrows(ExternalInvokeException.class, ()->{
			genericRestClient.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
					GenericRestClientResponse.class, HttpStatus.CREATED);
		});
		
		assertThat(exception.getMessage(),containsString("Error response API functions - ".concat(CREATE_URL)));
	}

	@Test
	public void whenCallCreate_thenReturnOkResponse() throws ExternalInvokeException {

		GenericRestClientResponse exchangerResponse = new GenericRestClientResponse("Created");

		when(restTemplate.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class)).thenReturn(
						new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.CREATED));
		when(genericRestClient.exchange(CREATE_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class, HttpStatus.CREATED)).thenReturn(
						new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.CREATED));

		GenericRestClientResponse response = genericRestClient.create(CREATE_URL, new HttpEntity<Object>(request),
				GenericRestClientResponse.class);

		assertNotNull(response);
		assertEquals("Created", response.getMessage());

	}

	@Test
	public void whenCallDelete_thenReturnOkResponse() throws ExternalInvokeException {

		GenericRestClientResponse exchangerResponse = new GenericRestClientResponse("Deleted");

		when(restTemplate.exchange(REMOVE_URL, HttpMethod.DELETE, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));
		when(genericRestClient.exchange(REMOVE_URL, HttpMethod.DELETE, new HttpEntity<Object>(request),
				GenericRestClientResponse.class, HttpStatus.OK))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));

		GenericRestClientResponse response = genericRestClient.remove(REMOVE_URL, new HttpEntity<Object>(request),
				GenericRestClientResponse.class);

		assertNotNull(response);
		assertEquals("Deleted", response.getMessage());

	}

	@Test
	public void whenCallUpdate_thenReturnOkResponse() throws ExternalInvokeException {

		GenericRestClientResponse exchangerResponse = new GenericRestClientResponse("Updated");

		when(restTemplate.exchange(UPDATE_URL, HttpMethod.PUT, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));
		when(genericRestClient.exchange(UPDATE_URL, HttpMethod.PUT, new HttpEntity<Object>(request),
				GenericRestClientResponse.class, HttpStatus.OK))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));

		GenericRestClientResponse response = genericRestClient.update(UPDATE_URL, new HttpEntity<Object>(request),
				GenericRestClientResponse.class);

		assertNotNull(response);
		assertEquals("Updated", response.getMessage());

	}

	@Test
	public void whenCallPost_thenReturnOkResponse() throws ExternalInvokeException {

		GenericRestClientResponse exchangerResponse = new GenericRestClientResponse("Post");

		when(restTemplate.exchange(POST_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));
		when(genericRestClient.exchange(POST_URL, HttpMethod.POST, new HttpEntity<Object>(request),
				GenericRestClientResponse.class, HttpStatus.OK))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));

		GenericRestClientResponse response = genericRestClient.post(POST_URL, new HttpEntity<Object>(request),
				GenericRestClientResponse.class);

		assertNotNull(response);
		assertEquals("Post", response.getMessage());

	}
	
	@Test
	public void whenCallGet_thenReturnOkResponse() throws ExternalInvokeException {

		GenericRestClientResponse exchangerResponse = new GenericRestClientResponse("Get");

		when(restTemplate.exchange(GET_URL, HttpMethod.GET, new HttpEntity<Object>(request),
				GenericRestClientResponse.class))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));
		when(genericRestClient.exchange(GET_URL, HttpMethod.GET, new HttpEntity<Object>(request),
				GenericRestClientResponse.class, HttpStatus.OK))
						.thenReturn(new ResponseEntity<GenericRestClientResponse>(exchangerResponse, HttpStatus.OK));

		GenericRestClientResponse response = genericRestClient.get(GET_URL, new HttpEntity<Object>(request),
				GenericRestClientResponse.class);

		assertNotNull(response);
		assertEquals("Get", response.getMessage());

	}

}

package com.acme.architecture.core.client.dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.ResponseEntity;

import com.acme.architecture.core.client.dummy.entity.CreateDummyEntity;
import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DummyServiceResponseTest {
	
	public static final String COMMON_PACKAGE_TEST = "com.acme.core";

	private DummyServiceResponse serviceDummyResponse;

	private ObjectMapper mapper;

	private ResourceLoader resourceLoader;

	private PathMatchingResourcePatternResolver resolver;

	private ClassPathScanningCandidateComponentProvider provider;

	private Resource mockResource = mock(Resource.class);
	private Resource[] mockResources = { mockResource };

	private BeanDefinition beanDefinition = mock(BeanDefinition.class);
	private Set<BeanDefinition> beanDefinitionSet = new HashSet<>();

	@BeforeEach
	public void setUp() throws IOException {

		serviceDummyResponse = new DummyServiceResponse();
		mapper = new ObjectMapper();
		resourceLoader = mock(ResourceLoader.class);
		resolver = mock(PathMatchingResourcePatternResolver.class);
		provider = mock(ClassPathScanningCandidateComponentProvider.class);

		when(mockResource.getInputStream())
				.thenReturn(new ByteArrayInputStream(CreateDummyEntity.FILE_RESOURCE.getBytes()));
		when(mockResource.getFilename()).thenReturn(CreateDummyEntity.FILE_NAME);
		when(mockResource.exists()).thenReturn(true);

		when(resolver.getResource(anyString())).thenReturn(mockResource);
		when(resolver.getResources(anyString())).thenReturn(mockResources);

		when(beanDefinition.getBeanClassName()).thenReturn(CreateDummyEntity.class.getName());
		beanDefinitionSet.add(beanDefinition);
		when(provider.findCandidateComponents(anyString())).thenReturn(beanDefinitionSet);

		serviceDummyResponse.setProvider(provider);
		serviceDummyResponse.setResolver(resolver);
		serviceDummyResponse.setResourceLoader(resourceLoader);
		serviceDummyResponse.setMapper(mapper);

	}

	// Public methods
	@Test
	public void whenCallGetDummyResponseEntity_BodythenReturnResponseEntityDummyTestInstance() {

		serviceDummyResponse.load(COMMON_PACKAGE_TEST);

		CreateDummyEntity dummyTest = serviceDummyResponse.getDummyResponseEntityBody(CreateDummyEntity.class);

		assertNotNull(dummyTest);
		assertEquals(CreateDummyEntity.TEXT_VALUE, dummyTest.getText());
	}

	@Test
	public void whenCallGetDummyResponseEntityJson_thenReturnJsonDummyTestInstance() throws JsonProcessingException {

		serviceDummyResponse.load(COMMON_PACKAGE_TEST);

		String dummyTest = serviceDummyResponse.getDummyResponseEntityJsonBody(CreateDummyEntity.class);

		assertNotNull(dummyTest);
		assertEquals(CreateDummyEntity.FILE_RESOURCE, dummyTest);
	}

	@Test
	public void whenCallGetDummyResponseEntity_thenReturnResponseEntityDummyTestInstance() {

		serviceDummyResponse.load(COMMON_PACKAGE_TEST);

		ResponseEntity<CreateDummyEntity> dummyTestEntity = serviceDummyResponse
				.getDummyResponseEntity(CreateDummyEntity.class);

		CreateDummyEntity dummyTest = dummyTestEntity.getBody();
		assertNotNull(dummyTest);
		assertEquals(CreateDummyEntity.TEXT_VALUE, dummyTest.getText());
	}

	// Get dummy response
	@Test
	public void whenCallGetDummyResponse_thenReturnCreateDummyTestInstance() {

		serviceDummyResponse.load(COMMON_PACKAGE_TEST);

		CreateDummyEntity dummyTest = serviceDummyResponse.getDummyResponse(CreateDummyEntity.class);

		assertNotNull(dummyTest);
		assertEquals(CreateDummyEntity.TEXT_VALUE, dummyTest.getText());
	}

	@Test
	public void whenCallGetDummyResponseWithResponseNotFound_thenThrowAcmeInvokeRuntimeException() {

		assertThrows(AcmeInvokeRuntimeException.class, ()->{
			serviceDummyResponse.createInstanceOfResponses();
	
			serviceDummyResponse.getDummyResponse(CreateDummyEntity.class);
		});
	}

	@Test
	public void whenCallGetDummyResponseWithResponseNull_thenThrowAcmeInvokeRuntimeException() {

		assertThrows(AcmeInvokeRuntimeException.class, ()->{
			serviceDummyResponse.findAnnotatedClassesWithCreateDummyResponse(COMMON_PACKAGE_TEST);
			serviceDummyResponse.createInstanceOfResponses();

			serviceDummyResponse.getDummyResponse(CreateDummyEntity.class);
		});
			
	}

	// create instances of responses
	@Test
	public void whenCallCreateInstanceOfResponses_thenCreateIntanceIntoReponseMap() {

		serviceDummyResponse.load(COMMON_PACKAGE_TEST);

		assertTrue(serviceDummyResponse.getInstanceReponseMap().containsKey(CreateDummyEntity.class));

		CreateDummyEntity dummyTest = (CreateDummyEntity) serviceDummyResponse.getInstanceReponseMap()
				.get(CreateDummyEntity.class);

		assertNotNull(dummyTest);
		assertEquals(CreateDummyEntity.TEXT_VALUE, dummyTest.getText());

	}

	// find annotated classes
	@Test
	public void whenCallFindAnnotatedClassesWithCreateDummyResponse_thenMapInstanceReponseMap() {

		serviceDummyResponse.findAnnotatedClassesWithCreateDummyResponse(COMMON_PACKAGE_TEST);

		assertTrue(serviceDummyResponse.getInstanceReponseMap().containsKey(CreateDummyEntity.class));
		assertNull(serviceDummyResponse.getInstanceReponseMap().get(CreateDummyEntity.class));
	}

	@Test
	public void whenCallFindAnnotatedClassesWithClassNotFound_thenMapInstanceReponseMapEmpty() {

		when(beanDefinition.getBeanClassName()).thenReturn("AnyClassNotExist");

		serviceDummyResponse.findAnnotatedClassesWithCreateDummyResponse(COMMON_PACKAGE_TEST);

		assertTrue(serviceDummyResponse.getInstanceReponseMap().isEmpty());
	}

	// Read all azure responses
	@Test
	public void whenCallReadAllAzureResponses_thenJsonReponseMap() {

		serviceDummyResponse.readAllAzureResponses();

		assertTrue(serviceDummyResponse.getJsonResponseMap().containsKey(CreateDummyEntity.FILE_NAME));
		assertEquals(CreateDummyEntity.FILE_RESOURCE,
				serviceDummyResponse.getJsonResponseMap().get(CreateDummyEntity.FILE_NAME).toString());
	}

	@Test
	public void whenCallReadAllAzureResponsesDummyFolderNotFound_thenJsonReponseMapIsEmpty() throws IOException {

		when(mockResource.exists()).thenReturn(false);

		serviceDummyResponse.readAllAzureResponses();

		assertTrue(serviceDummyResponse.getJsonResponseMap().isEmpty());
	}

	@Test
	public void whenCallReadAllAzureResponsesJsonFileNotFound_thenJsonReponseMapIsEmpty() throws IOException {

		when(resolver.getResources(anyString())).thenThrow(new IOException("IOException"));

		serviceDummyResponse.readAllAzureResponses();

		assertTrue(serviceDummyResponse.getJsonResponseMap().isEmpty());
	}

}

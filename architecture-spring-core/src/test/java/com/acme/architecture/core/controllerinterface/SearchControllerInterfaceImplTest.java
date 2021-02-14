package com.acme.architecture.core.controllerinterface;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acme.architecture.common.util.converter.RestConverterUtil;
import com.acme.architecture.core.constant.SearchControllerConstant;
import com.acme.architecture.core.controllerinterface.dummy.SearchControllerInterfaceImplTesting;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.request.TestingQueryRequest;

public class SearchControllerInterfaceImplTest {

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(SearchControllerInterfaceImplTesting.class).build();
	}

	@Test
	public void whenCallSearchWithQueryRequestNull_thenReturn400Status() throws Exception {

		TestingQueryRequest request = null;
		String criteriaMode = "ALL";
		String criteriaString = "EXACT";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallSearchWithAllCriteriaModeAndCriteriaString_thenReturn200Status() throws Exception {

		TestingQueryRequest request = TestingQueryRequest.createSampleDefault();
		String criteriaMode = "ALL";
		String criteriaString = "EXACT";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallSearchWithAnyCriteriaModeAndCriteriaString_thenReturn200Status() throws Exception {

		TestingQueryRequest request = TestingQueryRequest.createSampleDefault();
		String criteriaMode = "ANY";
		String criteriaString = "EXACT";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallSearchWithCriteriaModeAndCriteriaStringExact_thenReturn200Status() throws Exception {

		TestingQueryRequest request = TestingQueryRequest.createSampleDefault();
		String criteriaMode = "ALL";
		String criteriaString = "EXACT";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallSearchWithCriteriaModeAndCriteriaStringStarting_thenReturn200Status() throws Exception {

		TestingQueryRequest request = TestingQueryRequest.createSampleDefault();
		String criteriaMode = "ALL";
		String criteriaString = "STARTING";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallSearchWithCriteriaModeAndCriteriaStringEnding_thenReturn200Status() throws Exception {

		TestingQueryRequest request = TestingQueryRequest.createSampleDefault();
		String criteriaMode = "ALL";
		String criteriaString = "ENDING";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallSearchWithCriteriaModeAndCriteriaStringContaining_thenReturn200Status() throws Exception {

		TestingQueryRequest request = TestingQueryRequest.createSampleDefault();
		String criteriaMode = "ALL";
		String criteriaString = "CONTAINING";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallSearchWithCriteriaModeAndCriteriaStringRegex_thenReturn200Status() throws Exception {

		TestingQueryRequest request = TestingQueryRequest.createSampleDefault();
		String criteriaMode = "ALL";
		String criteriaString = "REGEX";
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL
				+ SearchControllerConstant.BASE_SEARCH_URL)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, criteriaMode)
						.param(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, criteriaString)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}
}

package com.acme.architecture.core.controllerinterface;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.acme.architecture.core.constant.ControllerConstant;
import com.acme.architecture.core.controllerinterface.dummy.BaseGetControllerInterfaceImplTesting;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;

public class BaseGetControllerInterfaceTest {

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {

		mockMvc = MockMvcBuilders.standaloneSetup(BaseGetControllerInterfaceImplTesting.class).build();
	}

	// Get all
	@Test
	public void whenCallAPIGetAll_thenReturn200Status() throws Exception {

		mockMvc.perform(get(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL)).andExpect(status().isOk());
	}

	// Get by id
	@Test
	public void whenCallAPIGetIdWithMaxId_thenReturn400Status() throws Exception {

		String id = "2147483648";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL
				+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id)).andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIGetIdWithValidId_thenReturn200Status() throws Exception {

		String id = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL
				+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id)).andExpect(status().isOk());
	}

	// Get by id list
	@Test
	public void whenCallAPIGetIdListWithCharacterIds_thenReturn400Status() throws Exception {

		List<String> ids = new ArrayList<>();
		ids.add("a");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.put(ControllerConstant.IDS_QUERY_PARAM, ids);

		mockMvc.perform(
				get(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL + ControllerConstant.BASE_LIST_URL)
						.params(params))
				.andExpect(status().isOk());
	}

	public void whenCallAPIGetIdListWithNullIds_thenReturnXXXStatus() throws Exception {

		List<String> ids = null;

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.put(ControllerConstant.IDS_QUERY_PARAM, ids);

		mockMvc.perform(
				get(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL + ControllerConstant.BASE_LIST_URL)
						.params(params))
				.andExpect(status().isBadRequest());
	}

	public void whenCallAPIGetIdListWithIntegerIds_thenReturn200Status() throws Exception {

		List<String> ids = new ArrayList<>();
		ids.add("1");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.put(ControllerConstant.IDS_QUERY_PARAM, ids);

		mockMvc.perform(
				get(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL + ControllerConstant.BASE_LIST_URL)
						.params(params))
				.andExpect(status().isOk());
	}

	public void whenCallAPIGetIdListWithEmptyIds_thenReturn200Status() throws Exception {

		List<String> ids = new ArrayList<>();

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.put(ControllerConstant.IDS_QUERY_PARAM, ids);

		mockMvc.perform(
				get(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL + ControllerConstant.BASE_LIST_URL)
						.params(params))
				.andExpect(status().isOk());
	}

}

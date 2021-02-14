package com.acme.architecture.core.controllerinterface;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acme.architecture.core.constant.ControllerConstant;
import com.acme.architecture.core.controllerinterface.dummy.FilterLanguageControllerInterfaceImplTesting;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;

public class FilterLanguageControllerInterfaceTest {

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(FilterLanguageControllerInterfaceImplTesting.class).build();
	}

	// Get all
	@Test
	public void whenCallAPIGetAllWithLanguageIdCharacter_thenReturn400Status() throws Exception {

		String languageId = "a";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_URL).param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIGetAllWithLanguageIdNull_thenReturn400Status() throws Exception {

		String languageId = null;

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_URL).param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetAllWithLanguageIdEmpty_thenReturn200Status() throws Exception {

		String languageId = "";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_URL).param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetAllWithLanguageId_thenReturn200Status() throws Exception {

		String languageId = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_URL).param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isOk());
	}

	// Get by id
	@Test
	public void whenCallAPIGetIdWithInvalidIdAndLanguageId_thenReturn400Status() throws Exception {

		String id = "a";
		String languageId = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, id)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIGetIdWithMaxIdAndLanguageId_thenReturn400Status() throws Exception {

		String id = "2147483648";
		String languageId = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, id)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIGetIdWithValidIdAndLanguageId_thenReturn200Status() throws Exception {

		String id = "1";
		String languageId = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, id)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetByIdWithEntityIdAndLanguageIdIdCharacter_thenReturn400Status() throws Exception {

		String languageId = "a";
		String id = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, id)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIGetByIdWithEntityIdAndLanguageIdIdNull_thenReturn400Status() throws Exception {

		String languageId = null;
		String id = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, id)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetByIdWithEntityIdAndLanguageIdIdEmpty_thenReturn200Status() throws Exception {

		String languageId = "";
		String id = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, id)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetByIdWithEntityIdAndLanguageId_thenReturn200Status() throws Exception {

		String languageId = "1";
		String id = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, id)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId))
				.andExpect(status().isOk());
	}

	// Get by id list
	@Test
	public void whenCallAPIGetIdListWithIdsNullAndLanguageId_thenReturn400Status() throws Exception {

		String id = null;
		String[] ids = { id };
		String languageId = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_LIST_URL)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIGetIdListWithIdsEmptyAndLanguageId_thenReturn200Status() throws Exception {

		String id = "";
		String[] ids = { id };
		String languageId = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_LIST_URL)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetIdListWithValidIdAndLanguageId_thenReturn200Status() throws Exception {

		String[] ids = { "1" };
		String languageId = "1";

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_LIST_URL)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetIdListWithEntityIdAndLanguageIdIdCharacter_thenReturn400Status() throws Exception {

		String languageId = "a";
		String[] ids = { "1" };

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_LIST_URL)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIGetIdListWithEntityIdAndLanguageIdIdNull_thenReturn400Status() throws Exception {

		String languageId = null;
		String[] ids = { "1" };

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_LIST_URL)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetIdListWithEntityIdAndLanguageIdIdEmpty_thenReturn200Status() throws Exception {

		String languageId = "";
		String[] ids = { "1" };

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_LIST_URL)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCallAPIGetIdListWithEntityIdAndLanguageId_thenReturn200Status() throws Exception {

		String languageId = "1";
		String[] ids = { "1" };

		mockMvc.perform(get(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL
				+ ControllerConstant.BASE_LANGUAGE_LIST_URL)
						.param(ControllerConstant.LANGUAGE_ID_QUERY_PARAM, languageId)
						.param(ControllerConstant.IDS_QUERY_PARAM, ids))
				.andExpect(status().isOk());
	}
}

package com.acme.architecture.core.controllerinterface;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acme.architecture.common.util.converter.RestConverterUtil;
import com.acme.architecture.core.constant.ControllerConstant;
import com.acme.architecture.core.constant.HeaderConstant;
import com.acme.architecture.core.controllerinterface.dummy.UpdateControllerInterfaceImplTesting;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.request.TestingUpdateRequest;

public class UpdateControllerInterfaceImplTest {

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(UpdateControllerInterfaceImplTesting.class).build();
	}

	@Test
	public void whenCallAPIUpdateWithWithInvalidId_thenReturn400Status() throws Exception {

		String id = "a";
		String userId = "userId";
		TestingUpdateRequest request = TestingUpdateRequest.createSampleDefault();
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(put(ControllerInterfaceTestingConstant.UPDATE_CONTROLLER_BASE_URL
				+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id).header(HeaderConstant.HEADER_USER_ID_AUDIT, userId)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIUpdateWithWithMaxId_thenReturn400Status() throws Exception {

		String id = "2147483648";
		String userId = "userId";
		TestingUpdateRequest request = TestingUpdateRequest.createSampleDefault();
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(put(ControllerInterfaceTestingConstant.UPDATE_CONTROLLER_BASE_URL
				+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id).header(HeaderConstant.HEADER_USER_ID_AUDIT, userId)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIUpdateWithUserIdNull_thenReturn400Status() throws Exception {

		String userId = null;
		String id = "1";
		TestingUpdateRequest request = TestingUpdateRequest.createSampleDefault();
		String content = RestConverterUtil.covertToJsonResponse(request);
		
		assertThrows(IllegalArgumentException.class, ()->{
			mockMvc.perform(put(ControllerInterfaceTestingConstant.UPDATE_CONTROLLER_BASE_URL
					+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id).header(HeaderConstant.HEADER_USER_ID_AUDIT, userId)
							.contentType(MediaType.APPLICATION_JSON).content(content));
		});
		
	}

	@Test
	public void whenCallAPIUpdateWithRequestEmpty_thenReturn400Status() throws Exception {

		String userId = "userId";
		String id = "1";
		String content = "";

		mockMvc.perform(put(ControllerInterfaceTestingConstant.UPDATE_CONTROLLER_BASE_URL
				+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id).header(HeaderConstant.HEADER_USER_ID_AUDIT, userId)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIUpdateWithRequestFieldTextNull_thenReturn400Status() throws Exception {

		String userId = "userId";
		String id = "1";
		TestingUpdateRequest request = TestingUpdateRequest.createSampleDefault();
		request.setText(null);
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(put(ControllerInterfaceTestingConstant.UPDATE_CONTROLLER_BASE_URL
				+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id).header(HeaderConstant.HEADER_USER_ID_AUDIT, userId)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPIUpdateWithUserIdAndRequest_thenReturn200Status() throws Exception {

		String userId = "userId";
		String id = "1";
		TestingUpdateRequest request = TestingUpdateRequest.createSampleDefault();
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(put(ControllerInterfaceTestingConstant.UPDATE_CONTROLLER_BASE_URL
				+ ControllerConstant.ID_PATH_VARIABLE_PARAM, id).header(HeaderConstant.HEADER_USER_ID_AUDIT, userId)
						.contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}
}

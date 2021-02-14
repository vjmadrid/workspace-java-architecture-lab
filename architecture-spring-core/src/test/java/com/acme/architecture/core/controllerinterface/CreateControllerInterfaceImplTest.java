package com.acme.architecture.core.controllerinterface;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acme.architecture.common.util.converter.RestConverterUtil;
import com.acme.architecture.core.constant.HeaderConstant;
import com.acme.architecture.core.controllerinterface.dummy.CreateControllerInterfaceImplTesting;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.request.TestingCreateRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { CreateControllerInterfaceImplTesting.class }, properties="spring.main.banner-mode=off")
public class CreateControllerInterfaceImplTest {

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {

		mockMvc = MockMvcBuilders.standaloneSetup(CreateControllerInterfaceImplTesting.class).build();
	}

	@Test
	public void whenCallAPICreateWithUserIdNull_thenReturn400Status() throws Exception {

		String userId = null;
		TestingCreateRequest request = TestingCreateRequest.createSampleDefault();
		String content = RestConverterUtil.covertToJsonResponse(request);

		assertThrows(IllegalArgumentException.class, ()->{
			mockMvc.perform(post(ControllerInterfaceTestingConstant.CREATE_CONTROLLER_BASE_URL)
					.header(HeaderConstant.HEADER_USER_ID_AUDIT, userId).contentType(MediaType.APPLICATION_JSON)
					.content(content));
		});
		
	}

	@Test
	public void whenCallAPICreateWithRequestEmpty_thenReturn400Status() throws Exception {

		String userId = "userId";
		String content = "";

		mockMvc.perform(post(ControllerInterfaceTestingConstant.CREATE_CONTROLLER_BASE_URL)
				.header(HeaderConstant.HEADER_USER_ID_AUDIT, userId).contentType(MediaType.APPLICATION_JSON)
				.content(content)).andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPICreateWithRequestFieldTextNull_thenReturn400Status() throws Exception {

		String userId = "userId";
		TestingCreateRequest request = TestingCreateRequest.createSampleDefault();
		request.setText(null);
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.CREATE_CONTROLLER_BASE_URL)
				.header(HeaderConstant.HEADER_USER_ID_AUDIT, userId).contentType(MediaType.APPLICATION_JSON)
				.content(content)).andExpect(status().isBadRequest());
	}

	@Test
	public void whenCallAPICreateWithUserIdAndRequest_thenReturn200Status() throws Exception {

		String userId = "userId";
		TestingCreateRequest request = TestingCreateRequest.createSampleDefault();
		String content = RestConverterUtil.covertToJsonResponse(request);

		mockMvc.perform(post(ControllerInterfaceTestingConstant.CREATE_CONTROLLER_BASE_URL)
				.header(HeaderConstant.HEADER_USER_ID_AUDIT, userId).contentType(MediaType.APPLICATION_JSON)
				.content(content)).andExpect(status().isOk());
	}
}

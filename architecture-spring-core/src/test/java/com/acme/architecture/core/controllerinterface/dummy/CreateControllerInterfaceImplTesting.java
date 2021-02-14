package com.acme.architecture.core.controllerinterface.dummy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.architecture.core.controllerinterface.CreateControllerInterface;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.request.TestingCreateRequest;
import com.acme.architecture.core.controllerinterface.dummy.response.TestingResponse;

@RestController
@RequestMapping(ControllerInterfaceTestingConstant.CREATE_CONTROLLER_BASE_URL)
public class CreateControllerInterfaceImplTesting
		implements CreateControllerInterface<TestingResponse, TestingCreateRequest> {

	@Override
	public ResponseEntity<TestingResponse> create(TestingCreateRequest request,  String userId) {

		assertNotNull(userId);
		assertFalse(userId.isEmpty());

		assertNotNull(request);
		assertNotNull(request.getText());

		return ResponseEntity.ok(TestingResponse.createSampleDefault());
	}

}

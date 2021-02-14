package com.acme.architecture.core.controllerinterface.dummy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.architecture.core.controllerinterface.UpdateControllerInterface;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.request.TestingUpdateRequest;
import com.acme.architecture.core.controllerinterface.dummy.response.TestingResponse;

@RestController
@RequestMapping(ControllerInterfaceTestingConstant.UPDATE_CONTROLLER_BASE_URL)
public class UpdateControllerInterfaceImplTesting
		implements UpdateControllerInterface<TestingResponse, TestingUpdateRequest> {

	@Override
	public ResponseEntity<TestingResponse> update(int id, TestingUpdateRequest request, String userId) {

		assertNotNull(id);

		assertNotNull(userId);
		assertFalse(userId.isEmpty());

		assertNotNull(request);
		assertNotNull(request.getId());
		assertNotNull(request.getText());

		return ResponseEntity.ok(TestingResponse.createSampleDefault());
	}

}

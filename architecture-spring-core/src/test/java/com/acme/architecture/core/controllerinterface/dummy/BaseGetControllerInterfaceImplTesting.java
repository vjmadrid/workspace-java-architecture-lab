package com.acme.architecture.core.controllerinterface.dummy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.architecture.core.controllerinterface.BaseGetControllerInterface;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.response.TestingResponse;

@RestController
@RequestMapping(ControllerInterfaceTestingConstant.GET_CONTROLLER_BASE_URL)
public class BaseGetControllerInterfaceImplTesting implements BaseGetControllerInterface<TestingResponse> {

	@Override
	public ResponseEntity<List<TestingResponse>> findAll() {

		return ResponseEntity.ok(TestingResponse.createSampleDefaultList());
	}

	@Override
	public ResponseEntity<TestingResponse> findById(int id) {
		
		assertNotNull(id);
		
		return ResponseEntity.ok(TestingResponse.createSampleDefault());
	}

	@Override
	public ResponseEntity<List<TestingResponse>> findByIdList(List<String> ids) {
		
		assertNotNull(ids);
		
		return ResponseEntity.ok(TestingResponse.createSampleDefaultList());
	}

}

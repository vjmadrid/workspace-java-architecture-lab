package com.acme.architecture.core.controllerinterface.dummy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.architecture.core.controllerinterface.FilterLanguageControllerInterface;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.response.TestingResponse;

@RestController
@RequestMapping(ControllerInterfaceTestingConstant.LANGUAGE_CONTROLLER_BASE_URL)
public class FilterLanguageControllerInterfaceImplTesting
		implements FilterLanguageControllerInterface<TestingResponse> {

	@Override
	public ResponseEntity<List<TestingResponse>> findAll(int languageId) {
		
		assertNotNull(languageId);
		
		return ResponseEntity.ok(TestingResponse.createSampleDefaultList());
	}

	@Override
	public ResponseEntity<TestingResponse> findById(int id, int languageId) {
		
		assertNotNull(id);
		assertNotNull(languageId);
		
		return ResponseEntity.ok(TestingResponse.createSampleDefault());
	}

	@Override
	public ResponseEntity<List<TestingResponse>> findByIdListLanguage(List<String> ids, int languageId) {
		
		assertNotNull(ids);
		assertNotNull(languageId);
		
		return ResponseEntity.ok(TestingResponse.createSampleDefaultList());
	}

}

package com.acme.architecture.core.controllerinterface.dummy;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.architecture.core.constant.SearchControllerConstant;
import com.acme.architecture.core.controllerinterface.SearchControllerInterface;
import com.acme.architecture.core.controllerinterface.dummy.constant.ControllerInterfaceTestingConstant;
import com.acme.architecture.core.controllerinterface.dummy.request.TestingQueryRequest;
import com.acme.architecture.core.controllerinterface.dummy.response.TestingResponse;


@RestController
@RequestMapping(ControllerInterfaceTestingConstant.SEARCH_CONTROLLER_BASE_URL)
public class SearchControllerInterfaceImplTesting
		implements SearchControllerInterface<TestingResponse, TestingQueryRequest> {

	@Override
	public ResponseEntity<List<TestingResponse>> search(TestingQueryRequest request, String criteriaMode,
			String criteriaString) {

		assertNotNull(request);
		assertTrue(SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE_ALLOWABLE_VALUES.contains(criteriaMode));
		assertTrue(SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING_ALLOWABLE_VALUES.contains(criteriaString));

		return ResponseEntity.ok(TestingResponse.createSampleDefaultList());
	}

}

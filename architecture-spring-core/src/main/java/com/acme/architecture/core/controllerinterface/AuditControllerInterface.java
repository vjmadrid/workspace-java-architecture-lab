package com.acme.architecture.core.controllerinterface;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.acme.architecture.core.constant.SearchControllerConstant;

import io.swagger.annotations.ApiOperation;

@Validated
public interface AuditControllerInterface<T, R> {

	@ApiOperation(notes = "<h4>Search for a audit dates range (dateFrom and dateTo) from the parent or from detail</h4>\r\n" + 
			"<ul>\r\n" + 
			"<li>If <span style=\"text-decoration: underline;\">both dates are present</span> then searches between them&nbsp;</li>\r\n" + 
			"<ul>\r\n" + 
			"<li>If only <em><strong>dateFrom</strong> </em>is present then searches from date on</li>\r\n" + 
			"<li>If only <em><strong>dateTo</strong> </em>is present then searches from the beginning of time to the date indicated</li>\r\n" + 
			"</ul>\r\n" + 
			"</li>\r\n" + 
			"<li><strong>auditDate</strong> represents if you are searching by creation date (CREATED) or modification date (MODIFIED)</li>\r\n" + 
			"<li><strong>auditLevel</strong> represents if you are searching in the parent entity (PARENT) or its details (DETAIL)</li>\r\n" + 
			"<li><span style=\"color: #ff0000;\">Note:</span> If you are searching for detail you have to report the <em><strong>language</strong></em>.</li>\r\n" + 
			"</ul>", value = "Search for a audit dates range (dateFrom and dateTo) from the parent or from detail")
	@PostMapping(value = SearchControllerConstant.BASE_AUDIT_URL)
	public ResponseEntity<List<T>> search(@Valid @RequestBody R request);

}

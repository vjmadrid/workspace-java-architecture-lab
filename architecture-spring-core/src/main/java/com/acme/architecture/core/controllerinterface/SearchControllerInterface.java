package com.acme.architecture.core.controllerinterface;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.acme.architecture.core.constant.SearchControllerConstant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
public interface SearchControllerInterface<E, U> {

	@ApiOperation(notes = "<h4>Search by entity atributes or its detail<br /><br /></h4>\r\n" + "<div>\r\n" + "<ul>\r\n"
			+ "<li><strong><span class=\"cm-line\">criteriaMode</span></strong>: represents the type of search\r\n"
			+ "<ul>\r\n"
			+ "<li><span class=\"cm-line\">If <em><strong>ALL</strong> </em>is selected then all atributes have to match in the query (Behaviour as <span style=\"text-decoration: underline;\">AND</span>)  </span></li>\r\n"
			+ "<li><span class=\"cm-line\">If <em><strong>ANY</strong> </em>is selected then one or more atributtes have to match in the query (Behaviour as <span style=\"text-decoration: underline;\">OR</span>) </span></li>\r\n"
			+ "</ul>\r\n" + "</li>\r\n"
			+ "<li><span class=\"cm-line\"><strong>criteriaString</strong> represents the type of search to be performed on the <strong><em>strings</em></strong> (Behaviour as EXACT, STARTING, ENDING, CONTAINING, REGEX) </span></li>\r\n"
			+ "<li><span class=\"cm-line\"><strong>fullQueryRequest</strong> represents the atributes with wich you can search. The detail of the list can be <span style=\"text-decoration: underline;\">empty</span> or contains only a <span style=\"text-decoration: underline;\">simple element</span>.</span></li>\r\n"
			+ "</ul>\r\n" + "</div>", value = "Search by entity atributes or its detail")
	@PostMapping(value = SearchControllerConstant.BASE_SEARCH_URL, params = {
			SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE,
			SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING })
	public ResponseEntity<List<E>> search(@Valid @RequestBody U request,
			@ApiParam(required = true, allowableValues = SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE_ALLOWABLE_VALUES) @Pattern(regexp = SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE_PATTERN_VALUES, flags = Pattern.Flag.CASE_INSENSITIVE) @RequestParam(name = SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, required = true) String criteriaMode,
			@ApiParam(required = true, allowableValues = SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING_ALLOWABLE_VALUES) @Pattern(regexp = SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING_PATTERN_VALUES, flags = Pattern.Flag.CASE_INSENSITIVE) @RequestParam(name = SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, required = true) String criteriaString);

}

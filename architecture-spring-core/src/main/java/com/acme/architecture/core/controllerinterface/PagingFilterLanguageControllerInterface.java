package com.acme.architecture.core.controllerinterface;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.acme.architecture.core.constant.PageableConstant;
import com.acme.architecture.core.constant.SearchControllerConstant;
import com.acme.architecture.core.pageable.response.PageableResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
public interface PagingFilterLanguageControllerInterface<T> extends PagingControllerInterface {

	@ApiOperation(notes = "<h4>Search by entity and/or by audit atributes or its detail with pagination<br /><br /></h4>\r\n"
			+ "<div> <strong>Search information</strong>\r\n" + "<ul>\r\n"
			+ "<li><strong><span class=\"cm-line\">criteriaMode</span></strong>: represents the type of search\r\n" + "<ul>\r\n"
			+ "<li><span class=\"cm-line\">If <em><strong>ALL</strong> </em>is selected then all atributes have to match in the query (Behaviour as <span style=\"text-decoration: underline;\">AND</span>) </span></li>\r\n"
			+ "<li><span class=\"cm-line\">If <em><strong>ANY</strong> </em>is selected then one or more atributtes have to match in the query (Behaviour as <span style=\"text-decoration: underline;\">OR</span>) </span></li>\r\n"
			+ "</ul>\r\n" + "</li>\r\n"
			+ "<li><span class=\"cm-line\"><strong>criteriaString</strong>: represents the type of search to be performed on the <strong><em>strings</em></strong> (Behaviour as EXACT, STARTING, ENDING, CONTAINING, REGEX) </span></li>\r\n"
			+ "<li><span class=\"cm-line\"><strong>fullQueryRequest</strong>: represents the atributes with wich you can search. The detail of the list can be  <span style=\"text-decoration: underline;\">empty</span> or contains only a <span style=\"text-decoration: underline;\">simple element</span></span></li>\r\n"
			+ "</ul>\r\n" + "</div>"
			+ "<div> <strong>Page information</strong>\n" + "<ul>\n"
			+ "<li><strong>Page Number</strong>: represents the page you are looking for</li>\n"
			+ "<li><strong>Page Size</strong>: represents the maximun number of elements per page</li>\n"
			+ "<li><strong>Sort Property</strong>: represents by which property you want to sort the information for</li>\n"
			+ "<li><strong>Sort Order</strong>: represents in witch order you want to receive the information -> <strong>ASC</strong> for Ascending or <strong>DESC</strong> for descending order</li>\n"
			+ "<li><strong>Language Id</strong>: represents in wich language you want to search</li>\n"
			+ "</ul>\n" + " </div>"
			+ "<div><strong>Audit information</strong>" + "<ul>\r\n"
			+ "<li>If <span style=\"text-decoration: underline;\">both dates are present</span> it searches between them &nbsp;</li>\r\n"
			+ "<ul>\r\n"
			+ "<li>If only <em><strong>dateFrom</strong> </em>is present then searches from date on</li>\r\n"
			+ "<li>If only <em><strong>dateTo</strong> </em>is present then searches from the beginning of time to the date indicated</li>\r\n"
			+ "</ul>\r\n" + "</li>\r\n"
			+ "<li><strong>auditDate</strong> represents if you are searching by creation date (CREATED) or modification date (MODIFIED)</li>\r\n"
			+ "<li><strong>auditLevel</strong> represents if you are searching in the parent entity (PARENT) or its details (DETAIL)</li>\r\n"
			+ "<li><span style=\"color: #ff0000;\">Note:</span> if you are searching for detail you have to report the <em><strong>language</strong></em></li>\r\n"
			+ "</ul></div>", value = "Search by entity and/or by audit atributes or its detail")
	@PostMapping(path = PageableConstant.GET_PAGING, params = { PageableConstant.QUERY_PARAM_PAGE_NUMBER,
			PageableConstant.QUERY_PARAM_PAGE_SIZE, PageableConstant.QUERY_PARAM_SORT_PROPERTY,
			PageableConstant.QUERY_PARAM_SORT_ORDER, SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE,
			SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING })
	public ResponseEntity<PageableResponse> searchPaginated(
			@ApiParam(required = true) @RequestParam(name = PageableConstant.QUERY_PARAM_PAGE_NUMBER, required = true) @Min(value = PageableConstant.MIN_PAGE_NUMBER) @Max(Integer.MAX_VALUE) Integer pageNumber,
			@ApiParam(required = true) @RequestParam(name = PageableConstant.QUERY_PARAM_PAGE_SIZE, required = true) @Min(value = PageableConstant.MIN_PAGE_SIZE) @Max(Integer.MAX_VALUE) Integer pageSize,
			@ApiParam(required = true) @RequestParam(name = PageableConstant.QUERY_PARAM_SORT_PROPERTY, required = true) @Size(min = 1, max = 50) String sortProperty,
			@ApiParam(required = true, allowableValues = PageableConstant.SORT_ORDER_ASC + ","
					+ PageableConstant.SORT_ORDER_DESC) @Pattern(regexp = PageableConstant.SORT_ORDER_ASC + "|"
							+ PageableConstant.SORT_ORDER_DESC, flags = Pattern.Flag.CASE_INSENSITIVE) @RequestParam(name = PageableConstant.QUERY_PARAM_SORT_ORDER, required = true) String sortOrder,
			@Valid @RequestBody T fullQueryRequest,
			@ApiParam(required = true, allowableValues = SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE_ALLOWABLE_VALUES) @Pattern(regexp = SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE_PATTERN_VALUES, flags = Pattern.Flag.CASE_INSENSITIVE) @RequestParam(name = SearchControllerConstant.QUERY_PARAM_CRITERIA_MODE, required = true) String criteriaMode,
			@ApiParam(required = true, allowableValues = SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING_ALLOWABLE_VALUES) @Pattern(regexp = SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING_PATTERN_VALUES, flags = Pattern.Flag.CASE_INSENSITIVE) @RequestParam(name = SearchControllerConstant.QUERY_PARAM_CRITERIA_STRING, required = true) String criteriaString);

}

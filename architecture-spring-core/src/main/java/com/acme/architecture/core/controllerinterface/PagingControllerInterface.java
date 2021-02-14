package com.acme.architecture.core.controllerinterface;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acme.architecture.core.constant.PageableConstant;
import com.acme.architecture.core.pageable.response.PageableResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
public interface PagingControllerInterface {

	@ApiOperation(notes = "<h4>Find all entities paginated by pagination params <br /><br /></h4>\r\n" + "<div>\n"
			+ "<ul>\n" + "<li><strong>Page Number</strong>: represents the page you are looking for</li>\n"
			+ "<li><strong>Page Size</strong>: represents the maximun number of elements per page</li>\n"
			+ "<li><strong>Sort Property</strong>: represents by which property you want to sort the information for</li>\n"
			+ "<li><strong>Sort Order</strong>: represents in witch order you want to receive the information <strong>ASC</strong> for Ascending or <strong>DESC</strong> for descending order</li>\n"
			+ "<li><strong>Language Id</strong>: represents in wich language you want to search</li>\n" + "</ul>\n"
			+ " </div>", value = "Search by entity and/or by audit atributes or its detail")
	@GetMapping(path = PageableConstant.GET_PAGING, params = { PageableConstant.QUERY_PARAM_PAGE_NUMBER,
			PageableConstant.QUERY_PARAM_PAGE_SIZE, PageableConstant.QUERY_PARAM_SORT_PROPERTY,
			PageableConstant.QUERY_PARAM_SORT_ORDER, PageableConstant.LANGUAGE_ID_QUERY_PARAM })
	public ResponseEntity<PageableResponse> findPaginated(
			@ApiParam(required = true) @RequestParam(name = PageableConstant.QUERY_PARAM_PAGE_NUMBER, required = true) @Min(value = PageableConstant.MIN_PAGE_NUMBER) @Max(Integer.MAX_VALUE) Integer pageNumber,
			@ApiParam(required = true) @RequestParam(name = PageableConstant.QUERY_PARAM_PAGE_SIZE, required = true) @Min(value = PageableConstant.MIN_PAGE_SIZE) @Max(Integer.MAX_VALUE) Integer pageSize,
			@ApiParam(required = true) @RequestParam(name = PageableConstant.QUERY_PARAM_SORT_PROPERTY, required = true) @Size(min = 1, max = 50) String sortProperty,
			@ApiParam(required = true, allowableValues = PageableConstant.SORT_ORDER_ASC + ","
					+ PageableConstant.SORT_ORDER_DESC) @Pattern(regexp = PageableConstant.SORT_ORDER_ASC + "|"
							+ PageableConstant.SORT_ORDER_DESC, flags = Pattern.Flag.CASE_INSENSITIVE) @RequestParam(name = PageableConstant.QUERY_PARAM_SORT_ORDER, required = true) String sortOrder,
			@ApiParam(required = true) @RequestParam(name = PageableConstant.LANGUAGE_ID_QUERY_PARAM, defaultValue = PageableConstant.LANGUAGE_ID_QUERY_PARAM_DEFAULT) @Min(0) @Max(Short.MAX_VALUE) Short languageId);

}

package com.acme.architecture.core.pageable.response;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.acme.architecture.common.constant.PatternsConstant;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageableResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8042297298238623801L;
	
	private static final String FIELD_LINK_NEXT_PAGE = "nextPage";
	private static final String FIELD_LINK_PREVIOUS_PAGE = "prevPage";
	private static final String FIELD_PAGE_TOTAL_ROWS = "totalRows";
	private static final String FIELD_PAGE_NUMBER = "pageNumber";
	private static final String FIELD_PAGE_SIZE = "pageSize";
	private static final String FIELD_PAGE_CONTENT = "content";

	@Pattern(regexp = PatternsConstant.URL)
	@Size(min = 8, max = Integer.MAX_VALUE)
	@JsonProperty(FIELD_LINK_NEXT_PAGE)
	private String next;

	@Pattern(regexp = PatternsConstant.URL)
	@Size(min = 8, max = Integer.MAX_VALUE)
	@JsonProperty(FIELD_LINK_PREVIOUS_PAGE)
	private String prev;

	@Min(0)
	@Max(Integer.MAX_VALUE)
	@JsonProperty(FIELD_PAGE_TOTAL_ROWS)
	private Integer totalPages;

	@Min(0)
	@Max(Integer.MAX_VALUE)
	@JsonProperty(FIELD_PAGE_NUMBER)
	private Integer pageNumber;

	@Min(1)
	@Max(Integer.MAX_VALUE)
	@JsonProperty(FIELD_PAGE_SIZE)
	private Integer pageSize;

	@JsonProperty(FIELD_PAGE_CONTENT)
	private List<?> content;

}

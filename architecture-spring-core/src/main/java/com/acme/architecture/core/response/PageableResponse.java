package com.acme.architecture.core.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageableResponse {

	private static final String FIELD_LINK_NEXT_PAGE = "nextPage";
	private static final String FIELD_LINK_PREVIOUS_PAGE = "prevPage";
	private static final String FIELD_PAGE_TOTAL_ROWS = "totalRows";
	private static final String FIELD_PAGE_NUMBER = "pageNumber";
	private static final String FIELD_PAGE_SIZE = "pageSize";
	private static final String FIELD_PAGE_CONTENT = "content";

	@JsonProperty(FIELD_LINK_NEXT_PAGE)
	private String next;

	@JsonProperty(FIELD_LINK_PREVIOUS_PAGE)
	private String prev;

	@JsonProperty(FIELD_PAGE_TOTAL_ROWS)
	private Integer totalPages;

	@JsonProperty(FIELD_PAGE_NUMBER)
	private Integer pageNumber;

	@JsonProperty(FIELD_PAGE_SIZE)
	private Integer pageSize;

	@JsonProperty(FIELD_PAGE_CONTENT)
	private List<?> content;

}

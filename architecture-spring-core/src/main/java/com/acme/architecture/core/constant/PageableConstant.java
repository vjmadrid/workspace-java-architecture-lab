package com.acme.architecture.core.constant;

public final class PageableConstant {

	private PageableConstant() {

	}

	public static final String QUERY_PARAM_PAGE_NUMBER = "pageNumber";
	public static final String QUERY_PARAM_PAGE_SIZE = "pageSize";
	public static final String QUERY_PARAM_SORT_PROPERTY = "sortProperty";
	public static final String QUERY_PARAM_SORT_ORDER = "sortOrder";

	public static final long MIN_PARAM_PAGE_NUMBER = 0;
	public static final long MIN_PARAM_PAGE_SIZE = 1;

}

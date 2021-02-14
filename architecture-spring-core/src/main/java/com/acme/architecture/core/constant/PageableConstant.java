package com.acme.architecture.core.constant;

public final class PageableConstant {

	private PageableConstant() {

	}

	public static final String QUERY_PARAM_PAGE_NUMBER = "pageNumber";
	public static final String QUERY_PARAM_PAGE_SIZE = "pageSize";
	public static final String QUERY_PARAM_SORT_PROPERTY = "sortProperty";
	public static final String QUERY_PARAM_SORT_ORDER = "sortOrder";
	public static final String SORT_ORDER_DESC = "DESC";
	public static final String SORT_ORDER_ASC = "ASC";
	public static final String GET_PAGING = "/page";

	public static final long MIN_PAGE_NUMBER = 0;
	public static final long MIN_PAGE_SIZE = 1;

	public static final String LANGUAGE_ID_QUERY_PARAM = "languageId";
	// Default language id -> 1 : ENGLISH
	public static final String LANGUAGE_ID_QUERY_PARAM_DEFAULT = "1";

}

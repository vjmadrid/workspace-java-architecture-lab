package com.acme.architecture.core.constant;

public final class SearchControllerConstant {

	private SearchControllerConstant() {

	}

	public static final String BASE_SEARCH_URL = "/search";
	public static final String BASE_AUDIT_URL = SearchControllerConstant.BASE_SEARCH_URL + "/audit";

	public static final String QUERY_PARAM_CRITERIA_MODE = "criteriaMode";
	public static final String QUERY_PARAM_CRITERIA_MODE_ALLOWABLE_VALUES = "ALL,ANY";
	public static final String QUERY_PARAM_CRITERIA_MODE_PATTERN_VALUES = "ALL|ANY";

	public static final String QUERY_PARAM_CRITERIA_STRING = "criteriaString";
	public static final String QUERY_PARAM_CRITERIA_STRING_ALLOWABLE_VALUES = "EXACT,STARTING,ENDING,CONTAINING,REGEX";
	public static final String QUERY_PARAM_CRITERIA_STRING_PATTERN_VALUES = "EXACT|STARTING|ENDING|CONTAINING|REGEX";

}

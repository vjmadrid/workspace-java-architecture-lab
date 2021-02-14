package com.acme.architecture.core.constant;

import com.acme.architecture.common.constant.PatternsConstant;

public final class ControllerConstant {

	private ControllerConstant() {

	}

	public static final String ID_PATH_VARIABLE = "id";
	public static final String ID_PATH_VARIABLE_PARAM = "/{" + ID_PATH_VARIABLE + "}";
	public static final String IDS_QUERY_PARAM = "ids";

	public static final String BASE_FILTER_URL = "/filter";
	public static final String BASE_LIST_URL = "/list";

	public static final String BASE_LANGUAGE_URL = "/language";
	public static final String BASE_LANGUAGE_ENTITY_ID_URL = ID_PATH_VARIABLE_PARAM + BASE_LANGUAGE_URL;
	public static final String BASE_LANGUAGE_LIST_URL = BASE_LANGUAGE_URL + BASE_LIST_URL;

	public static final String LANGUAGE_ID_QUERY_PARAM = PageableConstant.LANGUAGE_ID_QUERY_PARAM;
	// Default language id -> 1 : ENGLISH
	public static final String LANGUAGE_ID_QUERY_PARAM_DEFAULT = PageableConstant.LANGUAGE_ID_QUERY_PARAM_DEFAULT;

	// REGEXP
	public static final String ANY = PatternsConstant.ANY;
	public static final String ALPHABETIC_CHARACTER_ONLY = PatternsConstant.ALPHABETIC_CHARACTER_ONLY;
	public static final String NUMERIC_CHARACTER_ONLY = PatternsConstant.NUMERIC_CHARACTER_ONLY;
	public static final String ALPHABETIC_CHARACTER_AND_NUMERIC = PatternsConstant.ALPHABETIC_CHARACTER_AND_NUMERIC;

}

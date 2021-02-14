package com.acme.architecture.core.constant;

import org.springframework.data.domain.ExampleMatcher.MatchMode;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

public final class QueryCriteriaConstant {

	private QueryCriteriaConstant() {

	}
	
	public static final StringMatcher STRING_MATHCER_DEFAULT = StringMatcher.CONTAINING;
	public static final Boolean IGNORE_CASE_DEFAULT = Boolean.TRUE;
	
	public static final MatchMode MODE_ALL = MatchMode.ALL;
	public static final MatchMode MODE_ANY = MatchMode.ANY;

	
}

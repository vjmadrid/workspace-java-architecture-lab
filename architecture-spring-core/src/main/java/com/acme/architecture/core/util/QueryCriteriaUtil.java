package com.acme.architecture.core.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.MatchMode;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.acme.architecture.core.constant.QueryCriteriaConstant;

public final class QueryCriteriaUtil {

	protected QueryCriteriaUtil() {
		throw new IllegalStateException("QueryCriteriaUtil");
	}

	public static ExampleMatcher getExampleMatcherDefault() {
		return ExampleMatcher.matchingAny().withStringMatcher(QueryCriteriaConstant.STRING_MATHCER_DEFAULT)
				.withIgnoreCase(QueryCriteriaConstant.IGNORE_CASE_DEFAULT);
	}

	public static ExampleMatcher getExampleMatcherByCriteria(String queryCriteriaMatchMode,
			String queryCriteriaStringMatcher) {

		if (queryCriteriaMatchMode == null && queryCriteriaStringMatcher == null) {
			return getExampleMatcherDefault();
		}

		MatchMode matchModeDefault = getMatchModeByCriteria(queryCriteriaMatchMode);
		StringMatcher stringMatcher = getStringMatcherByCriteria(queryCriteriaStringMatcher);
		ExampleMatcher exampleMatcher = null;
		
		
		
		
		
//		switch (matchModeDefault) {
//
//		
//		
//		case ALL:
//			exampleMatcher = ExampleMatcher.matchingAll().withStringMatcher(stringMatcher)
//					.withIgnoreCase(QueryCriteriaConstant.IGNORE_CASE_DEFAULT);
//			break;
//		case ANY:
//			exampleMatcher = ExampleMatcher.matchingAny().withStringMatcher(stringMatcher)
//					.withIgnoreCase(QueryCriteriaConstant.IGNORE_CASE_DEFAULT);
//			break;
//		default:
//			// do nothing
//		}
		
		
//		switch (matchModeDefault) {
//
//		
//		
//		case QueryCriteriaConstant.MODE_ALL:
//			exampleMatcher = ExampleMatcher.matchingAll().withStringMatcher(stringMatcher)
//					.withIgnoreCase(QueryCriteriaConstant.IGNORE_CASE_DEFAULT);
//			break;
//		case ANY:
//			exampleMatcher = ExampleMatcher.matchingAny().withStringMatcher(stringMatcher)
//					.withIgnoreCase(QueryCriteriaConstant.IGNORE_CASE_DEFAULT);
//			break;
//		default:
//			// do nothing
//		}
		
		if ( MatchMode.ALL.equals(matchModeDefault)) {
			exampleMatcher = ExampleMatcher.matchingAll().withStringMatcher(stringMatcher)
					.withIgnoreCase(QueryCriteriaConstant.IGNORE_CASE_DEFAULT);
		} else if ( MatchMode.ANY.equals(matchModeDefault)) {
			exampleMatcher = ExampleMatcher.matchingAny().withStringMatcher(stringMatcher)
					.withIgnoreCase(QueryCriteriaConstant.IGNORE_CASE_DEFAULT);
		}
			

		return exampleMatcher;
	}

	public static MatchMode getMatchModeByCriteria(String queryCriteriaMatchMode) {

		// Default config
		MatchMode matchMode = MatchMode.ANY;

		if (queryCriteriaMatchMode != null) {
			for (MatchMode searchMatchMode : MatchMode.values()) {
				if (searchMatchMode.name().equalsIgnoreCase(queryCriteriaMatchMode)) {
					matchMode = MatchMode.valueOf(queryCriteriaMatchMode.toUpperCase());
				}
			}
		}

		return matchMode;
	}

	protected static StringMatcher getStringMatcherByCriteria(String queryCriteriaStringMatcher) {

		// Default config
		StringMatcher stringMatcher = StringMatcher.CONTAINING;

		if (queryCriteriaStringMatcher != null) {
			for (StringMatcher searchStringMatcher : StringMatcher.values()) {
				if (searchStringMatcher.name().equalsIgnoreCase(queryCriteriaStringMatcher)) {
					stringMatcher = StringMatcher.valueOf(queryCriteriaStringMatcher.toUpperCase());
				}
			}
		}

		return stringMatcher;
	}

	public static Set<Short> filterIdShortByMatchMode(String criteriaMode, Set<Short> listByExampleShort,
			Set<Short> listByAuditShort) {

		Set<Integer> listByExample = listByExampleShort.stream().map(Short::intValue).collect(Collectors.toSet());
		Set<Integer> listByAudit = listByAuditShort.stream().map(Short::intValue).collect(Collectors.toSet());

		Set<Integer> listResult = filterIdByMatchMode(criteriaMode, listByExample, listByAudit);

		return listResult.stream().map(Integer::shortValue).collect(Collectors.toSet());
	}


	public static Set<Integer> filterIdByMatchMode(String criteriaMode, Set<Integer> listByExample,
			Set<Integer> listByAudit) {

		MatchMode mode = getMatchModeByCriteria(criteriaMode);

		Set<Integer> ids = new HashSet<>();

//		switch (mode) {
//
//		case ALL:
//
//			if (CollectionUtils.isEmpty(listByAudit)) {
//				ids = listByExample;
//			} else if (CollectionUtils.isEmpty(listByExample)) {
//				ids = listByAudit;
//			} else {
//				// stream the list and use the set to filter it
//				ids = listByExample.stream().filter(e -> listByAudit.contains(e)).collect(Collectors.toSet());
//			}
//
//			break;
//
//		case ANY:
//
//			ids.addAll(listByExample);
//			ids.addAll(listByAudit);
//
//			break;
//
//		default:
//			// do nothing
//		}
//		
		if ( MatchMode.ALL.equals(mode)) {
			if (CollectionUtils.isEmpty(listByAudit)) {
				ids = listByExample;
			} else if (CollectionUtils.isEmpty(listByExample)) {
				ids = listByAudit;
			} else {
				// stream the list and use the set to filter it
				ids = listByExample.stream().filter(e -> listByAudit.contains(e)).collect(Collectors.toSet());
			}
		} else if ( MatchMode.ANY.equals(mode)) {
			ids.addAll(listByExample);
			ids.addAll(listByAudit);
		}

		return ids;
	}

}


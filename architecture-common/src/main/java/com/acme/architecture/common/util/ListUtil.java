package com.acme.architecture.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public final class ListUtil {

	protected ListUtil() {
		throw new IllegalStateException("AcmeListUtil");
	}

	public static List<String> getNumericList(final List<String> strList) {

		if (strList == null || strList.isEmpty())
			return Collections.emptyList();

		return strList.stream().filter(ListUtil::isNumeric).collect(Collectors.toList());
	}
	
	
	public static boolean isNumeric(final String str) {

		if (str == null || str.length() == 0) {
			return false;
		}

		return str.chars().allMatch(Character::isDigit);

	}
	
	public static List<String> getTokensByStringTokenizer(final String str) {

		if (str == null || str.length() == 0) {
			return Collections.emptyList();
		}

		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, ",");
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

}

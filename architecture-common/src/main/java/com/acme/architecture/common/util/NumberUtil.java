package com.acme.architecture.common.util;

public final class NumberUtil {

	protected NumberUtil() {
		throw new IllegalStateException("NumberUtil");
	}

	public static Short intValueToShortValue(int number) {

		Integer integerNumber = Integer.valueOf(number);

		return integerNumber.shortValue();

	}

}

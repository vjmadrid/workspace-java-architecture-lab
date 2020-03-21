package com.acme.architecture.common.util.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CollectionConverter {
	
	protected CollectionConverter() {
		throw new IllegalStateException("AcmeCollectionConverter");
	}

	public static <T> List<T> toList(final Iterable<T> iterable) {

		if (Objects.isNull(iterable)) {
			return Collections.emptyList();
		}

		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}
	
	public static <T> Set<T> toSet(final List<T> list) {

		if (Objects.isNull(list) || list.isEmpty()) {
			return Collections.emptySet();
		}

		return list.stream().collect(Collectors.toSet());
	}
	
}

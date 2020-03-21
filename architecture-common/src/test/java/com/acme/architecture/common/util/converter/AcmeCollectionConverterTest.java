package com.acme.architecture.common.util.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AcmeCollectionConverterTest {

	private Integer simpleObject;

	private List<Integer> simpleObjectList;

	@BeforeEach
	public void init() {
		simpleObject = Integer.valueOf(0);
		simpleObjectList = Arrays.asList(simpleObject, Integer.valueOf(1));
	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new AcmeCollectionConverter();
		});
	}

	@Test
	public void whenCallToListWithNull_ThenReturnEmptyList() {

		List<?> result = AcmeCollectionConverter.toList(null);
		assertNotNull(result);
		assertThat(result).isEmpty();
	}

	@Test
	public void whenCallToListWithOneElement_ThenReturnListWithOneElement() {

		List<Integer> oneElementList = Arrays.asList(simpleObject);

		List<Integer> result = AcmeCollectionConverter.toList(oneElementList);

		assertNotNull(result);
		assertThat(result).hasSize(1);
		assertThat(result.get(0)).isEqualTo(simpleObject);
	}

	@Test
	public void whenCallToListWithElementList_ThenReturnListWithElementList() {

		List<Integer> result = AcmeCollectionConverter.toList(simpleObjectList);

		assertNotNull(result);
		assertThat(result).hasSize(simpleObjectList.size());
		assertThat(result).isEqualTo(simpleObjectList);
	}
	
	@Test
	public void whenCallToSetWithNull_ThenReturnEmptySet() {

		Set<?> result = AcmeCollectionConverter.toSet(null);
		
		assertNotNull(result);
		assertThat(result).isEmpty();
	}
	
	@Test
	public void whenCallToSetWithEmpty_ThenReturnEmptySet() {

		Set<?> result = AcmeCollectionConverter.toSet(Collections.emptyList());
		
		assertNotNull(result);
		assertThat(result).isEmpty();
	}
	
	@Test
	public void whenCallToSetWithOneElementList_ThenReturnSetWithOneElement() {

		List<Integer> oneElementList = Arrays.asList(simpleObject);

		Set<Integer> result = AcmeCollectionConverter.toSet(oneElementList);

		assertNotNull(result);
		assertThat(result).hasSize(1);
		assertThat(result.contains(simpleObject));
	}

}
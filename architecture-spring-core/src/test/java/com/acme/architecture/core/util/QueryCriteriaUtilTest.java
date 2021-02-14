package com.acme.architecture.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.MatchMode;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

public class QueryCriteriaUtilTest {

	private static String[] validMatchString = { "EXACT", "STARTING", "ENDING", "CONTAINING", "REGEX" };
	private static String[] validMatchMode = { "ALL", "ANY" };

	public static final List<String> CRITERIA_MATCH_STRINGS = Arrays.asList(validMatchString);
	public static final List<String> CRITERIA_MATCH_MODE = Arrays.asList(validMatchMode);

	public static final StringMatcher CRITERIA_STRING_DEFAULT = StringMatcher.CONTAINING;
	public static final MatchMode CRITERIA_MATCH_MODE_DEFAULT = MatchMode.ANY;
	public static final NullHandler CRITERIA_NULL_HANDLER_DEFAULT = NullHandler.IGNORE;
	public static final Boolean CRITERIA_IGNORE_CASE_DEFAULT = Boolean.TRUE;

	public static final Set<Integer> LIST_BY_EXAMPLE = new HashSet<>();
	public static final Set<Integer> LIST_BY_AUDIT = new HashSet<>();

	public static final Integer I_1 = 1;
	public static final Integer I_2 = 2;
	public static final Integer I_3 = 3;

	public static final Set<Short> LIST_BY_EXAMPLE_SHORT = new HashSet<>();
	public static final Set<Short> LIST_BY_AUDIT_SHORT = new HashSet<>();

	public static final Short S_1 = 1;
	public static final Short S_2 = 2;

	@BeforeEach
	public final void setUp() throws IOException {

		LIST_BY_EXAMPLE.add(I_1);
		LIST_BY_EXAMPLE.add(I_2);

		LIST_BY_AUDIT.add(I_2);
		LIST_BY_AUDIT.add(I_3);

		LIST_BY_EXAMPLE_SHORT.add(S_1);
		LIST_BY_AUDIT_SHORT.add(S_2);
	}

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, ()->{
			new QueryCriteriaUtil();
		});
	}

	@Test
	public void whenCallGetExampleMatcherDefault_thenReturnExampleMatcherDefault() {

		ExampleMatcher exampleMatcher = QueryCriteriaUtil.getExampleMatcherDefault();
		validateExampleMatcherDefault(exampleMatcher);
	}

	@Test
	public void whenCallGetExampleMatcherByCriteriaWithNull_thenReturnExampleMatcherDefault() {

		String queryCriteriaMatchMode = null;
		String queryCriteriaStringMatcher = null;
		ExampleMatcher exampleMatcher = QueryCriteriaUtil.getExampleMatcherByCriteria(queryCriteriaMatchMode,
				queryCriteriaStringMatcher);

		validateExampleMatcherDefault(exampleMatcher);
	}

	@Test
	public void whenCallGetExampleMatcherByCriteriaWithMatchModeNull_thenReturnExampleMatcher() {

		String queryCriteriaMatchMode = null;
		String queryCriteriaStringMatcher = "EXACT";
		ExampleMatcher exampleMatcher = QueryCriteriaUtil.getExampleMatcherByCriteria(queryCriteriaMatchMode,
				queryCriteriaStringMatcher);

		assertThat(exampleMatcher).extracting(ExampleMatcher::getMatchMode).isEqualTo(CRITERIA_MATCH_MODE_DEFAULT);
		assertThat(exampleMatcher).extracting(ExampleMatcher::getDefaultStringMatcher)
				.isEqualTo(StringMatcher.valueOf(queryCriteriaStringMatcher));
		validateCommonConfig(exampleMatcher);
	}

	@Test
	public void whenCallGetExampleMatcherByCriteriaWithStringMatcherNull_thenReturnExampleMatcher() {

		String queryCriteriaMatchMode = "ANY";
		String queryCriteriaStringMatcher = null;
		ExampleMatcher exampleMatcher = QueryCriteriaUtil.getExampleMatcherByCriteria(queryCriteriaMatchMode,
				queryCriteriaStringMatcher);

		assertThat(exampleMatcher).extracting(ExampleMatcher::getMatchMode)
				.isEqualTo(MatchMode.valueOf(queryCriteriaMatchMode));
		assertThat(exampleMatcher).extracting(ExampleMatcher::getDefaultStringMatcher)
				.isEqualTo(CRITERIA_STRING_DEFAULT);
		validateCommonConfig(exampleMatcher);
	}

	@Test
	public void whenCallGetExampleMatcherByCriteriaWithAnyAndExact_thenReturnExampleMatcher() {

		String queryCriteriaMatchMode = "ANY";
		String queryCriteriaStringMatcher = "EXACT";
		ExampleMatcher exampleMatcher = QueryCriteriaUtil.getExampleMatcherByCriteria(queryCriteriaMatchMode,
				queryCriteriaStringMatcher);

		assertThat(exampleMatcher).extracting(ExampleMatcher::getMatchMode)
				.isEqualTo(MatchMode.valueOf(queryCriteriaMatchMode));
		assertThat(exampleMatcher).extracting(ExampleMatcher::getDefaultStringMatcher)
				.isEqualTo(StringMatcher.valueOf(queryCriteriaStringMatcher));
		validateCommonConfig(exampleMatcher);
	}

	@Test
	public void whenCallGetExampleMatcherByCriteriaWithAllAndExact_thenReturnExampleMatcher() {

		String queryCriteriaMatchMode = "ALL";
		String queryCriteriaStringMatcher = "EXACT";
		ExampleMatcher exampleMatcher = QueryCriteriaUtil.getExampleMatcherByCriteria(queryCriteriaMatchMode,
				queryCriteriaStringMatcher);

		assertThat(exampleMatcher).extracting(ExampleMatcher::getMatchMode)
				.isEqualTo(MatchMode.valueOf(queryCriteriaMatchMode));
		assertThat(exampleMatcher).extracting(ExampleMatcher::getDefaultStringMatcher)
				.isEqualTo(StringMatcher.valueOf(queryCriteriaStringMatcher));
		validateCommonConfig(exampleMatcher);
	}

	@Test
	public void whenCallGetExampleMatcherByCriteriaWithNoMatcherAndExact_thenReturnExampleMatcher() {

		String queryCriteriaMatchMode = "NOMATCHER";
		String queryCriteriaStringMatcher = "EXACT";
		ExampleMatcher exampleMatcher = QueryCriteriaUtil.getExampleMatcherByCriteria(queryCriteriaMatchMode,
				queryCriteriaStringMatcher);

		assertThat(exampleMatcher).extracting(ExampleMatcher::getMatchMode).isEqualTo(CRITERIA_MATCH_MODE_DEFAULT);
		assertThat(exampleMatcher).extracting(ExampleMatcher::getDefaultStringMatcher)
				.isEqualTo(StringMatcher.valueOf(queryCriteriaStringMatcher));
		validateCommonConfig(exampleMatcher);
	}

	private void validateExampleMatcherDefault(ExampleMatcher exampleMatcher) {

		assertThat(exampleMatcher).extracting(ExampleMatcher::getMatchMode).isEqualTo(CRITERIA_MATCH_MODE_DEFAULT);
		assertThat(exampleMatcher).extracting(ExampleMatcher::getDefaultStringMatcher)
				.isEqualTo(CRITERIA_STRING_DEFAULT);
		validateCommonConfig(exampleMatcher);
	}

	private void validateCommonConfig(ExampleMatcher exampleMatcher) {
		assertThat(exampleMatcher).extracting(ExampleMatcher::getNullHandler).isEqualTo(CRITERIA_NULL_HANDLER_DEFAULT);
		assertThat(exampleMatcher.isIgnoreCaseEnabled()).isEqualTo(CRITERIA_IGNORE_CASE_DEFAULT);
	}

	@Test
	public void whenCallGetStringMatcherByCriteriaWithNull_thenReturnStringMatcherContaining() {

		String queryCriteriaStringMatcher = null;
		StringMatcher stringMatcher = QueryCriteriaUtil.getStringMatcherByCriteria(queryCriteriaStringMatcher);
		assertThat(stringMatcher).isEqualTo(CRITERIA_STRING_DEFAULT);
	}

	@Test
	public void whenCallGetStringMatcherByCriteriaWithEmpty_thenReturnStringMatcherContaining() {

		String queryCriteriaStringMatcher = "";
		StringMatcher stringMatcher = QueryCriteriaUtil.getStringMatcherByCriteria(queryCriteriaStringMatcher);
		assertThat(stringMatcher).isEqualTo(CRITERIA_STRING_DEFAULT);
	}

	@Test
	public void whenCallGetStringMatcherByCriteriaWithNoMatcher_thenReturnStringMatcherContaining() {

		String queryCriteriaStringMatcher = "NOMATCHER";
		StringMatcher stringMatcher = QueryCriteriaUtil.getStringMatcherByCriteria(queryCriteriaStringMatcher);
		assertThat(stringMatcher).isEqualTo(CRITERIA_STRING_DEFAULT);
	}

	@Test
	public void whenCallGetStringMatcherByCriteriaWithValidMatcher_thenReturnStringMatcherContaining() {

		for (String queryCriteriaStringMatcher : validMatchString) {
			StringMatcher stringMatcher = QueryCriteriaUtil.getStringMatcherByCriteria(queryCriteriaStringMatcher);
			assertThat(stringMatcher).extracting(StringMatcher::name).hasToString(queryCriteriaStringMatcher);
		}

	}

	@Test
	public void whenCallGetMatchModeByCriteriaWithNull_thenReturnthenReturnMatchModeAny() {

		String queryCriteriaMatchMode = null;
		MatchMode matchMode = QueryCriteriaUtil.getMatchModeByCriteria(queryCriteriaMatchMode);
		assertThat(matchMode).isEqualTo(CRITERIA_MATCH_MODE_DEFAULT);
	}

	@Test
	public void whenCallGetMatchModeByCriteriaWithEmpty_thenReturnMatchModeAny() {

		String queryCriteriaMatchMode = "";
		MatchMode matchMode = QueryCriteriaUtil.getMatchModeByCriteria(queryCriteriaMatchMode);
		assertThat(matchMode).isEqualTo(CRITERIA_MATCH_MODE_DEFAULT);
	}

	@Test
	public void whenCallGetMatchModeByCriteriaWithNoMatcher_thenReturnMatchModeAny() {

		String queryCriteriaMatchMode = "NOMATCHER";
		MatchMode matchMode = QueryCriteriaUtil.getMatchModeByCriteria(queryCriteriaMatchMode);
		assertThat(matchMode).isEqualTo(CRITERIA_MATCH_MODE_DEFAULT);
	}

	@Test
	public void whenCallGetMatchModeByCriteriaWithValidModes_thenReturnMatchMode() {

		for (String queryCriteriaMatchMode : validMatchMode) {
			MatchMode matchMode = QueryCriteriaUtil.getMatchModeByCriteria(queryCriteriaMatchMode);
			assertThat(matchMode).extracting(MatchMode::name).hasToString(queryCriteriaMatchMode);
		}
	}

	@Test
	public void whenCallFilterIdByMatchModeWithModeAny_thenReturnSetWithElements() {

		Set<Integer> integerSet = QueryCriteriaUtil.filterIdByMatchMode("ANY", LIST_BY_EXAMPLE, LIST_BY_AUDIT);
		assertThat(integerSet).containsAll(LIST_BY_AUDIT);
		assertThat(integerSet).containsAll(LIST_BY_EXAMPLE);
	}

	@Test
	public void whenCallFilterIdByMatchModeWithModeAll_thenReturnSetWithElements() {

		Set<Integer> integerSet = QueryCriteriaUtil.filterIdByMatchMode("ALL", LIST_BY_EXAMPLE, LIST_BY_AUDIT);
		assertThat(integerSet).containsOnly(I_2);
	}

	@Test
	public void whenCallFilterIdByMatchModeWithModeAllAndListByExampleEmpty_thenReturnSetWithElements() {

		Set<Integer> integerSet = QueryCriteriaUtil.filterIdByMatchMode("ALL", new HashSet<>(), LIST_BY_AUDIT);
		assertThat(integerSet).containsAll(LIST_BY_AUDIT);
	}

	@Test
	public void whenCallFilterIdByMatchModeWithModeAllAndListByAuditEmpty_thenReturnSetWithElements() {

		Set<Integer> integerSet = QueryCriteriaUtil.filterIdByMatchMode("ALL", LIST_BY_EXAMPLE, new HashSet<>());
		assertThat(integerSet).containsAll(LIST_BY_EXAMPLE);
	}

	@Test
	public void whenCallFilterIdShortByMatch_thenReturnSetWithElements() {

		Set<Short> shortSet = QueryCriteriaUtil.filterIdShortByMatchMode("ANY", LIST_BY_EXAMPLE_SHORT,
				LIST_BY_AUDIT_SHORT);
		assertThat(shortSet).containsAll(LIST_BY_AUDIT_SHORT);
		assertThat(shortSet).containsAll(LIST_BY_EXAMPLE_SHORT);
	}

}


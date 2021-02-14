package com.acme.architecture.core.pageable.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.acme.architecture.core.pageable.response.PageableResponse;
import com.acme.architecture.core.pageable.util.PageableUtil;
import com.acme.architecture.core.util.example.CoveragePageableUtilClass;

public class PageableUtilTest {

	private static final int PAGE_NUMBER = 0;
	private static final int PAGE_SIZE = 1;
	private static final String PROPERTY_NAME = "id";
	private static final String ORDER_DIRECTION = "desc";

	private static final String BASE_URI = "page?";
	private static final String PAGE_NUMBER_FILTER = "pageNumber=";
	private static final String PAGE_SIZE_FILTER = "pageSize=";
	private static final String SORT_PROPERTY_FILTER = "sortProperty=";
	private static final String SORT_ORDER_FILTER = "sortOrder=";

	private static final String httpUrl = BASE_URI + SORT_PROPERTY_FILTER + PROPERTY_NAME + "&" + SORT_ORDER_FILTER
			+ ORDER_DIRECTION + "&" + PAGE_NUMBER_FILTER + PAGE_NUMBER + "&" + PAGE_SIZE_FILTER + PAGE_SIZE;

	@BeforeEach
	public void init() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter(PAGE_NUMBER_FILTER, "" + PAGE_NUMBER);
		request.addParameter(PAGE_SIZE_FILTER, "" + PAGE_SIZE);
		request.addParameter(SORT_PROPERTY_FILTER, PROPERTY_NAME);
		request.addParameter(SORT_ORDER_FILTER, ORDER_DIRECTION);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, ()->{
			new PageableUtil();
		});
	}

	@Test
	public void whenCallbuildPageableResponse_thenPageableResponseOneOfTwoPage() {

		Integer actualPage = 0;
		Integer totalPages = 2;
		Pageable pageable = PageRequest.of(actualPage, PAGE_SIZE);
		List<Integer> list = Collections.singletonList(1);
		Page<?> body = new PageImpl<>(list, pageable, totalPages);

		PageableResponse pageResponse = PageableUtil.buildPageableResponse(body);

		assertTrue(pageResponse.getContent().size() == 1);

		assertEquals(actualPage, pageResponse.getPageNumber());
		assertEquals(PAGE_SIZE, pageResponse.getPageSize().intValue());
		assertEquals(totalPages, pageResponse.getTotalPages());
		assertNull(pageResponse.getPrev());
		assertNotNull(pageResponse.getNext());
	}

	@Test
	public void whenCallbuildPageableResponse_thenPageableResponseTeoOfTwoPage() {

		Integer actualPage = 1;
		Integer totalPages = 2;
		Pageable pageable = PageRequest.of(actualPage, PAGE_SIZE);
		List<Integer> list = Collections.singletonList(1);
		Page<?> body = new PageImpl<>(list, pageable, totalPages);

		PageableResponse pageResponse = PageableUtil.buildPageableResponse(body);

		assertTrue(pageResponse.getContent().size() == 1);

		assertEquals(actualPage, pageResponse.getPageNumber());
		assertEquals(PAGE_SIZE, pageResponse.getPageSize().intValue());
		assertEquals(totalPages, pageResponse.getTotalPages());
		assertNotNull(pageResponse.getPrev());
		assertNull(pageResponse.getNext());
	}

	@Test
	public void whenCallbuildPageableResponse_thenPageableResponseMultiplePage() {

		Integer actualPage = 1;
		Integer totalPages = 3;
		Pageable pageable = PageRequest.of(actualPage, PAGE_SIZE);
		List<Integer> list = Collections.singletonList(1);
		Page<?> body = new PageImpl<>(list, pageable, totalPages);

		PageableResponse pageResponse = PageableUtil.buildPageableResponse(body);

		assertTrue(pageResponse.getContent().size() == 1);

		assertEquals(actualPage, pageResponse.getPageNumber());
		assertEquals(PAGE_SIZE, pageResponse.getPageSize().intValue());
		assertEquals(totalPages, pageResponse.getTotalPages());
		assertNotNull(pageResponse.getPrev());
		assertNotNull(pageResponse.getNext());
	}

	@Test
	public void whenCallCreatePageRequestUnsortedProperty_thenReturnPageable() {

		Pageable pageable = PageableUtil.createPageRequest(PAGE_NUMBER, PAGE_SIZE, "any", ORDER_DIRECTION,
				CoveragePageableUtilClass.class);
		assertTrue(pageable.isPaged());
		assertEquals(PAGE_NUMBER, pageable.getPageNumber());
		assertEquals(PAGE_SIZE, pageable.getPageSize());
		assertFalse(pageable.getSort().isSorted());
	}

	@Test
	public void whenCallCreatePageRequestSortedProperty_thenReturnPageableSorted() {

		Pageable pageable = PageableUtil.createPageRequest(PAGE_NUMBER, PAGE_SIZE, PROPERTY_NAME, ORDER_DIRECTION,
				CoveragePageableUtilClass.class);
		assertTrue(pageable.isPaged());
		assertEquals(PAGE_NUMBER, pageable.getPageNumber());
		assertEquals(PAGE_SIZE, pageable.getPageSize());
		assertTrue(pageable.getSort().isSorted());

		List<Order> order = pageable.getSort().toList();
		assertTrue(order.size() == 1);
		assertTrue(order.get(0).getDirection().isDescending());
		assertEquals(PROPERTY_NAME, order.get(0).getProperty());
	}

	@Test
	public void whenCallCreatePageRequestUnsortedDirection_thenReturnPageableSorted() {

		Pageable pageable = PageableUtil.createPageRequest(PAGE_NUMBER, PAGE_SIZE, PROPERTY_NAME, "any",
				CoveragePageableUtilClass.class);
		assertTrue(pageable.isPaged());
		assertEquals(PAGE_NUMBER, pageable.getPageNumber());
		assertEquals(PAGE_SIZE, pageable.getPageSize());
		assertFalse(pageable.getSort().isSorted());
	}

	@Test
	public void whenCallFindSortPropertyName_thenReturnPropertyName() {

		Optional<String> optional = PageableUtil.findSortPropertyName(CoveragePageableUtilClass.class,
				CoveragePageableUtilClass.COLUMN_NAME);
		assertEquals(CoveragePageableUtilClass.COLUMN_NAME, optional.get());
	}

	@Test
	public void whenCallFindSortPropertyName_thenReturnPropertyNameEmpty() {

		Optional<String> optional = PageableUtil.findSortPropertyName(CoveragePageableUtilClass.class, "any");
		assertFalse(optional.isPresent());
	}

	@Test
	public void whenCallBuildPage() {

		List<CoveragePageableUtilClass> list = new ArrayList<>();
		list.add(new CoveragePageableUtilClass());
		Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

		Page<CoveragePageableUtilClass> page = PageableUtil.buildPage(list, pageable, list.size());
		assertNotNull(page);
	}

	@Test
	public void whenCallReplacePageParams_thenReturnLink() {

		UriComponentsBuilder origional = UriComponentsBuilder.fromUriString(httpUrl);
		int nextPage = PAGE_NUMBER + 1;

		Pageable pageable = PageRequest.of(nextPage, PAGE_SIZE);

		Link link = PageableUtil.replacePageParams(origional, pageable);

		assertTrue(link.getHref().contains(BASE_URI));
		assertTrue(link.getHref().contains(SORT_PROPERTY_FILTER + PROPERTY_NAME));
		assertTrue(link.getHref().contains(SORT_ORDER_FILTER + ORDER_DIRECTION));
		assertTrue(link.getHref().contains(PAGE_NUMBER_FILTER + nextPage));
		assertTrue(link.getHref().contains(PAGE_SIZE_FILTER + PAGE_SIZE));
	}

}

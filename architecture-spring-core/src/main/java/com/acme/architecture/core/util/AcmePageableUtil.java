package com.acme.architecture.core.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.acme.architecture.core.constant.PageableConstant;
import com.acme.architecture.core.response.PageableResponse;

public class AcmePageableUtil {

	protected AcmePageableUtil() {
		throw new IllegalStateException("PageableUtil");
	}

	public static Pageable createPageRequest(Integer pageNumber, Integer pageSize, String sortProperty,
			String sortOrder, Class<?> clzz) {

		Pageable pageable = null;
		Optional<Direction> directionSort = Direction.fromOptionalString(sortOrder);
		Optional<String> propertySort = findSortPropertyName(clzz, sortProperty);

		// If the attribute or sort direction is not correct, it will not be considered
		if (directionSort.isPresent() && propertySort.isPresent()) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(directionSort.get(), propertySort.get()));
		} else {
			pageable = PageRequest.of(pageNumber, pageSize);
		}

		return pageable;
	}

	public static Optional<String> findSortPropertyName(Class<?> clzz, String sortProperty) {

		Optional<String> columName = Optional.empty();

		for (Field field : clzz.getDeclaredFields()) {

			if (field.isAnnotationPresent(Column.class)
					&& field.getDeclaredAnnotation(Column.class).name().equalsIgnoreCase(sortProperty)) {
				columName = Optional.of(field.getName());
			}
		}

		return columName;
	}

	public static <T> Page<T> buildPage(List<T> list, Pageable pageable, long totalElement) {
		return new PageImpl<>(list, pageable, totalElement);
	}

	public static PageableResponse buildPageableResponse(Page<?> body) {

		PageableResponse pageableResponse = new PageableResponse();
		pageableResponse.setContent(body.getContent());
		pageableResponse.setTotalPages((int) body.getTotalElements());
		pageableResponse.setPageNumber(body.getPageable().getPageNumber());
		pageableResponse.setPageSize(body.getPageable().getPageSize());

		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		UriComponentsBuilder baseUri = ServletUriComponentsBuilder.fromServletMapping(request)
				.path(request.getRequestURI());

		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			for (String value : entry.getValue()) {
				baseUri.queryParam(entry.getKey(), value);
			}
		}

		UriComponentsBuilder original = baseUri;

		if (body.hasNext()) {
			Link next = replacePageParams(original, body.nextPageable());
			pageableResponse.setNext(next.getHref());
		}

		if (body.hasPrevious()) {
			Link prev = replacePageParams(original, body.previousPageable());
			pageableResponse.setPrev(prev.getHref());
		}

		return pageableResponse;
	}

	public static Link replacePageParams(UriComponentsBuilder origional, Pageable pageable) {
		UriComponentsBuilder builder = origional.cloneBuilder();
		builder.replaceQueryParam(PageableConstant.QUERY_PARAM_PAGE_NUMBER, pageable.getPageNumber());
		builder.replaceQueryParam(PageableConstant.QUERY_PARAM_PAGE_SIZE, pageable.getPageSize());
		return new Link(builder.toUriString());
	}

}

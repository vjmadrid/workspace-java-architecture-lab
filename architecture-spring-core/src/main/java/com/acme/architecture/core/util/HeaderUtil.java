package com.acme.architecture.core.util;


import java.util.Collections;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.acme.architecture.core.constant.ControllerConstant;
import com.acme.architecture.core.constant.HeaderConstant;
import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;

public final class HeaderUtil {

	protected HeaderUtil() {
		throw new IllegalStateException("HeaderUtil");
	}

	public static String checkUserIdAudit(final String userId, final String headerName) {

		String message = "Error extracting attribute header ".concat(headerName);

		if (userId == null || userId.isEmpty()) {

			message = message.concat(" not found");
			throw new AcmeInvokeRuntimeException(message, HttpStatus.BAD_REQUEST, Collections.singletonList(message));

		} else if (!Pattern.matches(ControllerConstant.ALPHABETIC_CHARACTER_AND_NUMERIC, userId)) {

			message = message.concat(" invalid pattern. Must be ")
					.concat(ControllerConstant.ALPHABETIC_CHARACTER_AND_NUMERIC);
			throw new AcmeInvokeRuntimeException(message, HttpStatus.BAD_REQUEST, Collections.singletonList(message));
		}

		return userId;
	}

	public static String checkAuthorization(HttpServletRequest httpServletRequest) {
		
		String accessToken = StringUtils.EMPTY;
		
		if (httpServletRequest != null) {
			accessToken = httpServletRequest.getHeader(HeaderConstant.HEADER_AUTHORIZATION);

			if (StringUtils.isEmpty(accessToken)) {
				accessToken = StringUtils.EMPTY;
			}
		}

		return accessToken;

	}

	public static UUID checkUuid(HttpServletRequest httpServletRequest) {
		
		UUID uuid = null;
		
		if (httpServletRequest != null) {
			String headerUuid = httpServletRequest.getHeader(HeaderConstant.HEADER_UUID);
			
			if (StringUtils.isEmpty(headerUuid)) {
				uuid = UUID.randomUUID();
			} else {
				uuid = UUID.fromString(headerUuid);
			}
			
		}

		return uuid;
	}

}

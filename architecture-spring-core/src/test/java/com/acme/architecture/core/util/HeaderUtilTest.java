package com.acme.architecture.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import com.acme.architecture.core.constant.HeaderConstant;
import com.acme.architecture.core.exception.AcmeInvokeRuntimeException;

public class HeaderUtilTest {

	private String USER_ID_VALUE = "test";
	private String HEADER_NAME_VALUE = "Id-Audit";
	
	private String OTHER_HEADER = "test";
	private String HEADER_AUTHORIZATION_VALUE = "1234";
	private String HEADER_UUID_VALUE = "2bb0d02d-429c-4cc5-a134-bd09a8658f91";
	
	
	private HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);

	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, ()->{
			new HeaderUtil();
		});
	}

	@Test
	public void whenhCallGetUserIdAuditWithNullRequest_ThenWithStatus400() {

		AcmeInvokeRuntimeException exception = assertThrows(AcmeInvokeRuntimeException.class, () -> {
			HeaderUtil.checkUserIdAudit(null, HEADER_NAME_VALUE);
		});

		assertThat(exception.getMessage()).contains(HeaderConstant.HEADER_USER_ID_AUDIT);
		assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(exception.getError()).asList().hasSize(1);
	}

	@Test
	public void whenhCallGetUserIdAuditWithoutUserIdAttribute_ThenWithStatus400() {

		AcmeInvokeRuntimeException exception = assertThrows(AcmeInvokeRuntimeException.class, () -> {
			HeaderUtil.checkUserIdAudit("", HEADER_NAME_VALUE);
		});

		assertThat(exception.getMessage()).contains(HeaderConstant.HEADER_USER_ID_AUDIT);
		assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(exception.getError()).asList().hasSize(1);

	}

	@Test
	public void whenhCallGetUserIdAuditWithoutInvalidPatternUserIdAttribute_ThenWithStatus400() {

		AcmeInvokeRuntimeException exception = assertThrows(AcmeInvokeRuntimeException.class, () -> {
			HeaderUtil.checkUserIdAudit("?", HEADER_NAME_VALUE);
		});

		assertThat(exception.getMessage()).contains(HeaderConstant.HEADER_USER_ID_AUDIT);
		assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(exception.getError()).asList().hasSize(1);

	}
	
	@Test
	public void whenhCallGetUserIdAudit_ThenReturnOk() {
		String resultUserId = HeaderUtil.checkUserIdAudit(USER_ID_VALUE, HEADER_NAME_VALUE);
		
		assertEquals(USER_ID_VALUE, resultUserId);
	}
	

	@Test
	public void whenhCallACheckAuthorizationWithHttpRequestNull_ThenReturnError() {
		assertEquals(StringUtils.EMPTY, HeaderUtil.checkAuthorization(null));
	}
	
	@Test
	public void whenhCallACheckAuthorizationWithoutHeader_ThenReturnError() {
		assertEquals(StringUtils.EMPTY, HeaderUtil.checkAuthorization(mockedRequest));
	}
	
	@Test
	public void whenhCallACheckAuthorizationWithOtherHeader_ThenReturnOk() {
		when(mockedRequest.getHeader(OTHER_HEADER)).thenReturn(HEADER_AUTHORIZATION_VALUE);
		
		assertEquals(StringUtils.EMPTY, HeaderUtil.checkAuthorization(mockedRequest));
	}
	
	@Test
	public void whenhCallACheckAuthorization_ThenReturnOk() {
		when(mockedRequest.getHeader(HeaderConstant.HEADER_AUTHORIZATION)).thenReturn(HEADER_AUTHORIZATION_VALUE);
		
		assertEquals(HEADER_AUTHORIZATION_VALUE, HeaderUtil.checkAuthorization(mockedRequest));
	}
	
	@Test
	public void whenhCallACheckUuidWithHttpRequestNull_ThenReturnError() {
		assertEquals(null, HeaderUtil.checkUuid(null));
	}
	
	@Test
	public void whenhCallACheckUuidWithoutHeader_ThenReturnError() {
		UUID resultUuid = HeaderUtil.checkUuid(mockedRequest);
		
		assertNotNull(resultUuid);
	}
	
	@Test
	public void whenhCallACheckUuidWithNoHeaderCreate_ThenReturnError() {
		when(mockedRequest.getHeader(OTHER_HEADER)).thenReturn(HEADER_AUTHORIZATION_VALUE);
		
		UUID resultUuid = HeaderUtil.checkUuid(mockedRequest);
		
		assertNotNull(resultUuid);
	}
	
	@Test
	public void whenhCallACheckUuidWithHeaderPropagate_ThenReturnError() {
		when(mockedRequest.getHeader(HeaderConstant.HEADER_UUID)).thenReturn(HEADER_UUID_VALUE);
		
		UUID resultUuid = HeaderUtil.checkUuid(mockedRequest);
		
		assertNotNull(resultUuid);
		assertEquals(HEADER_UUID_VALUE, resultUuid.toString());
	}
	


}

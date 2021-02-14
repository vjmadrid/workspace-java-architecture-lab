package com.acme.architecture.core.controllerinterface;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.acme.architecture.core.constant.ControllerConstant;
import com.acme.architecture.core.constant.HeaderConstant;

import io.swagger.annotations.ApiOperation;

@Validated
public interface CreateControllerInterface<E, C> {

	@ApiOperation(value = "Create new entity")
	@PostMapping
	public ResponseEntity<E> create(@Valid @RequestBody C request,
			@RequestHeader(name = HeaderConstant.HEADER_USER_ID_AUDIT, required = true) @Size(min = 1, max = 50) @Pattern(regexp = ControllerConstant.ANY) String userId);

}

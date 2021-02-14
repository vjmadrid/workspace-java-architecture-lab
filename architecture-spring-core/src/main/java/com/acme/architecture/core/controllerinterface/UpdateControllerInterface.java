package com.acme.architecture.core.controllerinterface;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.acme.architecture.core.constant.ControllerConstant;
import com.acme.architecture.core.constant.HeaderConstant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
public interface UpdateControllerInterface<E, U> {

	@ApiOperation(value = "Update entity by id")
	@PutMapping(value = ControllerConstant.ID_PATH_VARIABLE_PARAM)
	public ResponseEntity<E> update(
			@ApiParam(required = true) @PathVariable(ControllerConstant.ID_PATH_VARIABLE) @Min(0) @Max(Integer.MAX_VALUE) int id,
			@Valid @RequestBody U request,
			@RequestHeader(name = HeaderConstant.HEADER_USER_ID_AUDIT, required = true) @NotBlank @Size(min = 1, max = 50) @Pattern(regexp = ControllerConstant.ANY) String userId);

}

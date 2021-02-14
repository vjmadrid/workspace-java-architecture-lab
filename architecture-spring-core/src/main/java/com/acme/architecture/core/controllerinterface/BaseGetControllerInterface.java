package com.acme.architecture.core.controllerinterface;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.acme.architecture.core.constant.ControllerConstant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
public interface BaseGetControllerInterface<E> {

	@ApiOperation(value = "Get all entities")
	@GetMapping
	public ResponseEntity<List<E>> findAll();

	@ApiOperation(value = "Get entity by id")
	@GetMapping(value = ControllerConstant.ID_PATH_VARIABLE_PARAM)
	public ResponseEntity<E> findById(
			@ApiParam(required = true) @PathVariable(name = ControllerConstant.ID_PATH_VARIABLE, required = true) @Min(0) @Max(Integer.MAX_VALUE) int id);

	@ApiOperation(value = "Get list entities by id list")
	@GetMapping(value = ControllerConstant.BASE_LIST_URL, params = ControllerConstant.IDS_QUERY_PARAM)
	public ResponseEntity<List<E>> findByIdList(@RequestParam @Size(min=0, max=Integer.MAX_VALUE)List<String> ids);

}

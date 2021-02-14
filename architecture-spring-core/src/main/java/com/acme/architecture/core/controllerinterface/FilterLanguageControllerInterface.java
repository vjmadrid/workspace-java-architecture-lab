package com.acme.architecture.core.controllerinterface;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.acme.architecture.core.constant.ControllerConstant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
public interface FilterLanguageControllerInterface<E> {
	
	@ApiOperation(value = "Get all entities filtering language")
	@GetMapping(value = ControllerConstant.BASE_LANGUAGE_URL, params = ControllerConstant.LANGUAGE_ID_QUERY_PARAM)
	public ResponseEntity<List<E>> findAll(
			@ApiParam(required = true) @Min(0) @Max(Integer.MAX_VALUE) @RequestParam(defaultValue = ControllerConstant.LANGUAGE_ID_QUERY_PARAM_DEFAULT) int languageId);

	@ApiOperation(value = "Get entity by id filtering language")
	@GetMapping(value = ControllerConstant.BASE_LANGUAGE_ENTITY_ID_URL, params = ControllerConstant.LANGUAGE_ID_QUERY_PARAM)
	public ResponseEntity<E> findById(
			@ApiParam(required = true) @PathVariable(name = ControllerConstant.ID_PATH_VARIABLE, required = true) @Min(0) @Max(Integer.MAX_VALUE) int id,
			@ApiParam(required = true) @Min(0) @Max(Integer.MAX_VALUE) @RequestParam(defaultValue = ControllerConstant.LANGUAGE_ID_QUERY_PARAM_DEFAULT) int languageId);

	@ApiOperation(value = "Get list entities by id list filtering language")
	@GetMapping(value = ControllerConstant.BASE_LANGUAGE_LIST_URL, params = { ControllerConstant.IDS_QUERY_PARAM,
			ControllerConstant.LANGUAGE_ID_QUERY_PARAM })
	public ResponseEntity<List<E>> findByIdListLanguage(@RequestParam List<String> ids,
			@ApiParam(required = true) @Min(0) @Max(Integer.MAX_VALUE) @RequestParam(defaultValue = ControllerConstant.LANGUAGE_ID_QUERY_PARAM_DEFAULT) int languageId);

}

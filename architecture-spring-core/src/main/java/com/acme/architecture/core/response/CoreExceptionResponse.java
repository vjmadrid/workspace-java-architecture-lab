package com.acme.architecture.core.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.acme.architecture.common.constant.PatternsConstant;
import com.acme.architecture.core.error.CoreExceptionError;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoreExceptionResponse implements Serializable {

	private static final long serialVersionUID = -4601792895030653711L;

	@NotEmpty
	@Pattern(regexp = PatternsConstant.ANY)
	@Size(min = 1, max = Integer.MAX_VALUE)
	private String message;

	@NotNull
	@Size(max = Integer.MAX_VALUE)
	private List<CoreExceptionError> errors = new ArrayList<>();

}
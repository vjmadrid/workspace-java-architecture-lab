package com.acme.architecture.core.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoreExceptionResponse implements Serializable {

	private static final long serialVersionUID = -4601792895030653711L;

	private String message;

	private List<String> errors;

}
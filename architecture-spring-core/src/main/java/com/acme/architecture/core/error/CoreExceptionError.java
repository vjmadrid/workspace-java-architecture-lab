package com.acme.architecture.core.error;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.acme.architecture.common.constant.PatternsConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoreExceptionError implements Serializable {

	private static final long serialVersionUID = -4601792895030653711L;

	@NotEmpty
	@Pattern(regexp = PatternsConstant.ANY)
	@Size(min = 1, max = Integer.MAX_VALUE)
	private String error;

}
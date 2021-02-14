package com.acme.architecture.core.controllerinterface.dummy.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TestingCreateRequest implements Serializable {

	private static final long serialVersionUID = 2077639140155840233L;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String text;

	private String description;

	public static TestingCreateRequest createSampleDefault() {
		TestingCreateRequest testingCreateRequest = new TestingCreateRequest();
		testingCreateRequest.setText("text");
		return testingCreateRequest;
	}

}

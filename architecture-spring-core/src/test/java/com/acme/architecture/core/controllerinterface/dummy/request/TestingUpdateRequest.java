package com.acme.architecture.core.controllerinterface.dummy.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TestingUpdateRequest implements Serializable {

	private static final long serialVersionUID = -1769745872267412421L;

	@NotNull
	private Integer id;

	@NotNull
	private String text;

	public static TestingUpdateRequest createSampleDefault() {
		TestingUpdateRequest testingUpdateRequest = new TestingUpdateRequest();
		testingUpdateRequest.setId(1);
		testingUpdateRequest.setText("text");
		return testingUpdateRequest;
	}

}

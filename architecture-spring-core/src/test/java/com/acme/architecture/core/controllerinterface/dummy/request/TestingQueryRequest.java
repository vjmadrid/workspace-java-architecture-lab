package com.acme.architecture.core.controllerinterface.dummy.request;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TestingQueryRequest implements Serializable {

	private static final long serialVersionUID = -2867658092092591627L;

	private Integer id;

	private String text;

	public static TestingQueryRequest createSampleDefault() {
		TestingQueryRequest testingUpdateRequest = new TestingQueryRequest();
		testingUpdateRequest.setId(1);
		testingUpdateRequest.setText("text");
		return testingUpdateRequest;
	}

}

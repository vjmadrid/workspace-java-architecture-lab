package com.acme.architecture.core.controllerinterface.dummy.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class TestingResponse implements Serializable {

	private static final long serialVersionUID = 2077639140155840233L;

	@NotNull
	@Min(0)
	@Max(Integer.MAX_VALUE)
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String text;

	private String description;

	public static TestingResponse createSampleDefault() {
		TestingResponse testingResponse = new TestingResponse();
		testingResponse.setId(1);
		testingResponse.setText("text");
		return testingResponse;
	}

	public static List<TestingResponse> createSampleDefaultList() {
		List<TestingResponse> testingResponseList = new ArrayList<>();
		testingResponseList.add(createSampleDefault());
		return testingResponseList;
	}
}

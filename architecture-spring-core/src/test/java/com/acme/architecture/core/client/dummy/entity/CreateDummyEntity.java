package com.acme.architecture.core.client.dummy.entity;

import com.acme.architecture.common.annotation.response.CreateDummyResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CreateDummyResponse(fileName = CreateDummyEntity.FILE_NAME)
public class CreateDummyEntity {

	public static final String FILE_NAME = "file-name";

	public static final String TEXT_VALUE = "This is my file resource";

	public static final String FILE_RESOURCE = "{\"text\":\"" + TEXT_VALUE + "\"}";

	private String text;

}

package com.acme.architecture.core.util.example;

import javax.persistence.Column;

public class CoveragePageableUtilClass {

	public static final String COLUMN_NAME = "id";
	
	
	
	@Column(name = COLUMN_NAME)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}

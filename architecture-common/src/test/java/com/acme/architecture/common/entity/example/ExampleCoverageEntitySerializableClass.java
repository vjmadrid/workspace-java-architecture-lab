package com.acme.architecture.common.entity.example;

import com.acme.architecture.common.entity.AbstractEntity;

public class ExampleCoverageEntitySerializableClass extends AbstractEntity {

	private static final long serialVersionUID = -6645582330773095568L;
	
	private int intCoverage;

	public int getIntCoverage() {
		return intCoverage;
	}

	public void setIntCoverage(int intCoverage) {
		this.intCoverage = intCoverage;
	}
}

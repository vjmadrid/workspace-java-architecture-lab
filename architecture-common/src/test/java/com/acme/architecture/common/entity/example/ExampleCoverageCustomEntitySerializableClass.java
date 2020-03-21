package com.acme.architecture.common.entity.example;

import com.acme.architecture.common.entity.AbstractCustomEntity;

public class ExampleCoverageCustomEntitySerializableClass extends AbstractCustomEntity {

	private static final long serialVersionUID = 9127939006308528982L;
	
	private int intCoverage;

	public int getIntCoverage() {
		return intCoverage;
	}

	public void setIntCoverage(int intCoverage) {
		this.intCoverage = intCoverage;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}

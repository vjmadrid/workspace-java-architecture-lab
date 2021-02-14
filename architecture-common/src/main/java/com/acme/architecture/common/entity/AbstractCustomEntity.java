package com.acme.architecture.common.entity;

import java.io.Serializable;

public abstract class AbstractCustomEntity implements Serializable {

	private static final long serialVersionUID = 8205274245698638280L;

	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object object);
	
	@Override
	public abstract String toString();
	
}

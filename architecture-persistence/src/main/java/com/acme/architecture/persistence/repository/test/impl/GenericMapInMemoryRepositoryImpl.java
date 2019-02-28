package com.acme.architecture.persistence.repository.test.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.acme.architecture.persistence.repository.GenericRepository;

public abstract class GenericMapInMemoryRepositoryImpl<T, PK extends Serializable>
		implements GenericRepository<T, PK> {

	private Map<PK,T> entityMap = new HashMap<>();

	public Map<PK, T> getEntityMap() {
		return entityMap;
	}

	public void setEntityMap(Map<PK, T> entityMap) {
		this.entityMap = entityMap;
	}

}

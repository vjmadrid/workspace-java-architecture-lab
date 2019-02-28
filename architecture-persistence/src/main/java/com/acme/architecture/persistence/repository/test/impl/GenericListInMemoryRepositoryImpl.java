package com.acme.architecture.persistence.repository.test.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.acme.architecture.persistence.repository.GenericRepository;

public abstract class GenericListInMemoryRepositoryImpl<T, PK extends Serializable>
		implements GenericRepository<T, PK> {

	private List<T> entityList = new ArrayList<T>();

	@Override
	public final void insert(final T object) {
		entityList.add(object);
	}

	@Override
	public final void remove(final T object) {
		entityList.remove(object);
	}

	@Override
	public final void update(final T object) {
		throw new UnsupportedOperationException("Not supported");
	}

	@Override
	public final List<T> findAll() {
		return entityList;
	}

	@Override
	public final T findByPK(final PK id) {
		if (entityList.isEmpty()) {
			return null;
		}
		return entityList.get(0);
	}

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

}

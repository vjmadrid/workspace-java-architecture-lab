package com.acme.architecture.persistence.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericMapInMemoryRepository<T, PK extends Serializable>  {
	
	void insert(T object);
	
	void remove(T object);

	void update(T object);
	
	List<T> findAll();
	
	T findByPK(PK id);
	
	List<T> findByQuery(String query);
	
	List<T> findByQueryParameters(String query, Map<String, Object> params);
	
}

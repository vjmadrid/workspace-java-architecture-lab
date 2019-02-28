package com.acme.architecture.persistence.repository.hibernate.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.acme.architecture.persistence.repository.GenericRepository;

public class GenericHibernateRepositoryImpl<T, PK extends Serializable> implements
		GenericRepository<T, PK> {

	private SessionFactory sessionFactory;

	private Class<? extends T> daoType;

	@SuppressWarnings("unchecked")
	public GenericHibernateRepositoryImpl() {
		daoType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public GenericHibernateRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		daoType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected final Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public final void insert(final T object) {
		currentSession().save(object);
	}

	@Override
	public final void remove(final T object) {
		currentSession().delete(object);
	}

	@Override
	public final void update(final T object) {
		currentSession().saveOrUpdate(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<T> findAll() {
		return ((List<T>) currentSession().createCriteria(daoType).list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public final T findByPK(final PK id) {
		return (T) currentSession().get(daoType, id);
	}

	@SuppressWarnings("unchecked")
	public final List<T> findByQuery(final String query) {
		return ((List<T>) currentSession().createQuery(query).list());
	}

	public Query createQuery(final String query) {
		return (Query) currentSession().createQuery(query);
	}
	
	private Query createQueryWithParams(final String query,
			final Map<String, Object> params) {
		Query qQuery = (Query) currentSession().createQuery(query);
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = params.get(key);
				qQuery.setParameter(key, value);
			}
		}
		return qQuery;
	}

	@SuppressWarnings("unchecked")
	public final List<T> findByQueryParameters(final String query,
			final Map<String, Object> params) {
		Query qQuery = createQueryWithParams(query, params);
		return ((List<T>) qQuery.list());
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

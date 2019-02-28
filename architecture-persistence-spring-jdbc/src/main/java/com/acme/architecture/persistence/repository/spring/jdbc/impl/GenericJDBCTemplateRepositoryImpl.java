package com.acme.architecture.persistence.repository.spring.jdbc.impl;

import java.io.Serializable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acme.architecture.persistence.repository.GenericRepository;

public abstract class GenericJDBCTemplateRepositoryImpl<T, PK extends Serializable>
		implements GenericRepository<T, PK> {

	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
}

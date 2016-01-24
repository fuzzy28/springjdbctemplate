package org.jrue.poc.springjdbc.dao;

import java.util.List;

public interface GenericDao<E, K> {

	E findSingle(String key);
	List<E> findAll();
	void save(E record);
	void update(E record);
	void delete(K record);
}
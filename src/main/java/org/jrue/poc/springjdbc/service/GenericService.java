package org.jrue.poc.springjdbc.service;

import java.util.List;

import org.jrue.poc.springjdbc.dao.GenericDao;

public abstract class GenericService<E,K> {
	
	protected GenericDao<E, K> dao;
	
	public GenericService(GenericDao<E, K> dao) {
		this.dao = dao;
	}

	public GenericDao<E,K> getDao() {
		return dao;
	}

	public void setDao(GenericDao<E, K> dao) {
		this.dao = dao;
	}
	
	public abstract E findSingle(String key);
	public abstract List<E> findAll();
	public abstract void save(E record);
	public abstract void update(E record);
	public abstract void delete(K key);
}

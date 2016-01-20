package org.jrue.poc.springjdbc.dao;

import java.util.List;

public interface Repository<T> {

	T findSingle(String key);
	List<T> find(T key);
	boolean save(T record);
	boolean update(T record);
	boolean delete(T record);
}

package org.jrue.poc.springjdbc.service;

import java.util.List;

import org.jrue.poc.springjdbc.dao.GenericDao;
import org.jrue.poc.springjdbc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends GenericService<User, String> {

	@Autowired
	public UserService(GenericDao<User,String> userDao) {
		super(userDao);
	}

	@Override
	public User findSingle(String key) {
		User user;
		try {
			user = dao.findSingle(key);
		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
		return user;
	}

	@Override
	@Transactional
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	@Transactional
	public void save(User record) {
		User usr = findSingle(record.getName());
		if(usr != null && !usr.getName().isEmpty()) {
			throw new DuplicateKeyException(usr.getName() + " already exists!");
		} else {
			dao.save(record);
		}
	}

	@Override
	@Transactional
	public void update(User record) {
		dao.update(record);
	}

	@Override
	@Transactional
	public void delete(String key) {
		dao.delete(key);
	}
}

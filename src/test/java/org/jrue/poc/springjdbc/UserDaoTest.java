package org.jrue.poc.springjdbc;

import org.jrue.poc.springjdbc.dao.UserDao;
import org.jrue.poc.springjdbc.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoTest {

	private AbstractApplicationContext context;
	private UserDao dao;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("META-INF/spring-config.xml");
		dao = context.getBean("userDao", UserDao.class);
	}
	
	@After
	public void destroy() {
		context = null;
		dao = null;
	}
	
	@Test
	@Transactional
	public void testSingleCRUD() {
		//test insert single record
		User user = new User();
		user.setUserCode("JRUELOS" + (int) (Math.random() * 200));
		user.setUserName("JOEL RUELOS");
		user.setPassword("password123");
		user.setEmployeeId("123312");
		dao.save(user);
		
		//test query single record
		Assert.assertNotNull(dao.findSingle(user.getUserCode()));
//		
//		//test update single record
		user.setUserName("JOEL RUELOS JR");
		user.setPassword("password12345");
		user.setEmployeeId("12331245");
		dao.update(user);
//		
		User updatedUser = dao.findSingle(user.getUserCode());
//		
		Assert.assertEquals(user.getUserName(), updatedUser.getUserName());
		Assert.assertEquals(user.getPassword(), updatedUser.getPassword());
		Assert.assertEquals(user.getEmployeeId(), updatedUser.getEmployeeId());
//		
//		//test logical delete
		dao.delete(user);		
//		Assert.assertTrue(repository.find(user.getUserCode()).isEmpty());
	}
	
}
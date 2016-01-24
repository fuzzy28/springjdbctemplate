package org.jrue.poc.springjdbc;

import org.jrue.poc.springjdbc.domain.User;
import org.jrue.poc.springjdbc.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring-config.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@After
	public void destroy() {
		userService = null;
	}
	
	@Test
	public void testSingleCRUD() {
		//test insert single record
		User user = new User();
		user.setName("JRUELOS");
		user.setPassword("password123");
		user.setEmployeeId("123312");
		user.setDepartmentName("SDD1");
		userService.save(user);
	}
	
}
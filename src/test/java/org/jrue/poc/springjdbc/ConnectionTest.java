package org.jrue.poc.springjdbc;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class ConnectionTest {

	private AbstractApplicationContext context;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("META-INF/spring-config.xml");
	}
	
	@After
	public void destroy() {
		context = null;
	}
	
	@Test
	public void testConnectivity() throws SQLException {	
		Assert.assertNotNull(context.getBean("basicDataSource"));
	}
	
}
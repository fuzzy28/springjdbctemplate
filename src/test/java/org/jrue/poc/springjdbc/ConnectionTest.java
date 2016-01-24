package org.jrue.poc.springjdbc;

import java.sql.SQLException;







import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring-config.xml")
public class ConnectionTest {

	@Autowired
	private DataSource dataSource;
	
	@After
	public void destroy() {
		dataSource = null;
	}
	
	@Test
	public void testConnectivity() throws SQLException {	
		Assert.assertNotNull(dataSource);
	}
	
}
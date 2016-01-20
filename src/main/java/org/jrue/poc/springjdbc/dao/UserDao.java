package org.jrue.poc.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jrue.poc.springjdbc.model.User;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDao extends JdbcDaoSupport implements Repository<User> {

	private static final String INSERT_STATEMENT = "INSERT INTO M_ACCOUNT (USERCD,USERNAME,PASSWORD,CASHIERPASSWORD,EMPLOYEEID,DELFLAG) " +
			"VALUES (?, ? ,? ,? ,?, ?)";
	
	private static final String SELECT_STATEMENT = "SELECT USERCD,USERNAME,EMPLOYEEID,PASSWORD FROM M_ACCOUNT WHERE USERCD = ? AND DELFLAG = 0";
	
	private static final String UPDATE_STATEMENT = "UPDATE M_ACCOUNT SET USERNAME = ?,PASSWORD = ?,CASHIERPASSWORD = ?,EMPLOYEEID = ?,DELFLAG = ? " +
			",UPDPERSON = ? WHERE USERCD = ?";
	
	private static final String LOGICALDELETE_STATEMENT = "UPDATE M_ACCOUNT SET DELFLAG = 1 WHERE USERCD = ?";
	
	@Override
	public User findSingle(String key) {		
		return getJdbcTemplate().queryForObject(SELECT_STATEMENT,
				new ParameterizedRowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setUserCode(rs.getString(1));
						user.setUserName(rs.getString(2));
						user.setEmployeeId(rs.getString(3));
						user.setPassword(rs.getString(4));
						return user;
					}
				}, key);
	}

	@Override
	public List<User> find(User key) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean save(User record) {		
		return getJdbcTemplate().update(INSERT_STATEMENT, 
				record.getUserCode(),
				record.getUserName(),
				record.getPassword(),
				record.getPassword(),
				record.getEmployeeId(),
				0) > 0;
	}

	@Override
	public boolean update(User record) {
		return getJdbcTemplate().update(UPDATE_STATEMENT, 
				record.getUserName(),
				record.getPassword(),
				record.getPassword(),
				record.getEmployeeId(),
				0,
				record.getUserCode(),
				record.getUserCode()) > 0;
	}

	@Override
	public boolean delete(User record) {		
		return getJdbcTemplate().update(LOGICALDELETE_STATEMENT, 
				record.getUserCode()) > 0;
	}
}
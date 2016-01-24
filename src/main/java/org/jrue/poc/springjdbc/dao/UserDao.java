package org.jrue.poc.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jrue.poc.springjdbc.domain.User;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDao extends JdbcDaoSupport implements GenericDao<User, String> {
		
	private static final String INSERT_STATEMENT = "INSERT INTO M_USER (NAME,PASSWORD,DEPARTMENTNAME,EMPLOYEEID,DELFLAG) " +
													"VALUES (?, ? ,? ,?, 0)";
	
	private static final String SELECT_STATEMENT = "SELECT NAME,PASSWORD,DEPARTMENTNAME,EMPLOYEEID FROM M_USER WHERE DELFLAG = 0";
	
	private static final String UPDATE_STATEMENT = "UPDATE M_USER SET NAME = ?,PASSWORD = ?,DEPARTMENTNAME = ?,EMPLOYEEID = ?,DELFLAG = ? " +
													"WHERE USERCD = ?";
	
	private static final String LOGICALDELETE_STATEMENT = "UPDATE M_USER SET DELFLAG = 1 WHERE NAME = ?";
	
	@Override
	public User findSingle(String key) {		
		return getJdbcTemplate().queryForObject(SELECT_STATEMENT + " AND NAME = ?",
				new ParameterizedRowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setName(rs.getString(1));
						user.setPassword(rs.getString(2));
						user.setDepartmentName(rs.getString(3));
						user.setEmployeeId(rs.getString(4));
						return user;
					}
				}, key);
	}

	@Override
	public List<User> findAll() {
		return getJdbcTemplate().query(SELECT_STATEMENT, 
				new ParameterizedRowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setName(rs.getString(1));
						user.setPassword(rs.getString(2));
						user.setDepartmentName(rs.getString(3));
						user.setEmployeeId(rs.getString(4));
						return user;
					}
				});
	}

	@Override
	public void save(User record) {		
		getJdbcTemplate().update(INSERT_STATEMENT, 
					record.getName(),
					record.getPassword(),
					record.getDepartmentName(),
					record.getEmployeeId());
	}

	@Override
	public void update(User record) {
		getJdbcTemplate().update(UPDATE_STATEMENT, 
				record.getName(),
				record.getPassword(),
				record.getDepartmentName(),
				record.getEmployeeId());
	}

	@Override
	public void delete(String key) {		
		getJdbcTemplate().update(LOGICALDELETE_STATEMENT, 
				key);
	}
	
}
package com.gts.Service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gts.model.user;
@Repository
public class UserDAOImpl implements UserDAO {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	 public void setDataSource(DataSource dataSource) {
	  this.jdbcTemplate = new JdbcTemplate(dataSource);
	 }
	 public List<user> getUsers()
	 {
		 List<user> users = null ;
		  
		  try {
		   users = jdbcTemplate.query("SELECT * FROM usertable",new BeanPropertyRowMapper<user>(user.class));   
		  } catch (DataAccessException e) {
		   e.printStackTrace();
		  }
		  return users; 
	 }
	 public user getUser(String Id)
	 {
		 user User = null;
		  try {
		   User = jdbcTemplate.queryForObject("SELECT * FROM usertable WHERE id = ?",
		     new Object[] { Id }, new BeanPropertyRowMapper<user>(user.class));
		  } catch (DataAccessException e) {
		   e.printStackTrace();
		  }
		  return User;
	 }
	 	 public int createuser(user User)
	 	 {
	 		int count = jdbcTemplate.update(
	 			    "INSERT INTO usertable(Id,title,firstname, lastname,dateofbirth,email)VALUES(?,?,?,?,?,?)", new Object[] {
	 			      User.getId(),User.getTitle(), User.getFirstname(), User.getLastname(),User.getDateofbirth(), User.getEmail() });
	 			  return count;
	 	 }

}

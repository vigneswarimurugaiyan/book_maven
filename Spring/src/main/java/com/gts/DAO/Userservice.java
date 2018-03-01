package com.gts.DAO;

import java.util.List;

import com.gts.model.user;

public interface Userservice {
	public List<user> getusers();
	 public user getuser(String Id);	 
	 public int createuser(user User); 
}

package com.gts.Service;

import java.util.List;

import com.gts.model.user;

public interface UserDAO {
	 public List<user> getUsers();
	 public user getUser(String Id);
		public int createuser(user user);
}

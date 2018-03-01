package com.gts.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.Service.UserDAO;
import com.gts.model.user;
@Service("UserService")
public class Userserviceimpl implements Userservice{
	@Autowired
	private UserDAO userdao;
	public List<user> getusers()
	{
		List<user> users = userdao.getUsers();
		  return users;
		
	}
	 public user getuser(String Id)
	 {
		 user User=userdao.getUser(Id);
		  return User;		 
	 }
	 public int createuser(user User)
	 {
		 return userdao.createuser(User);

}
}

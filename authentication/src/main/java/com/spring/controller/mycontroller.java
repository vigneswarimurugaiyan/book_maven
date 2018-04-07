
package com.spring.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bean.UserInfo;
import com.spring.dao.UserDAO;



@RestController
public class mycontroller {
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping(value = "/")
	public void checklogin()
	{
	
	 UserDAO.getUserInfo("admin");
		 //UserInfo u=new UserInfo();
		System.out.println("mymiddle tire");
		//return new ResponseEntity<UserInfo>(u,HttpStatus.OK);
	
			
	 }
}

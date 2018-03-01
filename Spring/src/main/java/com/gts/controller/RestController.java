package com.gts.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gts.DAO.Userservice;
import com.gts.model.user;

@Controller
public class RestController {
	@Autowired
	 private Userservice userService;
	//@GetMapping(value="/getuser")
	
//	public ResponseEntity<List> users()
	//{
		// HttpHeaders headers = new HttpHeaders();
		  //List users = (ArrayList)userService.getusers();

		  //if (users == null) {
		   //return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
		  //}
		  //headers.add("Number Of Records Found", String.valueOf(users.size()));
		  //return new ResponseEntity<List>(users, headers, HttpStatus.OK);	
//	}
	

	@RequestMapping(value = "/getuser", method = RequestMethod.GET, produces = "application/json")
	 public ResponseEntity<List<user>> users() {

	  HttpHeaders headers = new HttpHeaders();
	  List<user> users = userService.getusers();

	  if (users == null) {
	   return new ResponseEntity<List<user>>(HttpStatus.NOT_FOUND);
	  }
	  headers.add("Number Of Records Found", String.valueOf(users.size()));
	  return new ResponseEntity<List<user>>(users, headers, HttpStatus.OK);
	 }
	
	
	
	
	//@GetMapping(value="/getAllusers/{userId}")
	//public ResponseEntity<List> getalluser(@PathVariable("userId") int userId)
	//{
		//userdetail u=userDAO.getuserbyid(userId);
		//System.out.println("obj**"+friendDAO);
		//List listfriends = (ArrayList) userDAO.getalluser1(u);
	//	return new ResponseEntity<List>(listfriends,HttpStatus.OK);
	//}
	
	
	
	
	 @RequestMapping(value = "/user/{Id}", method = RequestMethod.GET)
	 public ResponseEntity<user> getuser(@PathVariable("Id") String Id) {
	  user User = userService.getuser(Id);
	  if (User == null) {
	   return new ResponseEntity<user>(HttpStatus.NOT_FOUND);
	  }
	  return new ResponseEntity<user>(User, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/user", method = RequestMethod.POST,produces = "application/json")
	 public ResponseEntity<user> createUser(@RequestBody user User) {
	  HttpHeaders headers = new HttpHeaders();
	  if (User == null) {
	   return new ResponseEntity<user>(HttpStatus.BAD_REQUEST);
	  }
	  userService.createuser(User);
	  headers.add("Employee Created  - ", String.valueOf(User.getId()));
	  return new ResponseEntity<user>(User, headers, HttpStatus.CREATED);
	 }


}

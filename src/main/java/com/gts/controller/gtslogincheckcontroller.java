package com.gts.controller;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.gts.DAO.usersdao;
import com.gts.DAO.usertokendao;
import com.gts.entity.user_token;
import com.gts.entity.users;
@RestController
public class gtslogincheckcontroller {
	@Autowired
	usersdao usersDAO;
	@Autowired 
	usertokendao utdao;
	@PostMapping("logincheck")
	public ResponseEntity<?> logincheck(@RequestBody users us)
	{	
		System.out.println("inside logincheck controller ()");
		String username=us.getUser_name();
		String password=us.getUser_pwd();
		System.out.println(username+password);
		int length=username.length()+password.length();	
		try{
		users u1=usersDAO.gtslogincheck(username,password,length);
		int userid=u1.getUser_id();
		Date d=new Date();
		usersDAO.changelogintime(userid,d,"loggedin");
        System.out.println("successfully changed");
        String tv=utdao.getToken(length);
        user_token ut=new user_token();
        ut.setUser_tokenvalue(tv);
        Date d1=new Date();
        ut.setToken_createdon(d1);
        ut.setToken_expireon(d1);
        ut.setToken_status("valid");
        utdao.updatetokeninfo(ut,userid);
        System.out.println("token successfully updated");
       user_token utoken=(user_token)utdao.gettokeninfo(userid,tv);
		System.out.println("successfully get token");
		 return new ResponseEntity<user_token>(utoken,HttpStatus.OK);
		}
		catch(NoResultException e) {
			 user_token utoken=new user_token();
			 System.out.println("Username and password is incorrect,authenticated failuare");
			  return new ResponseEntity<user_token>(utoken,HttpStatus.OK);
	}
		catch(NullPointerException e)
		{
			 user_token utoken=new user_token();
			System.out.println("null pointer exception");
			return new ResponseEntity<user_token>(utoken,HttpStatus.OK);
		}
		}
	@PostMapping("getuserdetail")
		public ResponseEntity<?> getuserdetail(@RequestBody user_token us)
		{
		System.out.println(us);
		System.out.println("hgkkj"+us.getUser_tokenvalue());
		users myuser=us.getUser_id();
		System.out.println("users"+myuser.getUser_id());
		System.out.println("hsadl"+us.getUser_id());
		String tokenvalue=us.getUser_tokenvalue();
		int userid=myuser.getUser_id();
		try{
		 user_token utoken=(user_token)utdao.gettokeninfo(userid,tokenvalue);
		 System.out.println(utoken.getUser_tokenvalue());
		users u=new users();
		u.setUser_id(utoken.getUser_id().getUser_id());
		u.setUser_name(utoken.getUser_id().getUser_name());
		u.setUser_loginstatus(utoken.getUser_id().getUser_loginstatus());
		u.setUser_status(utoken.getUser_id().getUser_status());
		u.setUser_lastsuccessfulllogin(utoken.getUser_id().getUser_lastsuccessfulllogin());
		return new ResponseEntity<users>(u,HttpStatus.OK);
		}
		catch(NoResultException e) {
			 users u=new users();
			 System.out.println("Token invalid,no result exception");
			  return new ResponseEntity<users>(u,HttpStatus.OK);
	}
		catch(NullPointerException e)
		{
			 users u=new users();
			System.out.println("Token invalid,null pointer exception");
			return new ResponseEntity<users>(u,HttpStatus.OK);
		}
		}
		
	
	@GetMapping("logout/{userid}")
	
		public boolean logout(@PathVariable("userid") int userid)
		{
		try
		{
			Date d=new Date();
		usersDAO.changelogintime(userid,d,"loggedout");
		user_token ut=new user_token();
		 ut.setUser_tokenvalue("invalid");
	       
	       ut.setToken_createdon(d);
	       ut.setToken_expireon(d);
	        ut.setToken_status("invalid");
		utdao.updatetokeninfo(ut,userid);
			return true;
			}
		catch(Exception e)
		{
			return false;
		}
		}
}






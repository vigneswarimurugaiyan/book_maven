package com.gts.DAO;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.entity.user_token;


@Transactional
@Repository
public class usertokendaoimpl implements usertokendao {
	@PersistenceContext
	private EntityManager entityManager;
	private final Random random = new Random();
	private final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890]!@#$";

	/*public void savetokeninfo(user_token u)
	{
		try{
		StoredProcedureQuery storeprocedure=entityManager.createNamedStoredProcedureQuery("savetokeninfo");
		storeprocedure.setParameter("myuserid",u.getUser_id());
		storeprocedure.setParameter("tokenvalue",u.getUser_tokenvalue());
		storeprocedure.setParameter("tokencreatedon",u.getToken_createdon());
		storeprocedure.setParameter("tokenexpireon",u.getToken_expireon());
		storeprocedure.setParameter("tokenstatus",u.getToken_status());
				storeprocedure.execute();
		}
		catch(Exception e)
		{
			System.out.println("My exception :"+e);
		}
	}*/
	
	
	public void updatetokeninfo(user_token u,int myuserid)
	{
		try
		{
			StoredProcedureQuery storeprocedure=entityManager.createNamedStoredProcedureQuery("updatetokeninfo");
			storeprocedure.setParameter("tokenvalue",u.getUser_tokenvalue());
			storeprocedure.setParameter("tokencreatedon",u.getToken_createdon());
			storeprocedure.setParameter("tokenexpireon",u.getToken_expireon());
			storeprocedure.setParameter("tokenstatus",u.getToken_status());
			storeprocedure.setParameter("myuserid",myuserid);
			
			System.out.println("before update token info procedure call");
			storeprocedure.getUpdateCount();
			storeprocedure.execute();	
			System.out.println("after update token info  procedure call");
		}
		catch(Exception e)
		{
			System.out.println("I am in error:" + e);
		}
	}
	public user_token gettokeninfo(int userid,String tokenvalue)
	{
		
		try
		{
		
			user_token ut =(user_token) entityManager.createNativeQuery("CALL gettokeninfo(:userid,:tokenvalue)",user_token.class).setParameter("userid",userid).setParameter("tokenvalue",tokenvalue).getSingleResult();
	
	return ut;
		}
		catch(Exception e)
		{
			System.out.println("Exception for getting the token" + e);
			user_token ut=new user_token();
			return ut;
		}

		
	}	
		public String getToken(int length) {
			try
			{
	    StringBuilder token = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	        token.append(CHARS.charAt
	        		(random.nextInt(CHARS.length())));
	    }
	    return token.toString();}
			catch(Exception e)
			{
				
				System.out.println("My Exception :"+e);
				return null;
			}
	}
	
}


	
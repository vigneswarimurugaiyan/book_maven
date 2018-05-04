package com.gts.DAO;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gts.entity.user_token;
import com.gts.entity.users;

@Transactional
@Repository
public class usersdaoimpl implements usersdao {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	usersdao ud;
	@Autowired 
	usertokendao utdao;

	
	
	public users gtslogincheck(String username,String password,int length)
	{
		
	System.out.println("entered the impl login check()");
	users us=(users) entityManager.createNativeQuery("CALL gtslogincheck(:myusername,:pass_word)",users.class).setParameter("myusername",username).setParameter("pass_word",password).getSingleResult();
	System.out.println("return obj"+us);
return us;
	
		
}

	public void changelogintime(int user_id,Date last_successfull_login_time,String userloginstatus)
	{
		try
		{
			StoredProcedureQuery storeprocedure=entityManager.createNamedStoredProcedureQuery("updatelastlogintime");
			storeprocedure.setParameter("user_id",user_id);
			storeprocedure.setParameter("last_successfull_login_time",last_successfull_login_time);
			storeprocedure.setParameter("userloginstatus",userloginstatus);
			//storeprocedure.setParameter("userstatus",userstatus);
			
			
			System.out.println("before update procedure call");
			storeprocedure.getUpdateCount();
			storeprocedure.execute();	
			System.out.println("after update procedure call");
		}
		catch(Exception e)
		{
			System.out.println("I am in error:" + e);
		}
	
	}
	
	
	

}


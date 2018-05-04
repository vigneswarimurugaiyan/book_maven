package com.gts.DAO;

import java.util.List;

import com.gts.entity.user_token;



public interface usertokendao {
	//void savetokeninfo(user_token u);
	void updatetokeninfo(user_token u,int myuserid);
	user_token gettokeninfo(int userid,String tokenvalue);
	String getToken(int length);
}

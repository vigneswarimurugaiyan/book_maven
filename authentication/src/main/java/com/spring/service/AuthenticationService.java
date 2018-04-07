package com.spring.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.bean.UserInfo;
import com.spring.dao.UserDAO;
@Service
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserInfo userInfo = userDAO.getUserInfo(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), 
				userInfo.getPassword(), Arrays.asList(authority));
		System.out.println("authenticationservice");
		System.out.println("userDetails");
		return userDetails;
	}
} 
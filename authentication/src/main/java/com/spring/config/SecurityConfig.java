package com.spring.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.spring.service.AuthenticationService;
@Configuration
@EnableWebSecurity
@PropertySource("classpath:jdbc.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	Environment env;
	@Autowired
	AuthenticationService authenticationService;	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//authorize requests
		System.out.println("inside configure method");
	
		 http.authorizeRequests().antMatchers("/").permitAll();
		
		//remember me configuration
		http.rememberMe(). 
		tokenRepository(persistentTokenRepository()).
              
                tokenValiditySeconds(86400);
		
		//logout configuration
            
	}
	public void call()
	{
		PersistentTokenRepository p=persistentTokenRepository();
		System.out.println(p);
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	      //  ShaPasswordEncoder encoder = new ShaPasswordEncoder();
	        auth.userDetailsService(authenticationService);
	    	call();
	}
	@Bean
	public DataSource getDataSource() {
	       BasicDataSource dataSource = new BasicDataSource();
	       dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	       dataSource.setUrl(env.getProperty("jdbc.url"));
	       dataSource.setUsername(env.getProperty("jdbc.username"));
	       dataSource.setPassword(env.getProperty("jdbc.password"));
	       return dataSource;
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		System.out.println("inside persistent method()");
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(getDataSource());
		System.out.print("my token"+tokenRepository);
		return tokenRepository;
	}
}
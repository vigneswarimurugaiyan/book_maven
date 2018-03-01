package com.gts.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class user {
	
	private String Id;
	private String Title;
	private String firstname;
	private String lastname;
	private Date dateofbirth;
	private String email;
	@JsonCreator
	 public user(@JsonProperty("Id") String Id, @JsonProperty("firstname") String firstname,
	   @JsonProperty("lastname") String lastname, @JsonProperty("dateofbirth") Date dateofbirth,@JsonProperty ("Title") String Title,@JsonProperty ("email") String email) {
	  this.Id = Id;
	  this.firstname = firstname;
	  this.lastname = lastname;
	  this.Title = Title;
	  this.dateofbirth=dateofbirth;
	  this.email=email;
	  
	 }
	
	public user(){
		
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	 public String toString() {
	  StringBuilder str = new StringBuilder();
	  str.append("Id:- " + getId());
	  str.append("title:-"+getTitle());
	  str.append(" first name:- " + getFirstname());
	  str.append(" last name:- " + getLastname());
	  str.append(" email:- " + getEmail());
	  str.append(" dateofbirth:- " + getDateofbirth());
	  return str.toString();
	 }
	

}

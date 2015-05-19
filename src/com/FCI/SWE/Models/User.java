package com.FCI.SWE.Models;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class User {
	private long id;
	private String name;
	private String email;
	private String password;
	
	private static User currentActiveUser;

	
	private User(String name, String email, String password) {
	
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	private void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}
	
	public static User getCurrentActiveUser(){
		return currentActiveUser;
	}
	
	public static void setCurrentActiveUserToNull(){
		
		currentActiveUser.email = null ;
		currentActiveUser.name = null ;
		currentActiveUser.password = null ;
	}
	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static User getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			currentActiveUser = new User(object.get("name").toString(), object.get(
					"email").toString(), object.get("password").toString());
			currentActiveUser.setId(Long.parseLong(object.get("id").toString()));
			return currentActiveUser;
		} catch (ParseException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return null;

	}
	

	public static User ParseUserInfo(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			User user = new User();
			user.setEmail(object.get("email").toString());
			user.setName(object.get("name").toString()) ;
			user.setId(Long.parseLong(object.get("id").toString()));
			return user; 
			
		} 
		catch (ParseException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return null;

	}

	private void setName(String na) {
		// TODO Auto-generated method stub
	     name = na ;
	}

	private void setEmail(String em) {
		// TODO Auto-generated method stub
		email = em ;
		
	}


}

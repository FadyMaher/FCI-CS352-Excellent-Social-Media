package com.FCI.SWE.Controller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.FCI.SWE.ServicesModels.UserEntity;

import static org.testng.AssertJUnit.assertEquals;

public class UserControllerTest {
  
	
	
	
	
	@Test
  public void resHomeTest() {
		Map<String, String> map = new HashMap<String, String>();
		assertEquals(null,UserController.home("No user", "No User"));
		map.put("fady", "fady@");
		assertEquals(map, UserController.home("fady", "1234"));
	
  }
	
	
	
	@Test
	public void signUpTest (){
		assertEquals("Registered Successfully",UserController.response("Test", "test@", "123"));
		assertEquals("Failed",UserController.response("fady", "fady@", "123"));
			// this function return failed because the user already exsists  
	}
	
	@Test
	public void sendReqTest(){
		
		assertEquals("Your Request Sent Successfully", UserController.response_sendmyrequest("fady@","maher@"));
		
	}
	
	
	@Test 
	public void createPostTest(){
		
		assertEquals("Your post saved successfully", UserController.response_post("Helloo  Friend", "public", "lovefriends", "Asa7by","ff"));
		
		
		
	}
	@Test 
	public void sendMsgTest(){
		
		assertEquals("Your message sent Successfully", UserController.response_msg("R@", "Hello"));
		
	}
	
	@Test 
	public void createpagepostTest(){
		
		assertEquals("Your Post Posted Successfully",UserController.response_pagePost("Helloooo", "RealMadrid"));
		
		
		
	}
	@Test 
	public void createuserpostTest(){
		
		assertEquals("Your Post Posted Successfully",UserController.response_userPost("msgTest", "FellingTest"));
		
		
		
	}
	
	@Test
	public void hashTagTest(){
		assertEquals("hashtaggets", UserController.response_hashtag("#Ronaldo"));
		
	}
	
	
	
	
	
}

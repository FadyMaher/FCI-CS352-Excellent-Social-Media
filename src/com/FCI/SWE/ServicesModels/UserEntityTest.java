package com.FCI.SWE.ServicesModels;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Vector;

import org.testng.annotations.Test;

public class UserEntityTest {

	@Test
	public void getUserTest() {
		// getUser
		UserEntity u = new UserEntity();
		u.setName("fady");
		u.setE("fady@");
		u.setPass("1234");
		assertEquals(u, UserEntity.getUser("fady", "1234"));
	}
	
	
	
	@Test
	public void searchUser(){
		// searchUser Test
		Vector<UserEntity> t = new Vector<UserEntity>();
		UserEntity uu = new UserEntity();
		uu.setName("fady");
		uu.setE("fady@");
		uu.setPass("1234");
		t.add(uu);
		assertEquals(t, UserEntity.searchUser("fady"));
	}
	@Test
	
	public void sendReq(){
	// sendRequest Test
		assertEquals(true, UserEntity.getrequest("fady@", "helllo@"));
	}
	
	
	@Test
	public void saveGrpMSG(){
		// save groupmsg test
		assertEquals(" ", UserEntity.saveGroupmsg("Conv Name", "fady@",
				"[r1 , r2 , r3]", "Hello Guys"));
		
	}
	@Test
	public void getAllUsers(){
		// getallusers
		Vector<String> getUsers = new Vector<String>();
		getUsers.add("hello");
		getUsers.add("fady");
		assertEquals(getUsers, UserEntity.getAllUsers());
	}
	@Test
	public void pagePost(){
		// test page post
		assertEquals(1,
				UserEntity.savePagePost("keko", "fadymaher is ..", "zezo"));
	}
	@Test
	public void userPost(){
		// testing save User post
		assertEquals(true, UserEntity.savePagePost("keko", "i am lion", "zezo"));
	}
	@Test
	public void searchSingleUser(){
	// testing searchSingleUser
		UserEntity u = new UserEntity();
		u.setName("fady");
		u.setE("fady@");
		u.setPass("1234");
		assertEquals(u, UserEntity.searchSingleUser("fady"));
	}
	
	
}


package com.FCI.SWE.ServicesModels;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Vector;

import org.testng.annotations.Test;

public class MessageEntityTest {
	@Test
	// Message Entity 
   public void savemsgTest(){
	   assertEquals(true,MessageEntity.savemsg("fady","Fady2", "Hello"));
   }
	@Test
	public void seaechMsgTest(){
		MessageEntity m= new MessageEntity("fady@", "helllo@", "hello man");
		
		Vector<MessageEntity> t = new Vector<MessageEntity>();
		t.add(m);
		assertEquals(t, MessageEntity.searchmsg("helllo@"));
	
	}
}

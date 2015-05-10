package com.FCI.SWE.Controller;

import static org.testng.AssertJUnit.assertEquals;
import junit.framework.Assert;

import org.testng.annotations.Test;

public class GroupControllerTest {
  @Test
  public void createGroupTest() {
	  assertEquals("Group created Successfully",GroupController.createGroup("TestG", "this is test group", "public"));
	  
  }
  
  @Test
  public void responseMSGtest(){
	  
	  assertEquals("", GroupController.response_msg("test1@", "test2@", "Hello"));
	  
  }
  
}

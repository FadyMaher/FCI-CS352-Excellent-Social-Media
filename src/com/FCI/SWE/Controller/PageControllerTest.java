package com.FCI.SWE.Controller;

import static org.testng.AssertJUnit.assertEquals;
import org.junit.Assert;
import org.testng.annotations.Test;

public class PageControllerTest {
  @Test
  public void createPageTest() {
	  assertEquals("Page Created Successfully", PageController.response("RealMadrid", "Sport", "football"));
  
  
  }
  
  
  
  @Test
  public void likeTest(){
	  assertEquals(5, PageController.usersList("RealMadrid"));
	  
  }
}

package com.FCI.SWE.ServicesModels;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

public class PageEntityTest {
	@Test
	// Page Entity 
	
	public void savePageTest(){
		assertEquals(true, PageEntity.savePage("Real Madrid", "football", "Sport"));
		
	}
	@Test
	public void likePageTest(){
		PageEntity p = new PageEntity("Real Madrid", "football", "Sport", 50);
		assertEquals(p, PageEntity.likePage("Real Madrid"));
		// failed
		
	}
}

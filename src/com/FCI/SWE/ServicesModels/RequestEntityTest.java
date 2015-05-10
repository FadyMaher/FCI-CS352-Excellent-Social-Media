package com.FCI.SWE.ServicesModels;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Vector;

import org.testng.annotations.Test;

public class RequestEntityTest {
	@Test
	public void saverequest(){
		// testing saverequest
		assertEquals(1, RequestEntity.saverequest("fady@", "keko@yahoo"));
	}
	
	
	@Test	
	//RequestEntity.search Req
	public void searchReq(){
	RequestEntity req = new RequestEntity("fady", "keko", "false");
		Vector<RequestEntity> v3 = new Vector<>();
		v3.add(req);
		assertEquals(req, RequestEntity.searchReq("fady"));
	}
	
	
	
}

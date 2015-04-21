package com.FCI.SWE.Services;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.GroupEntity;
import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.UserEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PageServices {

	@POST
	@Path("/CreatePageService")
	public String createpage(@FormParam("name") String pName,
			 
			@FormParam("cate") String cate ,  @FormParam("type") String type) {
		PageEntity.savePage(pName,  cate , type);
		JSONObject object = new JSONObject ();
		object.put("Status", "OK");
		return object.toJSONString();
		
		
	}
	
	
	@POST
	@Path("/PSearchService")
	public String searchService(@FormParam("pageName") String pname){
		PageEntity page = PageEntity.likePage(pname);
		JSONArray returnedJson = new JSONArray();
	System.out.println(returnedJson.toString());
		return returnedJson.toJSONString();		
	}
	
	
	
	
}

package com.FCI.SWE.Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;

@Path("/")
@Produces("text/html")
public class PageController {

	@GET
	@Path("/page")
	public Response page() {

		if (User.getCurrentActiveUser() == null) {
			return Response.serverError().build();
		}
		return Response.ok(new Viewable("/jsp/createPage")).build();
	}
	@POST
	@Path("/CreatePage")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("name") String pName,
			 @FormParam("cate") String cate ,  @FormParam("type") String type ) {

		String serviceUrl = "http://excellent-social-media.appspot.com/rest/CreatePageService";
		String urlParameters = "name=" + pName + "&cate="+cate + "&type="+type;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Page Created Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
	@GET
	@Path("/searchPage")
	public Response searchP(){
		//System.out.println("here");
		return Response.ok(new Viewable("/jsp/likepage")).build();
	}
	
	@POST
	@Path("/like")
	public Response usersList(@FormParam("pageName") String pname){
	    System.out.println("hereeeeeeeeeeeeeeeeeeeeee");
		String serviceUrl = "http://localhost/rest/PSearchService";
		String urlParameters = "pageName=" + pname;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-formss-urlencoded;charset=UTF-8");
		return null;
		
		
	}
	

	
	
	
	
	
	
	
}

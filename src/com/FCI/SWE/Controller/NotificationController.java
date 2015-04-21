package com.FCI.SWE.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.Notification;
import com.FCI.SWE.Models.User;

@Path("/")
@Produces("text/html")
public class NotificationController {

	
	@GET
	@Path("/test")
	public String function(){
		return "dsldksldkskdsd";
	}
	
	@GET
	@Path("/allNotification")
	public Response getAllNotification() {
		User user = User.getCurrentActiveUser();
		String urlParams = "user="+user.getName();
		String retJson = Connection.connect("http://excellent-social-media.appspot.com/rest/getNotification",
				urlParams, "POST", "application/x-www-form-urlencoded");
		
		JSONParser parser = new JSONParser();
		Vector<Notification> notifications = new Vector<Notification>();
		Map<String, Vector<Notification> > results = new HashMap<String, Vector<Notification>>();
		
		try {
			JSONArray array = (JSONArray) parser.parse(retJson);
			for(int i=0;i<array.size();i++){
				JSONObject obj = (JSONObject)array.get(i);
				Notification not = new Notification();
				not.setTimestamp(obj.get("timestamp").toString());
				not.setId(obj.get("id").toString());
				not.setParams(obj.get("params").toString());
				not.setType(obj.get("type").toString());
				notifications.add(not);
			}
			
			results.put("result", notifications);
			
			  
			return Response.ok(new Viewable("/jsp/notifications",results)).build();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

package com.FCI.SWE.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.Message;
import com.FCI.SWE.Models.Requests;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.labs.repackaged.org.json.JSONException;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class UserController {

	@GET
	@Path("/Notification")
	public Response Notification() {
		return Response.ok(new Viewable("/jsp/Notification")).build();
	}

	/*
	 * Msg Notifiaction it's working till reach to the html page (Show MN) then
	 * the error begain to appear the error in the final part of the code circle
	 */
	@POST
	@Path("/msgNotification")
	public Response msgList(@FormParam("uemail") String uemail) {

		String serviceUrl = "http://localhost:8888/rest/mshNS";
		String urlParameters = "uemail=" + uemail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Map<String, Vector<Message>> passed_msgs = new HashMap<String, Vector<Message>>();
		try {
			JSONArray arr = (JSONArray) parser.parse(retJson);
			Vector<Message> msgs = new Vector<Message>();
			System.out.println(arr.size() + " Size");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject object;
				object = (JSONObject) arr.get(i);
				msgs.add(Message.ParsemsgInfo(object.toJSONString()));
			}
			System.out.println(msgs.toString());
			passed_msgs.put("msgList", msgs);
			return Response.ok(new Viewable("/jsp/showMN", passed_msgs))
					.build();
		} catch (ParseException e) {
			System.out.println("Out of here");
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Path("/RNotification")
	public Response reqList(@FormParam("uemail") String uemail) {
		System.out.println("Fady");
		String serviceUrl = "http://localhost:8888/rest/getRS";
		String urlParameters = "uemail=" + uemail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Map<String, Vector<Requests>> passed_reqs = new HashMap<String, Vector<Requests>>();
		try {
			System.out.println("Fady 2");
			JSONArray arr = (JSONArray) parser.parse(retJson);
			Vector<Requests> reqS = new Vector<Requests>();
			System.out.println(arr.size() + " Size");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject object;
				object = (JSONObject) arr.get(i);
				reqS.add(Requests.ParsereqInfo(object.toJSONString()));
			}
			System.out.println(reqS.toString());
			passed_reqs.put("reqList", reqS);
			return Response.ok(new Viewable("/jsp/showRN", passed_reqs))
					.build();
		} catch (ParseException e) {
			System.out.println("Out of here");
			e.printStackTrace();
		}

		return null;
	}

	// ////////////////////////////////////////////////////////////

	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}
	
	@GET
	@Path("/pagePost")
	public Response pagePost() {
		return Response.ok(new Viewable("/jsp/pagePost")).build();
	}
	
	@GET
	@Path("/userPost")
	public Response userPost() {
		return Response.ok(new Viewable("/jsp/userPost")).build();
	}

	@GET
	@Path("/SendFriendRequest")
	public Response SFR() {
		return Response.ok(new Viewable("/jsp/SendFriendRequest")).build();
	}

	@GET
	@Path("/search")
	public Response search() {
		return Response.ok(new Viewable("/jsp/search")).build();
                
	}
	@GET
	@Path("/createPost")
	public Response creatPost(){
		System.out.println("michael");
		return Response.ok(new Viewable("/jsp/CreatePost")).build();
	}
	
	@GET
	@Path("/postPri")
	public Response postPri(){
		return Response.ok(new Viewable("/jsp/postPri")).build();
	}
	
	
	@GET
	@Path("/SendMSG")
	public Response sendmsg() {
		return Response.ok(new Viewable("/jsp/SendMSG")).build();

	}

	@GET
	@Path("/acceptFriend")
	public Response accept() {
		return Response.ok(new Viewable("/jsp/acceptFriend")).build();
	}

	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}

	@GET
	@Path("/entryPoint")
	public Response exit() {
		User.setCurrentActiveUserToNull();
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */

	@POST
	@Path("/doSearch")
	public Response usersList(@FormParam("uname") String uname) {
		System.out.println(uname);
		String serviceUrl = "http://localhost:8888/rest/SearchService";
		String urlParameters = "uname=" + uname;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		System.out.println(retJson);
		JSONParser parser = new JSONParser();
		Map<String, Vector<User>> passed_users = new HashMap<String, Vector<User>>();
		try {
			JSONArray arr = (JSONArray) parser.parse(retJson);
			Vector<User> users = new Vector<User>();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject object;
				object = (JSONObject) arr.get(i);
				users.add(User.ParseUserInfo(object.toJSONString()));
			}
			passed_users.put("usersList", users);
			return Response.ok(new Viewable("/jsp/showUsers", passed_users))
					.build();
		} catch (ParseException e) {
			System.out.println("Out of here");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,
			@FormParam("password") String pass) {

		String urlParameters = "uname=" + uname + "&password=" + pass;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/LoginService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			User user = User.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home", map)).build();
		} catch (ParseException e) {

			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}

	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email
				+ "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {

			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

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

	@POST
	@Path("/sendingmyrequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String response_sendmyrequest(
			@FormParam("senderemail") String Semail,
			@FormParam("reciveremail") String Remail) {

		String urlParameters = "senderemail=" + Semail + "&reciveremail="
				+ Remail;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/SendrequestService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;

		try {

			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;

			if (object.get("Status").equals("OK")) {

				return "Your Request Sent Successfully";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Failed";
	}

	@POST
	@Path("/createpost")
	@Produces(MediaType.TEXT_PLAIN)
	public String response_post(@FormParam("userPost") String uPost) {
		String serviceUrl = "http://localhost:8888/rest/createPostService";
		String urlParameters = "userPost=" + uPost;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object =(JSONObject) obj;
			if(object.get("Status").equals("OK"))
				return "Your post saved successfully";
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return "Failed";
	}

	@POST
	@Path("/sendmymsg")
	@Produces(MediaType.TEXT_PLAIN)
	public String response_msg(@FormParam("reciveremail") String ReciverN,
			@FormParam("msg") String msg) {

		String serviceUrl = "http://localhost:8888/rest/sendmsgService";
		String urlParameters = "reciveremail=" + ReciverN + "&msg=" + msg;

		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {

			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Your message sent Successfully";

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "Failed";

	}


	@POST
	@Path("/createPagepost")
	@Produces(MediaType.TEXT_PLAIN)
	public String response_pagePost(@FormParam("Post") String Post,
			@FormParam("Page") String Page) {

		String serviceUrl = "http://localhost:8888/rest/pagePostService";
		String urlParameters = "Post=" + Post + "&Page=" + Page;

		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {

			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Your Post Posted Successfully";

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "Failed";

	}

	
	@POST
	@Path("/createUserpost")
	@Produces(MediaType.TEXT_PLAIN)
	public String response_userPost(@FormParam("Post") String Post,
			@FormParam("Felling") String Felling) {

		String serviceUrl = "http://localhost:8888/rest/userPostService";
		String urlParameters = "Post=" + Post + "&Felling=" + Felling;

		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {

			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Your Post Posted Successfully";

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "Failed";

	}
	
	
	
	
	
	
	
	
	
	
	
}
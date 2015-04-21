package com.FCI.SWE.Services;

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

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.MessageEntity;
import com.FCI.SWE.ServicesModels.PostEntity;
import com.FCI.SWE.ServicesModels.RequestEntity;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.FCI.SWE.Models.Post;

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
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {

	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

/*	@POST
	@Path("/createPostService")
	public String createPostService(@FormParam("userPost") String uPost,
			@FormParam("userPrivacy") String uPrivacy) {
		
		
		Post p = new Post("", "", "");
		User u = User.getCurrentActiveUser();
		PostEntity pe = new PostEntity();
		pe.savePost(u.getEmail(), uPost, "public");
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toJSONString();
	}*/

	@POST
	@Path("/sendmsgService")
	public String sendmsgService(@FormParam("reciveremail") String ReciverE,
			@FormParam("msg") String msg) {
		MessageEntity msgg = new MessageEntity("", "", "");
		User u = User.getCurrentActiveUser();
		msgg.savemsg(u.getEmail(), ReciverE, msg);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	@POST
	@Path("/acceptRequest")
	public String accept(@FormParam("email") String email,
			@FormParam("femail") String femail) {

		JSONObject object = new JSONObject();

		if (UserEntity.getrequest(email, femail)) {

			object.put("Status", "OK");
		} else {
			object.put("Status", "Failed");
		}

		return object.toString();
	}
	
	@POST
	@Path("/createPostService")
	public String createPost(@FormParam("postPrivacy") String postPrivacy,
			@FormParam("postContent") String postContent) {
		System.out.println("HAPPY");
		PostEntity post = new PostEntity();
		User u = User.getCurrentActiveUser();
		post.saveForPublic(u.getEmail(), postContent);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();		
	}
	
	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return user in json format
	 */

	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {

		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);

		if (user == null) {

			object.put("Status", "Failed");
		} else {

			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
			System.out.println("here");
		}

		return object.toString();
	}

	@POST
	@Path("/SendrequestService")
	public String SendrequestService(@FormParam("senderemail") String Semail,
			@FormParam("reciveremail") String Remail) {
		RequestEntity request = new RequestEntity("", "", "");
		request.saverequest(Semail, Remail);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	@POST
	@Path("/SearchService")
	public String searchService(@FormParam("uname") String uname) {

		System.out.println("here eeee");
		Vector<UserEntity> users = UserEntity.searchUser(uname);
		JSONArray result = new JSONArray();
		for (UserEntity user : users) {
			JSONObject jobject = new JSONObject();
			jobject.put("id", user.getId());
			jobject.put("name", user.getName());
			jobject.put("email", user.getEmail());
			result.add(jobject);
		}
		System.out.println(result.toJSONString() + "heyyy");
		return result.toJSONString();

	}

	@POST
	@Path("/mshNS")
	public String MshNS(@FormParam("uemail") String uemail) {

		System.out.println("Feby & Fady");
		Vector<MessageEntity> msgs = MessageEntity.searchmsg(uemail);
		JSONArray result = new JSONArray();
		for (MessageEntity msg : msgs) {
			JSONObject jobject = new JSONObject();
			jobject.put("RevicerEmail", msg.getSender());
			jobject.put("SenderEmail", msg.getReiver());
			jobject.put("Message", msg.getMessage());
			result.add(jobject);
		}
		return result.toJSONString();

	}

	@POST
	@Path("/getRS")
	public String GetRS(@FormParam("uemail") String uemail) {

		System.out.println("here eeee");
		Vector<RequestEntity> reqS = RequestEntity.searchReq(uemail);
		JSONArray result = new JSONArray();
		for (RequestEntity req : reqS) {
			JSONObject jobject = new JSONObject();
			jobject.put("ReciverEmail", req.getSender());
			jobject.put("SenderEmail", req.getReiver());
			jobject.put("Status", req.getStatus());
			result.add(jobject);
		}
		return result.toJSONString();

	}

}
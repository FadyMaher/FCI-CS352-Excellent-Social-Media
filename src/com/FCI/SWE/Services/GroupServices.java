package com.FCI.SWE.Services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.GroupEntity;
import com.FCI.SWE.ServicesModels.UserEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class GroupServices {

	@POST
	@Path("/CreateGroupService")
	public String createGroup(@FormParam("user_id") String userId,
			@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("privacy") String privacy) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setDescription(desc);
		groupEntity.setName(name);
		groupEntity.setOwnerId(Long.parseLong(userId));
		groupEntity.setPrivacy(privacy);
		JSONObject json = new JSONObject();
		if (groupEntity.saveGroup())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}

	@POST
	@Path("/sendGroupmsgService")
	public String sendmsgService(@FormParam("convName") String convName,
			@FormParam("reciversemail") String ReciverE,
			@FormParam("gmsg") String msg) {

		UserEntity user = new UserEntity("", "", "");
		User u = User.getCurrentActiveUser();
		String currentUserEmail = u.getEmail();

		ArrayList<String> R = new ArrayList<String>();
		for (String retval : ReciverE.split(",", 100)) {
			R.add(retval);
		}

		
		
			user.saveGroupmsg(convName, currentUserEmail,R.toString(), msg);
		
		R.clear();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
}

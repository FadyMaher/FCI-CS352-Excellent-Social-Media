package com.FCI.SWE.Models;

import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class Post {
	private Long postID;
	private String postOwner;
	private String privacy;
	private String postContent;

	public Post(String postOwner, String postContent, String privacy) {
		this.postOwner = postOwner;
		this.postContent = postContent;
		this.privacy = privacy;
	}

	public Post() {
	}

	public Long getPostID() {
		return postID;
	}

	public String getPostOwner() {
		return postOwner;
	}

	public String getPostContent() {
		return postContent;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPostID(Long postD) {
		postID = postD;
	}

	public void setPostOwner(String postO) {
		postOwner = postO;
	}

	public void setPostContent(String postC) {
		postContent = postC;
	}

	public void setPostPrivacy(String postP) {
		privacy = postP;
	}

	public static Post parsePost(String json) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			Post p = new Post();
			p.setPostOwner(object.get("postOwner").toString());
			p.setPostContent(object.get("postContent").toString());
			p.setPostPrivacy(object.get("privacy").toString());
			return p;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}



	
}

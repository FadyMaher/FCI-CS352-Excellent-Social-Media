package com.FCI.SWE.ServicesModels;

import java.util.List;
import java.util.Vector;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class PostEntity {

	private static int postID;
	private String postOwner;
	private String privacy;
	private String postContent;
	private String feeling;
	private int numberOfLikes;
	private int pageID;

	public PostEntity() {
	}

	public int getPostID() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		
		return list.size();
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

	public String getFrivacy() {
		return feeling;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPostID(int postD) {
		this.postID = postD;
	}

	public void setPostOwner(String postO) {
		this.postOwner = postO;
	}

	public void setPostContent(String postC) {
		this.postContent = postC;
	}

	public void setPostPrivacy(String postP) {
		this.privacy = postP;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}

	public void setNumberOfLikes(int numOfLikes) {
		this.numberOfLikes = numOfLikes;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public Boolean saveForPublic(String postPrivacy,String postContent,String postFeeling) {
		this.privacy = "public";
		System.out.println("In SaveForPublic");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		try {
			System.out.println("HPP");
			Entity post = new Entity("Posts", list.size() + 1);
			post.setProperty("PostID", postID);
			post.setProperty("PostOwner", postOwner);
			post.setProperty("PostPrivacy", postPrivacy);
			post.setProperty("PostContent", postContent);
			post.setProperty("NumberOfLikes",numberOfLikes);
			post.setProperty("feeling", postFeeling);
			datastore.put(post);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;
	}
	
	

	public boolean saveForPage(String pageID) {
		this.privacy = "p"+pageID;
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		try {
			
			Entity post = new Entity("Posts", list.size() + 1);
			post.setProperty("PostID", postID);
			post.setProperty("PostOwner", postOwner);
			post.setProperty("PostPrivacy",privacy );
			post.setProperty("PostContent", postContent);
			post.setProperty("NumberOfLikes",numberOfLikes);
			post.setProperty("feeling",feeling);
			datastore.put(post);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}

	public boolean saveForUser(String userNames) {
		this.privacy = "!"+userNames;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		try {
			
			Entity post = new Entity("Posts", list.size() + 1);
			post.setProperty("PostID", postID);
			post.setProperty("PostOwner", postOwner);
			post.setProperty("PostPrivacy",privacy );
			post.setProperty("PostContent", postContent);
			post.setProperty("NumberOfLikes",numberOfLikes);
			post.setProperty("feeling",feeling);
			datastore.put(post);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}

}

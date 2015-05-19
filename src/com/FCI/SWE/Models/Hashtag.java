package com.FCI.SWE.Models;

import java.util.List;
import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class Hashtag {
	private String Hashtag;
	private String HashtagID;

	public String getHashtag() {
		return Hashtag;
	}

	public void setHashtag(String hashtag) {
		Hashtag = hashtag;
	}

	public String getHashtagID() {
		return HashtagID;
	}

	public void setHashtagID(String hashtagID) {
		HashtagID = hashtagID;
	}

	
	public Boolean saveHashtag(Vector<Long> v){
		int x=1;
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Transaction t = dataStore.beginTransaction();
		Query q = new Query("Post");
		PreparedQuery pq = dataStore.prepare(q);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		try {
			Entity posts = new Entity("Posts",list.size()+x);
			//posts.setProperty("HashtagID", postId);
			
			dataStore.put(posts);
			t.commit();
		} finally {
			if(t.isActive())
				t.rollback();
		}
		return true;
	}
}

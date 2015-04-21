package com.FCI.SWE.ServicesModels;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	private String name;
	private String email;
	private String password;
	private long id;
	
	

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	private void setId(long id) {
		this.id = id;
	}

	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}


	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
		}

		return null;
	}	
	public static boolean getrequest(String email, String femail) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty("ReciverEmail").toString().equals(email)
					&& entity.getProperty("SenderEmail").toString()
							.equals(femail)
					&& entity.getProperty("Status").toString().equals("false")) {

				entity.setProperty("Status", "true");
				datastore.put(entity);

				return true;
			}
		}

		return false;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("users", list.size() + 2);

			employee.setProperty("name", this.name);
			employee.setProperty("email", this.email);
			employee.setProperty("password", this.password);

			datastore.put(employee);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}

	

	// /////////////////////////////////////////////////////////////////////

	// Here i have an error on the the garpage column and int the first time of
	// runnig the code
	// the function in not work correctly

	public Boolean saveGroupmsg(String CN, String SE, String RE, String Msg) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Conversation_Messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		try {
			Entity MSG = new Entity("Conversation_Messages", list.size() + 1);
			MSG.setProperty("ConversationName", CN);
			MSG.setProperty("SenderEmail", SE);
			MSG.setProperty("RevicerEmail", RE);
			MSG.setProperty("Message", Msg);

			datastore.put(MSG);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}

	// /////////////////////////////////////////////////////////////////

	

	public static Vector<UserEntity> searchUser(String uname) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Vector<UserEntity> users = new Vector<UserEntity>();

		for (Entity entity : pq.asIterable()) {

			String Cname = entity.getProperty("name").toString();
			if (Cname.contains(uname)) {
				UserEntity user = new UserEntity(entity.getProperty("name")
						.toString(), entity.getProperty("email").toString(),
						entity.getProperty("password").toString());
				user.setId(entity.getKey().getId());
				users.add(user);
			}

		}

		return users;
	}
	
	public Boolean savePagePost(String Writer, String Post, String Page) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Pages Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		try {
			Entity PP = new Entity("Pages Posts", list.size() + 1);
			PP.setProperty("Writer",Writer );
			PP.setProperty("Post", Post);
			PP.setProperty("Page", Page);
			PP.setProperty("NumberOfSeen", " ");
			datastore.put(PP);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}
	
	public Boolean saveUserPost(String Writer, String Post, String Felling) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Users Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		try {
			Entity PP = new Entity("Users Posts", list.size() + 1);
			PP.setProperty("User",Writer);
			PP.setProperty("Post", Post);
			
			PP.setProperty("Felling", Felling);
		
			datastore.put(PP);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}
	
	
	
}

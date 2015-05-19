package com.FCI.SWE.ServicesModels;

import java.util.List;
import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class MessageEntity {
	
	private String Sender;
	private String Reciver;
	private String Message;

	public MessageEntity(String Sender,String Reciver, String Message){
		this.Sender=Sender;
		this.Reciver=Reciver;
		this.Message=Message;
		
		
	}
	
	public String getSender() {
		return Sender;
	}

	public String getReiver() {
		return Reciver;
	}

	public String getMessage() {
		return Message;
	}
	
	
	public static boolean savemsg(String SenderEmail, String ReciverEmail, String Message) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
int x=1;
		try {
			Entity MSG = new Entity("Messages", list.size() + x);
			MSG.setProperty("SenderEmail",SenderEmail);
			MSG.setProperty("RevicerEmail", ReciverEmail);
			MSG.setProperty("Message", Message);
			datastore.put(MSG);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}
	
	
	public static Vector<MessageEntity> searchmsg(String uemail) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Vector<MessageEntity> msgs = new Vector<MessageEntity>();
		for (Entity entity : pq.asIterable()) {

			String Cemail = entity.getProperty("RevicerEmail").toString();
			
			System.out.println(uemail + " is NULL ");
			
			if ( Cemail.equals(uemail)) {
				
				System.out.println("Here " + Cemail + " *");
				
				
				MessageEntity msg = new MessageEntity(entity.getProperty("RevicerEmail")
						.toString(), entity.getProperty("SenderEmail").toString(),
						entity.getProperty("Message").toString());
				
				msgs.add(msg);
				
			}

		}

		return msgs;
	}
	
	
	
	

}

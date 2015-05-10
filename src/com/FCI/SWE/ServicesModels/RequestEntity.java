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

public class RequestEntity {

	private String Sender;
	private String Reciver;
	private String Status;

	public RequestEntity(String Sender, String Reciver, String Status) {
		this.Sender = Sender;
		this.Reciver = Reciver;
		this.Status = Status;

	}

	public String getSender() {
		return Sender;
	}

	public String getReiver() {
		return Reciver;
	}

	public String getStatus() {
		return Status;
	}

	public static Boolean saverequest(String senderEmail, String recieverEmail) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Requests", list.size() + 1);
		employee.setProperty("SenderEmail", senderEmail);
		employee.setProperty("ReciverEmail", recieverEmail);
		employee.setProperty("Status", "false");
		datastore.put(employee);

		return true;
	}

	public static Vector<RequestEntity> searchReq(String uemail) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Vector<RequestEntity> reqS = new Vector<RequestEntity>();

		for (Entity entity : pq.asIterable()) {

			String Cemail = entity.getProperty("RevicerEmail").toString();

			if (Cemail.equals(uemail)) {

				RequestEntity req = new RequestEntity(entity.getProperty(
						"RevicerEmail").toString(), entity.getProperty(
						"SenderEmail").toString(), entity.getProperty("Status")
						.toString());

				reqS.add(req);
			}

		}

		return reqS;
	}

}

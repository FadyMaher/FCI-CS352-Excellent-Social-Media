package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Requests {
	
	private String Sender;
	private String Reciver;
	private String Status;

	public Requests(String Sender,String Reciver, String Status){
		this.Sender=Sender;
		this.Reciver=Reciver;
		this.Status=Status;
		
		
	}
	
	public Requests(){}

	public String getSender() {
		return Sender;
	}

	public String getReciver() {
		return Reciver;
	}

	public String getstatus() {
		return Status;
	}
	
	public void setSender(String sender) {
		Sender = sender;
	}

	public void setReciver(String reciver) {
		Reciver = reciver;
	}

	public void setstatus(String status) {
		Status = status;
	}
	
	public String toString(){
		
		return "recieverrrrrrrrrrrrrrrrrrrrr = " + Reciver ;
	}

	public static Requests ParsereqInfo(String json) {
		
		System.out.println("hereeeeeeeeee" + json);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			Requests R = new Requests();
			System.out.println( object.get("ReciverEmail").toString() + "*****" );
			R.setReciver(object.get("ReciverEmail").toString());
			R.setSender(object.get("SenderEmail").toString()) ;
			R.setstatus(object.get("Status").toString());
			return R; 
			
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	
}

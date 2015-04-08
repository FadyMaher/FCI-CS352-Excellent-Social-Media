package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Message {
	
	private String Sender;
	private String Reciver;
	private String Message;

	public Message(String Sender,String Reciver, String Message){
		this.Sender=Sender;
		this.Reciver=Reciver;
		this.Message=Message;
		
		
	}
	
	public Message(){}

	public String getSender() {
		return Sender;
	}

	public String getReciver() {
		return Reciver;
	}

	public String getMessage() {
		return Message;
	}
	
	public void setSender(String sender) {
		Sender = sender;
	}

	public void setReciver(String reciver) {
		Reciver = reciver;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	public String toString(){
		
		return "recieverrrrrrrrrrrrrrrrrrrrr = " + Reciver ;
	}

	public static Message ParsemsgInfo(String json) {
		
		System.out.println("hereeeeeeeeee" + json);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			Message m = new Message();
			System.out.println( object.get("RevicerEmail").toString() + "*****" );
			m.setReciver(object.get("RevicerEmail").toString());
			m.setSender(object.get("SenderEmail").toString()) ;
			m.setMessage(object.get("Message").toString());
			return m; 
			
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	
}

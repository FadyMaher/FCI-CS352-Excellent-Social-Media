
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



import java.util.List;
import java.util.Vector;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PageEntity {
	private String name;
	private int number_oflikes ;
	private String cateogry ;
	private String type ;

	
	public String getName(){
		return name;
	}
	
	public int getnol(){
		return number_oflikes;
	}
	

	
	

	public int getNumber_oflikes() {
		return number_oflikes;
	}

	public void setNumber_oflikes(int number_oflikes) {
		this.number_oflikes = number_oflikes;
	}

	public String getCateogry() {
		return cateogry;
	}

	public void setCateogry(String cateogry) {
		this.cateogry = cateogry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public void setnol(int nol){
		this.number_oflikes = nol;
	}
	
	
	
public static Boolean savePage(String name, String cate , String type){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("Pages");
		entity.setProperty("PageName", name);
		entity.setProperty("cateogry", cate);
		entity.setProperty("type", type);
		entity.setProperty("likes" , 0) ;
		if(datastore.put(entity)!=null)return true;
		return false;
	}
	
public PageEntity(String name, String cateogry, String type , int likes) {

	this.name = name;
	this.cateogry = cateogry;
	this.type = type;
	this.number_oflikes = likes ;
}

public static PageEntity likePage(String pname) {
	DatastoreService dataStore = DatastoreServiceFactory
			.getDatastoreService();
	Query gae = new Query("pages");
	PreparedQuery preparedQuery = dataStore.prepare(gae);
	for (Entity entity : preparedQuery.asIterable()) {
		entity.getKey().getId();
		String currentName = entity.getProperty("PageName").toString();
		if (currentName.equals(pname)) {
			int y =Integer.parseInt(entity.getProperty("likes").toString()) ;
			y++ ; 
			entity.setProperty("likes", y);
			
			PageEntity returnedPage = new PageEntity(entity.getProperty("PageName").toString(), entity.getProperty("cateogry").toString(), entity.getProperty("type").toString(), y);
			
			return returnedPage;
		}
	}
	return null;
}
	


}


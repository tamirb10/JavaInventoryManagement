package com.hit.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.hit.dao.UsersFileDao;
import com.hit.dm.Product;
import com.hit.dm.User;
import com.hit.productServies.ProductServices;
import com.hit.server.*;


public class ProductController {
	ProductServices p1;
	UsersFileDao user_Dao;
	
	public ProductController()
	{
		
	}
	private Query jsonDeconstruct(String request) throws IOException
	{
		
		Gson gs = new Gson();

		Query tmp = gs.fromJson(request, Query.class);
		
		return tmp;
		
	 
	}

	
	
	public String getResult(String a) throws JsonIOException, JsonSyntaxException, IOException, ReflectiveOperationException {
		
		Query request = this.jsonDeconstruct(a);
		Gson gs = new Gson();
		p1 = new ProductServices(request.body.search,request.body.price,request.body.category) ;
		user_Dao = new UsersFileDao();
		
		
	    switch (request.header.action) {
	           case "search":
	        	   return gs.toJson(p1.search_product(p1.getSearching(),p1.getPrice_limit(),p1.getCategory()));
		       case "save":
		    	   Product prod1 = new Product(request.body.name,request.body.price,request.body.category,request.body.quantity);
		           p1.productFileDao.save(prod1);
		      	   return "null";
		       case "deleteAll":
		           p1.productFileDao.deleteAll();
		           return "null";
		       case "delete":
			       p1.productFileDao.delete(request.body.UUID);
			       return "null";	  
		       case "get":
			       Product p2 = p1.productFileDao.get(request.body.UUID);
			       return gs.toJson(p2);   
		       case "register":
			       User user = new User(request.body.username,request.body.password);
			       user_Dao.save(user);
			       return "null";
		       case "login":
		    	   int result = user_Dao.get(request.body.username,request.body.password);
		    	   String s=String.valueOf(result);
		    	   return s;
			       
		}
	   
	   return null;
	}

}
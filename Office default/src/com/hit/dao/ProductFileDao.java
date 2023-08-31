package com.hit.dao;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.w3c.dom.NameList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.hit.dm.Product;

public class ProductFileDao implements Dao<Long,Product>,Serializable {

	
	static String path="Product.json";
	Gson gson;
	Type listType;
	ArrayList<Product> users;
	
	
	public ProductFileDao() throws IOException
	{
		gson = new GsonBuilder()
        .setLenient()
        .create();
		
		listType = new TypeToken<ArrayList<Product>>() {}.getType();
		 users = gson.fromJson(new FileReader(path) , listType);
	}
	
	public void insert_To_json() throws IOException
	{
		PrintWriter printWriter = new PrintWriter(new FileWriter(path));
		String product2 = gson.toJson(users);
		printWriter.println(product2);

		printWriter.flush();
		printWriter.close();
	}
	
	public void save(Product t) throws JsonIOException, JsonSyntaxException, IOException
	{
	
		
		if(users==null)
		{
			users=new ArrayList<Product>();
		}
		
	
		users.add(t);
		
		insert_To_json();
		
		
	}
	
	public  Product get(String t) throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
					
		
		for(Product i : users)
		{
			
			if(t.equals(i.getUUID()))
			{
				return i;
				
			}
		}
		
		return null;
	}
	
	
	public  List<Product> getAll () throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		
		return users;
				
	}
	
	public void delete(String t) throws JsonIOException, JsonSyntaxException, IOException
	{

		//נבדוק מה יש בקובץ ננסה לטעון את החומר הקיים לתוך רשימה יש מצב יצור התנגשות אבל נבדוק אחר כך
		// לדאוג למקרי קצה כאן לדוגמא םא יכניסו 0 
		
		
		
		if (!users.isEmpty())
		{
			
			for(Product i : users)
			{
				if(t.equals(i.getUUID())) {
					
					users.remove(i);
					break;
				}
			}
			
		}
		
		insert_To_json();
			
	}
	
	
	
	public void deleteAll() throws JsonIOException, JsonSyntaxException, IOException
	{

		//נבדוק מה יש בקובץ ננסה לטעון את החומר הקיים לתוך רשימה יש מצב יצור התנגשות אבל נבדוק אחר כך
		// לדאוג למקרי קצה כאן לדוגמא םא יכניסו 0 
				
		if (!users.isEmpty())
		{
			
			users.removeAll(users);
		
		}
		
		insert_To_json();
	}
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException
	{



	
		
		
	}






	
	













	

	











	




}

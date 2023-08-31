package com.hit.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.hit.dm.User;

public class UsersFileDao  {

	static String path="Users.json";
	Gson gson;
	Type listType;
	ArrayList<User> users;
	
	public UsersFileDao() throws IOException
	{
		gson = new GsonBuilder()
        .setLenient()
        .create();
		
		listType = new TypeToken<ArrayList<User>>() {}.getType();
		 users = gson.fromJson(new FileReader(path) , listType);
	}
	
	public void insert_To_json() throws IOException
	{
		PrintWriter printWriter = new PrintWriter(new FileWriter(path));
		String user1 = gson.toJson(users);
		printWriter.println(user1);

		printWriter.flush();
		printWriter.close();
	}
	
	public void save(User t) throws JsonIOException, JsonSyntaxException, IOException
	{
	
		
		if(users==null)
		{
			users=new ArrayList<User>();
		}
		
	
		users.add(t);
		
		insert_To_json();
		
		
	}
	
	public  int get(String username,String password) throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
					
		
		
		for(User i : users)
		{
			
			
			if(username.equals(i.getUsername()) && password.equals(i.getPassword()))
			{
				return 1;
				
			}
		}
		
		return 0;
	}
}

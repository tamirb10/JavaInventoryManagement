package com.hit.server;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hit.controller.ProductController;
import com.hit.dm.Product;
import com.hit.productServies.ProductServices;

public class Request implements Runnable{
	
	Socket someClient;
	//static String path="Requests.json";
	ProductController p1= new ProductController();
	
	
	public Request(Socket s) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		someClient = s;

				
	}
	
	@Override
	public void run() {

		Scanner reader;
		PrintWriter writer;
		try {
			Thread.sleep(1000);
		     reader = new Scanner(new InputStreamReader(someClient.getInputStream()));
			 writer = new PrintWriter(new OutputStreamWriter(someClient.getOutputStream()));
			
			 
			
			
			String messageIn=(String)reader.nextLine();// כאן אני מקבל את מה שהלקוח כביכול שלח אמור להיות קובץ גייסון או בקשה מסוימת איך אני אמור לקחת אותה ולבצע 
			String message= p1.getResult(messageIn);
			
			writer.println(message);
			writer.flush();
			
			writer.close();
			reader.close();
			someClient.close();
		} catch (Exception e) {
			System.out.println("Server error");
		}
	}
	
	
	

}

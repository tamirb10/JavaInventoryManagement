package application;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;


public class APIRequest {
	
	String headers ; 
	String body;
	final String _URL="127.0.0.1";
	final int _PORT=8080;
	String action;
    String _GENERIC_HEADERS="{\"host\" : \"" + _URL + "\", \"action\" : \""+action+"\"}";
	final String _POST_HEADERS="{\"host\" : \"" + _URL + "\", \"method\" : \"POST\"}";
	

	
	
	
	
	APIRequest (String action)
	{
		
		this.action=action;
		_GENERIC_HEADERS="{\"host\" : \"" + _URL + "\", \"action\" : \""+this.action+"\"}";
		this.headers=_GENERIC_HEADERS ; 
		
	}
	

	
	APIRequest (String body,String action) throws UnknownHostException, IOException
	{
		this.action=action;
		_GENERIC_HEADERS="{\"host\" : \"" + _URL + "\", \"action\" : \""+this.action+"\"}";
		this.headers= _GENERIC_HEADERS;
		this.body=body;
		
		//this.addContentLength(null);
	}
	
	
	
//	public void addContentLength(String[] args) throws UnknownHostException, IOException
//	{
//		byte[] bt = this.body.getBytes(Charset.forName("UTF-8"));
//		
//		//this.headers.add("Content-Length: "+bt.length);
//	}
	
	
	
	
	public void get(String message) throws UnknownHostException, IOException
	{
		
		String response;
		Socket socket = new Socket(_URL,_PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		out.println("{\"header\" : "+this.headers+",\"body\" : "+message+"}");
		
		out.flush();
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		while((response=in.readLine())!=null) // קורא את קובץ הגייסון עם המוצרים?
		{
			System.out.println("message from server: "+response);
			
			
		}
		
		socket.close();
		
	}
	
	
	
	
	public String post(String[] args) throws UnknownHostException, IOException
	{
		String line;
		
		//List<String> response = new ArrayList<String>();
		
		Socket socket = new Socket(_URL,_PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		out.println("{\"header\" : "+this.headers+",\"body\" : "+this.body+"}");
		out.flush();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		line=in.readLine();
	   // System.out.println(line);
	
		 
		socket.close();
		return line;
	}
	
	
	
	public int ping() throws UnknownHostException, IOException
	{
		try
		{
			this.get("{\"query\" : \"ping\"}");
			return 200;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return 400;
		}
	}
	
//	public static void main(String[] args) {
//		Gson gs= new Gson();
//		
//		APIRequest call = new APIRequest("{\"productID\":\"1\"}");
////		APIRequest call2 = new APIRequest(gs.toJson());
//		call.post(args);
//		call2.post(args);
//		
//		
//	}
	
	
	
	
//	public String get()
//	{
//		
//	}
}

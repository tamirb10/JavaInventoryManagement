package com.hit.client;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try{

				new Thread(new Runnable() {
					
					@Override
					public void run() {
						Socket myServer;
						try {
						
							
							myServer = new Socket("localhost", 12345);
							Scanner reader= new Scanner(new InputStreamReader(myServer.getInputStream()));
							PrintWriter writer = new PrintWriter(new OutputStreamWriter(myServer.getOutputStream()));
							String messageFromServer=(String)reader.nextLine();
							System.out.println("message from server: "+messageFromServer);
							writer.println("networking is so simple in java");
							writer.flush();
							
							writer.close();
							reader.close();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();


		}catch (Exception e) {}

	}
}

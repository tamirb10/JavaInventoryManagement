package com.hit.backup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.Product;

public class BackupAndRestore {
	Gson gson;
	Type listType;
	ArrayList<Product> users;
	
	PrintWriter printWriter = null;
	public BackupAndRestore()
	{
		 gson= new Gson();
		 listType = new TypeToken<ArrayList<Product>>() {}.getType();
	}

	
	
	public void backup(String fromFilePath,String toPathBackup,long delay, long period) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		  	
		   Timer timer = new Timer();
		

		  
		  
		   users = gson.fromJson(new FileReader(fromFilePath) , this.listType);
		   TimerTask backupTask = new TimerTask() {
		   
			public void run() {
				
				
				try {
					printWriter = new PrintWriter(new FileWriter(toPathBackup));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String products = gson.toJson(users);
				printWriter.println(products);

				printWriter.flush();
				printWriter.close();
				
				
			}
			
			};
			
			
			timer.scheduleAtFixedRate(backupTask, delay, period);
			}
	
	
	
	// תשמע אנחנו לא הבנו בדיוק האם ריסטור אמור לשחזר לגמרי כלומר לקחת התוכן שיש בבאק אפ ולהעביר אותו לקובץ החדש 
	//כי נתת לנו את fromfilepath וזה הנתיב של הקובץ האורגינל י
	// אולי לא הבנו נכון את השאלה מקווה שתתחשב
	
	// כי כאן אנחנו מחזירים את התוכן של הגייסון שיש בבאק אפ 
	//פשוט היה נראה לא הגיוני להשתמש הקובץ המקורי שהרי אם הוא נמחק (לא ביקשת לשחזר כאן) אלא רק להחזיר את התוכן של הבאק אפ
	
	public Object restore(String toPathBackup) throws JsonIOException, JsonSyntaxException, IOException {
		
		 users = gson.fromJson(new FileReader(toPathBackup) , listType);
		 String products = gson.toJson(users);
		 return products;
		}

	
	

	
	
	
	
	
	
	
	
}

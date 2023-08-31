package com.hit.algorithm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.hit.backup.BackupAndRestore;
import com.hit.dao.ProductFileDao;
import com.hit.dm.Product;
import com.hit.productServies.ProductServices;
import com.hit.server.Query;

import junit.framework.Assert;

public class YourProudctServiceTest {
	
	 //ProductFileDao productFileDao ;
	 ProductServices productServices ;
	 BackupAndRestore backUp;
	YourProudctServiceTest() throws IOException, ReflectiveOperationException
	{
		
		
		productServices=new ProductServices("Chess",65,"home");
	    backUp = new BackupAndRestore();
		
		
	}
	// יש לי כאן טעות אבל לא הספקתי לתקן
	public Object jsonDeconstruct(String path) throws JsonSyntaxException, JsonIOException, FileNotFoundException
	{
		
	Gson gs = new Gson();
	Object temp = gs.fromJson(new FileReader(path), Object.class);
	System.out.println(temp);
	return temp;
		
	}
	// יש לי כאן את הטעות
	@Test
	public void check_If_BackUp_Worked () throws IOException, InterruptedException 
	{
	     
		backUp.backup("Product.json", "OurBackUp.json", 10,30);
		Thread.sleep(1000);
		Object file_orig = jsonDeconstruct("Product.json");
		Object file_backup = jsonDeconstruct("OurBackUp.json");
		Assert.assertEquals(file_orig, file_backup); 
		 
	}
	
	
	
// כל פעם מתבצעות מחיקות של המוצרים ,שאנחנו בודקים על מנת שלא יפגע בדטא בייס שלנו ויכניס אליו מוצרים שאנחנו שסתם נועדו לבדיקה ולא קיימים באמת
	
	@Test
	public void check_if_saved_and_delete () throws IOException 
	{
	     
		 Product prod1=new Product("chair",20,"home",30);
		 productServices.productFileDao.save(prod1);
	     List<Product> list = productServices.productFileDao.getAll();
	     Assert.assertEquals(prod1.getUUID(), list.get(list.size()-1).getUUID());
	     productServices.productFileDao.delete(prod1.getUUID());
	     
	}
	
	@Test
	public void check_get_product () throws IOException // by uuid
	{
	     
		 Product prod1=new Product("chair",20,"home",30);
		 productServices.productFileDao.save(prod1);
	     Product prod2=productServices.productFileDao.get(prod1.getUUID());
	     Assert.assertEquals(prod1.getUUID(), prod2.getUUID());
	     productServices.productFileDao.delete(prod1.getUUID());
	     
	}
	
	
	@Test
	public void search_By_all_fields() throws  IOException, ReflectiveOperationException
	{	
		 Product prod1=new Product("Chess game",60,"Play",40);
		 productServices = new ProductServices("Chess",65,"home");
		 productServices.productFileDao.save(prod1);
		
		Assert.assertEquals(true,(productServices.search_product(productServices.getSearching(),productServices.getPrice_limit(),productServices.getCategory()).isEmpty()));
		productServices.setCategory("Play");
		Assert.assertEquals(false,(productServices.search_product(productServices.getSearching(),productServices.getPrice_limit(),productServices.getCategory()).isEmpty()));
		productServices.setPrice_limit(50);
		Assert.assertEquals(true,(productServices.search_product(productServices.getSearching(),productServices.getPrice_limit(),productServices.getCategory()).isEmpty()));
		productServices.setSearching("Chess");
		productServices.setPrice_limit(65);
		Assert.assertEquals(false,(productServices.search_product(productServices.getSearching(),productServices.getPrice_limit(),productServices.getCategory()).isEmpty()));
		                         
		productServices.productFileDao.delete(prod1.getUUID());
	}
	
	
	
	
	
	
	public static void main(String[] args) throws IOException, ReflectiveOperationException {
		YourProudctServiceTest p1 = new YourProudctServiceTest();
		p1.jsonDeconstruct("Product.json");
	}

}

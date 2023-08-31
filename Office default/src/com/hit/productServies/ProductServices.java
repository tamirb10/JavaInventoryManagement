package com.hit.productServies;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.hit.algorithm.IAlgoStringMatching;
import com.hit.dao.Dao;
import com.hit.dao.ProductFileDao;
import com.hit.dm.AlgoStringMatch;

import com.hit.dm.Product;

public class ProductServices {

	public  ProductFileDao productFileDao; 	
	String searching;
	double price_limit;
	String category;
	//כל הערכים האלה אמורים להתחבר לתיבות טקטט בJAVA FX 
	
	
	// constructors different search receives
	public ProductServices(String search) throws IOException, ReflectiveOperationException
	{
		productFileDao = new ProductFileDao();
		this.searching=search;
		this.price_limit=-1;
		this.category=null;
	}
	
	
	public ProductServices(String search,double price,String cat) throws IOException, ReflectiveOperationException
	{
		productFileDao = new ProductFileDao();
		this.searching=search;
		this.price_limit=price;
		this.category=cat;
	}
	public ProductServices(String search,String cat) throws IOException, ReflectiveOperationException
	{
		productFileDao = new ProductFileDao();
		this.searching=search;
		this.price_limit=-1;
		this.category=cat;
	}
	public ProductServices(String search,double price) throws IOException, ReflectiveOperationException
	{
		productFileDao = new ProductFileDao();
		this.searching=search;
		this.price_limit=price;
		this.category=null;
	}
	
//getters setters
	
public String getSearching() {
		return searching;
	}


	public void setSearching(String searching) {
		this.searching = searching;
	}


	public double getPrice_limit() {
		return price_limit;
	}


	public void setPrice_limit(double price_limit) {
		this.price_limit = price_limit;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	
////////////////////////////////////////

// מציאת רשימת המוצרים לפי ההשם שהלקוח נותן	
public List<Product> search_product (String str,double price,String cat) throws JsonIOException, JsonSyntaxException, FileNotFoundException
{
	List<Product> list_of_products = new ArrayList<Product>();
	List<Product> list_of_products_chosen = new ArrayList<Product>();
	list_of_products=productFileDao.getAll();

	
	
	for(Product i:list_of_products)
	   {
		
			if(cat.equals("null"))
			{
				if (AlgoStringMatch.kmpFind(str,i.getName())==1)//ניתן לשנות את האלגוריתם כאן טוב? לא טוב? לא ידוע - לבחור אימפלמנטציה שונה
					{
						list_of_products_chosen.add(i);
					}
			}
			
			else
			{
				if (AlgoStringMatch.kmpFind(str,i.getName())==1 && AlgoStringMatch.kmpFind(cat,i.getCategory())==1 )//ניתן לשנות את האלגוריתם כאן טוב? לא טוב? לא ידוע - לבחור אימפלמנטציה שונה
					{
						list_of_products_chosen.add(i);
					}
			}
		
	   }
	
	
	
	if(price>0 )
	{
	
		if(!list_of_products_chosen.isEmpty())
		{
			List<Product> removable=new ArrayList<Product>();;
			for(Product i:list_of_products_chosen)
			{
				if(i.getPrice()>price )
				{
					
					removable.add(i);
				}
			
			}
			list_of_products_chosen.removeAll(removable);
			
			if(!list_of_products_chosen.isEmpty())
				{
				 Collections.sort(list_of_products_chosen, Collections.reverseOrder());
					//list_of_products_chosen.sort(null);
				}
		}
	}
	
	return list_of_products_chosen;
	
}
	






	public static void main(String[] args) throws IOException, ReflectiveOperationException {
		
//		List<Product> list_of_products = new ArrayList<Product>();
//		//בעייתיות קונסטרקטור בגלל סטאטיק
//		ProductServices productServices = new ProductServices("rod",50);
//		//productServices.productFileDao.deleteAll();
//		
////		System.out.println(productServices.productFileDao.get("c406c2a5-e698-4408-8a96-d47c48516497"));
////	    
////		productServices.productFileDao.delete("c406c2a5-e698-4408-8a96-d47c48516497");
////		
////		System.out.println(productServices.productFileDao.get("c406c2a5-e698-4408-8a96-d47c48516497"));
//		//System.out.println(productServices.productFileDao.get("6c4e6e6d-f6c8-4697-a1f3-24cb1851ef2b"));
//		
//	    Product prod1=new Product("Chess game",60,"Play",40);
//	    productServices.productFileDao.save(prod1);
	    //productServices.productFileDao.createProduct(prod1);
//	    prod1=new Product("maple",22,"home",22);
//	    productServices.productFileDao.save(prod1);
//	    
//	    prod1=new Product("broom",70,"computer",52);
//	    productServices.productFileDao.save(prod1);
//	   
//	    
//	    prod1=new Product("fishing rod",60,"computer",0);
//	    productServices.productFileDao.save(prod1);
//	    
//	    prod1=new Product("chair",3,"home",9);
//	    productServices.productFileDao.save(prod1);
//	
//        prod1=new Product("bair rod",60,"books",78);
//        productServices.productFileDao.save(prod1);
		
//        list_of_products=productServices.search_product(productServices.getSearching(),productServices.getPrice_limit(),productServices.getCategory());
////       
//		for(Product i:list_of_products)
//		   {
//			
//			System.out.println(i);
//			
//	       }
		
        
        
// מציאת מוצר לפי ID שלו		
		 
//		prod1=productFileDao.get((long)6);
//		System.out.println(prod1.getId());
//		System.out.println(prod1.getName());
//		System.out.println(prod1.getPrice());
//		prod1=productFileDao.get((long)3);
//		System.out.println(prod1.getId());
//		System.out.println(prod1.getName());
//		System.out.println(prod1.getPrice());
//		prod1=productFileDao.get((long)2);
//		System.out.println(prod1.getId());
//		System.out.println(prod1.getName());
//		System.out.println(prod1.getPrice());

	
		
				
// מציאת כל המוצרים שקיימים במפה		
		
//		List<Product> list_of_products = new ArrayList<Product>();
//		list_of_products=productFileDao.getAll();
//		for(Product i:list_of_products)
//		   {
//			System.out.println(i.getCategory());
//			System.out.println(i.getId());
//				System.out.println(i.getPrice());
//				System.out.println(i.getName());
//				System.out.println();
//			}
		
		
// מחיקת מוצר לפי ID שלו
		
//		productFileDao.delete((long)3);
//		System.out.println();
//	
//		prod1=productFileDao.get((long)(3));
//		System.out.println(prod1.getId());
//	    System.out.println(prod1.getName());
//    	System.out.println(prod1.getPrice());
//מומלץ להריץ את הרשימה לראות שזה באמת חסר		
	
		
		
  		
	
		
		
		
		
		
		
		
		
		
    	//Collections.sort(hm);
//		List<Integer,Double> list_of_id = new ArrayList<Integer,Double>();
//		List<Product> list_of_products = new ArrayList<Product>();
//		List<Product> chosen_list = new ArrayList<Product>();
		

		
//		for (Entry<Long, Product> entry : hm.entrySet()) {
//			 System.out.println("Price : (" + entry.getValue().getPrice()+("Name : (" + entry.getValue().getName()
//		             + ")"
//             + "), Value : "
//             + entry.getKey()));
//		}
		
		
//		for(Product i:pq)
//		{
//			System.out.println(i.getCategory());
//			System.out.println(i.getId());
//			System.out.println(i.getPrice());
//			System.out.println(i.getName());
//			System.out.println();
//		}
//		
//		  for(Product i : list_of_products) {
//	            
////	            System.out.println(i.getName());
////	            System.out.println(i.getPrice());
//	            if (AlgoStringMatch.naiveFind("a",i.getName())==1)
//	            {
//	            	chosen_list.add(i);
//	            }
//	        }
//		  
//		
//		  
//		  for(Product i : chosen_list) {
//			    System.out.println(i.getId());
//	            System.out.println(i.getName());
//	            System.out.println(i.getPrice());
//	            System.out.println();
//	            
//	        }
//		
//		  chosen_list.sort(null);
//		  System.out.println();
//		  System.out.println();
//		  
//		  
//		  for(Product i : chosen_list) {
//			    System.out.println(i.getId());
//	            System.out.println(i.getName());
//	            System.out.println(i.getPrice());
//	            System.out.println();
//	            
//	        }
		
	}
}

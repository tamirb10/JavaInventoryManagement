package application;
import java.util.UUID;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Product implements Comparable<Product>,Serializable{
	
	
	
	private  UUID uuid ;
	private String name;
	private String category;
	private int quantity;
	private double price;
	public Product(String name,double price,String cat,int quantity)
	{
		this.uuid = UUID.randomUUID();
		this.quantity=quantity;
		this.name=name;
		this.price=price;
		this.category = cat;
	}
	
	public void buyProduct()
	{
		if(this.quantity>0)
		{
			this.setQuantity(this.quantity-1);
		}
		else
		{
			System.out.println("No items left");
		}
	}
	
	public void addQuantity(int t)
	{
		this.setQuantity(this.getQuantity()+t);
	}
	
	
//setters	
	public void setUUID(UUID id) {
		this.uuid = id;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public void setCategory(String category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
//getters
	public String getCategory() {
		return category;
	}
	public String getName()
	 	{
		 return this.name;
	 	}
	 
	 public String getUUID()
		{
		return this.uuid.toString();
		}
	 public UUID getUUIDNOT()
		{
		return this.uuid;
		}
	 
	 public double getPrice()
		{
		return this.price;
		}
	 public int getQuantity() {
		 return quantity;
	 }
	


	@Override
	 public int compareTo(Product o) {
		    if(this.price<o.price)
		          return -1;
		    else if(o.price<this.price)
		          return 1;
		    return 0;
		
	 }

	@Override
	public String toString() {
		String uuidAsString=uuid.toString();

		return  " Name: " + name + " | Category: " + category + " | Price: " + price + " |  Quantity: " + quantity + " ";
	}
	


}

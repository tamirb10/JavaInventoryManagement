package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.hit.dm.Product;
//import com.hit.productServies.ProductServices;
//import com.hit.server.Query;


import javafx.event.ActionEvent;

public class MainSceneController {
	@FXML  final String SERVER="127.0.0.1:8080/";
	//הטקסט של החיפוש שלנו בעמוד הראשי
	@FXML private TextField tfTitle;
	@FXML private TextField tfTitle1;
	@FXML private TextField tfTitle11;
	String result;
	//הטקסט של ההוספת מוצר בעמוד המשני של המלאי
	@FXML private TextField field1;
	@FXML private TextField field2;
	@FXML private TextField field3;
	@FXML private TextField field4;
	
	// הטקסט של מחיקה ומחיקת הכל
	
	@FXML private TextField field5;
	
   // טקסט של עריכת כמות של מוצר
	@FXML private TextField field6;
	@FXML private TextField field7;
	
	//login details
	@FXML private TextField user_login;
	@FXML private PasswordField password_login1;
	private static Product p1;
	
	@FXML private Button  btn_login;
	@FXML private Button  btn_register;
	
	@FXML private Button  btn_exit_login;
	
	@FXML private Button btn_search;
	@FXML private Button btnitem;
	@FXML private Button btnback;
	@FXML private Button exit2;
	@FXML private Button btnexit;
	@FXML private Button btndeleteall;
	@FXML private Button btndelete;
	@FXML private GraphicsContext gc;
	@FXML private TextArea textArea1;
	
	@FXML private TextArea textarea2;
	@FXML private ListView<Product> listview;
	
	private Product jsonDeconstruct(String request) throws IOException
	{
		
		Gson gs = new Gson();
		
		Product tmp = gs.fromJson(request, Product.class);
		
		return tmp;
		
		
	}
	
	
	@FXML
	public void clickedExitLogin(ActionEvent event) throws UnknownHostException, IOException {
		Stage stage = (Stage) btn_exit_login.getScene().getWindow();
		stage.close();
	}
	
	
	@FXML
	public void clicked_Login(ActionEvent event) throws UnknownHostException, IOException {
		String username = user_login.getText();
		String password = password_login1.getText();
		
		if(username!="" && password!="")
		{
		String body="{\"username\" : \"" + username + "\", \"password\" : \""+password+"\"}";
		APIRequest call = new APIRequest(body,"login");
		result=call.post(null);
		
		if(result.equals("1")) {
			Stage stage = (Stage) btn_login.getScene().getWindow();
			stage.close();
			Stage primaryStage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			primaryStage.setTitle("Office Default");
			primaryStage.setScene(new Scene(root,515,530));
			primaryStage.show();
		}
		else
		{	
			Stage primaryStage = new Stage();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("NotFound.fxml"));
			primaryStage.setTitle("Error");
			primaryStage.setScene(new Scene(root,300,50));
			primaryStage.show();
		}
		
		}
	}
	
	@FXML
	public void clicked_Register(ActionEvent event) throws UnknownHostException, IOException {
		String username = user_login.getText();
		String password =  password_login1.getText();
		if(username!="" && password!="")
		{
		String body="{\"username\" : \"" + username + "\", \"password\" : \""+password+"\"}";
		APIRequest call = new APIRequest(body,"register");
		call.post(null);
		}
	}
	
	
	
	@FXML
	public void btnClearClicked(ActionEvent event) {
		listview.getItems().clear();
	}
	
	@FXML
	public void btnDeleteClicked(ActionEvent event) throws IOException, ReflectiveOperationException {
		String id = field5.getText();
		
		String body="{\"UUID\" : \"" + id + "\"}";
		APIRequest call = new APIRequest(body,"delete");
		result=call.post(null);
		
	}
	

	
	@FXML
	public void btnSetClicked(ActionEvent event) throws IOException, ReflectiveOperationException {
		String id = field6.getText();
		String body="{\"UUID\" : \"" + id + "\"}";
		APIRequest call = new APIRequest(body,"get");
		result=call.post(null);
		Product p1 =jsonDeconstruct(result);
		
	
		
		String quantity = field7.getText();
		int val1 = Integer.parseInt(quantity);
	    call = new APIRequest(body,"delete");
		call.post(null);
		
		
		p1.addQuantity(val1);
	    body="{\"name\" : \"" + p1.getName() + "\", \"price\" : \""+p1.getPrice()+"\",\"category\" : \""+p1.getCategory()+"\",\"quantity\" : \""+p1.getQuantity()+"\"}";
	    call = new APIRequest(body,"save");
	 	call.post(null);
		
	 	
			
		
		
	}
	
	@FXML
	public void btnExitClicked(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnexit.getScene().getWindow();
		stage.close();
	
	}
	@FXML
	public void btnExit2(ActionEvent event) throws IOException {
		Stage stage = (Stage) exit2.getScene().getWindow();
		stage.close();
	   
	}
	
	@FXML
	public void onListViewClicked(MouseEvent event) throws IOException {
		//primaryStage.setTitle("Office Default");
		 p1=listview.getSelectionModel().getSelectedItem();
		 
		 if(p1!=null)
		 {
			 textarea2 = new TextArea();
			 Stage primaryStage = new Stage();
			 Parent root = (Parent)FXMLLoader.load(getClass().getResource("PopUp.fxml"));
			 
			 primaryStage.setScene(new Scene(root,280,100));
			 primaryStage.show();
		 }
		 
		 
	}
	
	@FXML
	public void showUuid(ActionEvent event) throws IOException {

		textarea2.setText(p1.getUUID());
		textarea2.setEditable(false);
		 
		 
	}
	
	
	
	
	@FXML
	public void btnDeleteAllClicked(ActionEvent event) throws IOException, ReflectiveOperationException {
		String body="";
	    APIRequest call = new APIRequest(body,"deleteAll");
	    result=call.post(null);
		
		
	}
	
	@FXML
	public void btnBackClicked(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnback.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		primaryStage.setTitle("Office Default");
		primaryStage.setScene(new Scene(root,515,530));
		primaryStage.show();
	}
	
	@FXML
	public void btnItemClicked(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnitem.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("ItemInventory.fxml"));
	    primaryStage.setTitle("Office Default");
		primaryStage.setScene(new Scene(root,550,450));
		primaryStage.show();
	}
	
	
	
	@FXML
	public void btnAddClicked(ActionEvent event) throws IOException, ReflectiveOperationException {
		//ProductServices productServices = new ProductServices("sadsd");
		// לנטרל מצב שיכניסו משהו ריק לעשות שיהיה לא אפשרי!
		double val=0;
		int val1=0;
		
		String Name = field1.getText();
		String category = field2.getText();
		String price = field3.getText();
		String quantity = field4.getText();
		if(Name.equals("")||category.equals("")||price.equals("")||quantity.equals(""))
		{
			return;
		}
	
		
		
		
			 val = Double.parseDouble(price);
			
			 val1 = Integer.parseInt(quantity);
			
		
		
		String body="{\"name\" : \"" + Name + "\", \"price\" : \""+val+"\",\"category\" : \""+category+"\",\"quantity\" : \""+val1+"\"}";
	    APIRequest call = new APIRequest(body,"save");
	    result=call.post(null);
	    
	    

	}
	

	
	@FXML
	public void btnSearchClicked(ActionEvent event) throws UnknownHostException, IOException  {
		
		Gson gson=new Gson();
		Type listType;
		ArrayList<Product> products;
		
		listType = new TypeToken<ArrayList<Product>>() {}.getType();
	
	
	       
	       String title = tfTitle.getText();
           if(title=="")
           {
        	  title="דגדגדג" ;
           }
           
           String title1 = tfTitle1.getText();
           if(title1=="")
           {
        	   title1=null;
           }
           
           String title11 = tfTitle11.getText();
           if(title11=="")
           {
        	   title11="-1";
           }
           
	       double val = Double.parseDouble(title11);
	           
	       String body="{\"search\" : \"" + title + "\", \"price\" : \""+val+"\",\"category\" : \""+title1+"\"}";
	       APIRequest call = new APIRequest(body,"search");
	       result=call.post(null);
	       products = gson.fromJson(result,listType);
	              
	        if(products!=null)
	        {
	        listview.getItems().clear();
			for(Product i:products)
			   {
				
				listview.getItems().add(i);
			
		       }
			
	        }
	}
}



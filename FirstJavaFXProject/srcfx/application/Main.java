package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	
        		
            Parent root = (Parent)FXMLLoader.load(getClass().getResource("User-login.fxml"));
            Scene scene =  new Scene(root,470,300);
                  
            
   	      //Creating a Scene 
    		      
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println();
    }
}

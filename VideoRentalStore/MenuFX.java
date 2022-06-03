package VideoRentalStore;


import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.sql.*;

/**
 *  This is main class using  for UI(JavaFX)
 * 
 * @author kazuhisa kondo
 */
public class MenuFX extends Application {
	
	// Declaration for scene variables
	public static Scene sceneMain = null;  //for Main Menu
	public static Scene sceneCustomer = null; // for Customer Menu
	public static Scene sceneAddCustomer = null; // for Add Customer
	public static Scene sceneVideo = null; // for Video Menu
	public static Scene sceneAddVideo = null; // for Add Video
	public static Scene sceneRental = null; // for rent video
	public static Scene sceneReturn = null; // for return video
	public static Scene sceneShowRenting = null; // for show renting video lists
	public static Scene sceneShowOverdue = null; // for show overdue lists
	public static Scene sceneFile = null; // for file save and read 
	public static final ObservableList data = FXCollections.observableArrayList();
	
	//database valuables
	/*public static Connection con;
	public static Statement stmt;  
	*/
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setOnCloseRequest((WindowEvent t) -> {
            abortAction(t);
        });
		// set date format for New Zealand
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   
		/** Object for storing customers data using HashMap */
		Map<Integer,Customer> customerMap = new HashMap<>();
		
		/** Object for storing video data using HashMap */
		Map<Integer,rentalVideo> rentalVideoMap = new HashMap<>();
		
		
		//Set Stage title and stage size for Main Form
		stage.setTitle("Rental Video System");
		stage.setWidth(500);
		stage.setHeight(500);
		
		//Set Stage size for sub Form
		Stage stage2 = new Stage();
		stage2.setWidth(1000);
		stage2.setHeight(400);
		
		
		// Create stage
		initMainForm(stage,stage2,rentalVideoMap,customerMap);
		CustomerFX.initCustomerForm(stage2,customerMap);
		VideoFX.initVideoForm(stage2,rentalVideoMap,customerMap);
		VideoFX.rentVideoForm(stage2,rentalVideoMap,customerMap);
		VideoFX.returnVideoForm(stage2,rentalVideoMap,customerMap);
		VideoFX.initShowRentingVideoListsForm(stage2,rentalVideoMap);
		VideoFX.initShowOverdueVideoListsForm(stage2,rentalVideoMap);
		FileFX.fileForm(stage2,rentalVideoMap,customerMap);                       
		

		stage.setScene(sceneMain);
		stage.show();
			
	}
	
	public static void initMainForm(Stage stage,Stage stage2,Map<Integer,rentalVideo> rentalVideoMap,Map<Integer,Customer> customerMap) {
	   // AnchorPane root = new AnchorPane();
	  //  sceneMain = new Scene(root);

	    GridPane grid = new GridPane();
	    sceneMain = new Scene(grid);
		
	    grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 30, 10));
		grid.setAlignment(Pos.TOP_CENTER); 
		Label label1 = new Label("Video Rental System Menu");
		label1.setFont(new Font("Haettenschweiler", 48)); //Arial
		label1.setTextFill(Color.web("#0000FF"));
		
		grid.add(label1, 1, 0);
	    
		// button initialization
		GridPane btnGrid = new GridPane();
		//btnGrid.setHgap(5);
		//btnGrid.setVgap(5);
		btnGrid.setAlignment(Pos.CENTER); 
		String [] btnNames = {"Manage Customer","Manage Video","Rent Video","Return Video",
				              "Show renting Lists","Show overdue Lists","Exit"};
		int btnWidth=250;
		int btnHeight=40;
		Font btnFont = new Font("Verdana", 14); //Verdana, Helvetica,Microsoft Sans Serif
		int btnNumber =0;
		Button[] btn =	new Button [btnNames.length];
		for(String name: btnNames) {
			btn[btnNumber] = new Button(name);
			btn[btnNumber].setPrefWidth(btnWidth);
			btn[btnNumber].setPrefHeight(btnHeight);
			btn[btnNumber].setFont(btnFont);
			btn[btnNumber].setAlignment(Pos.CENTER);
			btnGrid.add(btn[btnNumber], 1, btnNumber+2);
		    btnNumber++;
		}
		
		btn[0].setOnMouseClicked(event -> {
			CustomerFX.initCustomerForm(stage2,customerMap);
	    	stage2.setTitle("Customer menu");	
	    	setScene(stage2,sceneCustomer);
	    });
		
		btn[1].setOnMouseClicked(event -> {
			VideoFX.initVideoForm(stage2,rentalVideoMap,customerMap);
	    	stage2.setTitle("Video menu");	
	    	setScene(stage2,sceneVideo);
	    });
		
		
		btn[2].setOnMouseClicked(event -> {
	    	stage2.setTitle("Rent Video");	
	    	setScene(stage2,sceneRental);
	    });
		
		btn[3].setOnMouseClicked(event -> {
	    	stage2.setTitle("Return Video");	
	    	setScene(stage2,sceneReturn);
	    });
		
		btn[4].setOnMouseClicked(event -> {
			 VideoFX.initShowRentingVideoListsForm(stage2,rentalVideoMap);
			
			stage2.setTitle("Show renting lists");	
	    	setScene(stage2,sceneShowRenting);
	    });
		
		btn[5].setOnMouseClicked(event -> {
			VideoFX.initShowOverdueVideoListsForm(stage2,rentalVideoMap);
			
			stage2.setTitle("Show overdue lists");	
	    	setScene(stage2,sceneShowOverdue);
	    });
		
		
		
		//Exit stop application
		btn[6].setOnMouseClicked(event -> {
			//abortAction(WINDOW_CLOSE_REQUEST)
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Confirmation");
	        alert.setHeaderText(null);
	        alert.setContentText("Exit rental video system?");

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == ButtonType.OK) {
	            System.exit(0);
	        } else {
	         //  t.consume();
	        }
			
	    });
		
		
		grid.add(btnGrid,1,2);
		
	  }
	
	
	public static  void setScene(Stage stage,Scene changeScene) {
		    stage.setScene(changeScene);
		    stage.show();
	}
	
	private void abortAction(WindowEvent t) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Exit rental video system?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
           t.consume();
        }
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
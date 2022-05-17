package VideoRentalStore;


import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

public class MenuFX extends Application {
	
	// Declaration for scene variables
	public static Scene sceneMain = null;  //for Main Menu
	public static Scene sceneCustomer = null; // for Customer Menu
	public static Scene sceneAddCustomer = null; // for Add Customer
	
	public static final ObservableList data = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setOnCloseRequest((WindowEvent t) -> {
            abortAction(t);
        });
		
		//Set Stage title and stage size for Main Form
		stage.setTitle("Rental Video System");
		stage.setWidth(500);
		stage.setHeight(500);
		
		//Set Stage size for other Form
		Stage stage2 = new Stage();
		//stage2.setTitle("Customer");
		stage2.setWidth(1000);
		stage2.setHeight(400);
		
		
		// Create stage
		initMainForm(stage,stage2);
		initCustomerForm(stage2);

		stage.setScene(sceneMain);
		stage.show();
			
	}
	
	public static void initMainForm(Stage stage,Stage stage2) {
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
		//Label label2 = new Label("The first JavaFX program");
		//layout.getChildren().add(label1);
		//layout.getChildren().add(label2);
		grid.add(label1, 1, 0);
	    
		// button initialization
		GridPane btnGrid = new GridPane();
		//btnGrid.setHgap(5);
		//btnGrid.setVgap(5);
		btnGrid.setAlignment(Pos.CENTER); 
		String [] btnNames = {"Manage Customer","Manage Video","Rent Video","Return Video",
				              "Show renting Lists","Show overdue Lists","Save data","Read data",
				              "Exit"};
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
	    	stage2.setTitle("Customer menu");	
	    	setScene(stage2,sceneCustomer);
	    });
		
		//Exit stop application
		btn[8].setOnMouseClicked(event -> {
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
			
			//stage.close();
			//Platform.exit();
	       // System.exit(0);
		/*
	        stage.setOnCloseRequest((WindowEvent t) -> {
				abortAction(WindowEvent.WINDOW_CLOSE_REQUEST);
	        }); */
	    });
		
		
		grid.add(btnGrid,1,2);
		
	  }
	
	public static void initCustomerForm(Stage stage) {
		/** Object for storing customers data using HashMap */
		Map<Integer,Customer> customerMap = new HashMap<>();
		
		// Table view
        final TableView<Customer> table = new TableView<>();
        final ObservableList<Customer> tvObservableList = FXCollections.observableArrayList();
        table.setEditable(true);
		
		AnchorPane root = new AnchorPane();
	    sceneCustomer = new Scene(root);
	   // sceneAddCustomer = new Scene(root);

	    // Add Customer
        CustomerFX.addCustomerFX(stage, table);
	    
	    HBox hb = new HBox();
	    Label label1 = new Label("Manage Customer");
	    label1.setFont(new Font("Verdana", 24)); //Arial
	    label1.setTextFill(Color.web("#0000FF"));
	    
	    Button btn1 = new Button("Add");
	    btn1.setOnMouseClicked(event -> {
	    	//stage.close();
	    	
	    	setScene(stage,sceneAddCustomer);
	    });
	    Button btn2 = new Button("Delete");
	    Button btn3 = new Button("Close");
	    Button btn4 = new Button("test");
	    btn1.setPrefWidth(100);
	    btn2.setPrefWidth(100);
	    btn3.setPrefWidth(100);
	    //btn2.setPrefHeight(40);
	    btn3.setOnMouseClicked(event -> {
	    	stage.close();
	    	
	    //	setScene(stage,sceneMain);
	    });
	    btn4.setOnMouseClicked(event -> {
	    	 System.out.println(customerMap.get(101));
	         if(table.getSelectionModel().getSelectedItem() != null) { 
	    	 Customer customer1 = table.getSelectionModel().getSelectedItem();
	    	 System.out.println(customer1.getFirstName());   
	         }
	    });
	    hb.getChildren().addAll(btn1,btn2,btn3,btn4);
	    //AnchorPane.setTopAnchor(btn, 30.0);
	    //AnchorPane.setTopAnchor(lbl, 10.0);
	    //root.getChildren().addAll(btn,lbl);
	    
	    
	    
	    
		
	    final ListView listView = new ListView(data);
	    listView.setPrefWidth(1000);
	    Main.addCustomerTestData(customerMap);
	    for(Integer key: customerMap.keySet()){
			System.out.println("ID:" + key +" "+ customerMap.get(key));
			data.add("ID:" + key +" "+ customerMap.get(key).toLine());
	    }
        
       
        listView.setItems(data);
        
        
        
        // Table appearance
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(600);
        table.setPrefHeight(600);
       
        
        // Data set for TableView
        for(Integer key: customerMap.keySet()){
			System.out.println("ID:" + key +" "+ customerMap.get(key));
			tvObservableList.add(customerMap.get(key));
			//data.add("ID:" + key +" "+ customerMap.get(key).toLine());
	    }
        table.setItems(tvObservableList);
  
        TableColumn<Customer,Integer> colId = new TableColumn<>("ID");
        colId.setMinWidth(100);
        colId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        //colId.setCellFactory(TextFieldTableCell.forTableColumn());
        //colId.setCellFactory(col-> new IntegerEditingCell());
        //colId.setCellFactory(Integer.parseInt(TextFieldTableCell.forTableColumn()));
        colId.setOnEditCommit(
            new EventHandler<CellEditEvent<Customer, Integer>>() {
                @Override
                public void handle(CellEditEvent<Customer, Integer> t) {
                    ((Customer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setCustomerId(t.getNewValue());
                }
            }
        );
        
        
        /*
        TableColumn<Customer, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colId.setMinWidth(5);
        colId.setOnEditCommit(
                new EventHandler<CellEditEvent<Customer, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Customer, Integer> t) {
                        ((Customer) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setCustomerId(t.getNewValue());
                    }
                }
            );
        */
        /*
        TableColumn<Customer, String> colFName = new TableColumn<>("First Name");
        colFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colFName.setOnEditCommit(
                new EventHandler<CellEditEvent<Customer, String>>() {
                    @Override
                    public void handle(CellEditEvent<Customer, String> t) {
                        ((Customer) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setFirstName(t.getNewValue());
                    }
                }
            ); */
        //table.setEditable(true);
        
        TableColumn<Customer,String> colFName = new TableColumn<>("First Name");
        colFName.setMinWidth(100);
        colFName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        colFName.setCellFactory(TextFieldTableCell.forTableColumn());
        colFName.setOnEditCommit(
            new EventHandler<CellEditEvent<Customer, String>>() {
                @Override
                public void handle(CellEditEvent<Customer, String> t) {
                    ((Customer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setFirstName(t.getNewValue());
                }
            }
        );
        
        TableColumn<Customer,String> colLName = new TableColumn<>("Last Name");
        colLName.setMinWidth(100);
        colLName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        colLName.setCellFactory(TextFieldTableCell.forTableColumn());
        colLName.setOnEditCommit(
            new EventHandler<CellEditEvent<Customer, String>>() {
                @Override
                public void handle(CellEditEvent<Customer, String> t) {
                    ((Customer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setLastName(t.getNewValue());
                }
            }
        );
        
        
        TableColumn<Customer,LocalDate> colDOB = new TableColumn<>("DOB");
        colDOB.setMinWidth(100);
        colDOB.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("DOB"));
        //colDOB.setCellFactory(TextFieldTableCell.forTableColumn());
        colDOB.setOnEditCommit(
            new EventHandler<CellEditEvent<Customer, LocalDate>>() {
                @Override
                public void handle(CellEditEvent<Customer, LocalDate> t) {
                    ((Customer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setDOB(t.getNewValue());
                }
            }
        );
        
        
        System.out.println(customerMap.get(101));
        /*
        TableColumn<Customer, String> colLName = new TableColumn<>("Last Name");
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
*/
        
        //table.setEditable(true);
        table.getColumns().addAll(colId,colFName,colLName,colDOB);
        root.getChildren().addAll(table);
        
	    //StackPane root = new StackPane();
        //AnchorPane.setTopAnchor(btn, 10.0);
	    //AnchorPane.setTopAnchor(listView, 30.0);
        //root.getChildren().addAll(listView,btn);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label1, hb,table);
        root.getChildren().addAll(vbox);
        //((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        
    
        
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
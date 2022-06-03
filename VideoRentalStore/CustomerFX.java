package VideoRentalStore;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *  This is class using manage customer for UI(JavaFX)
 * 
 */
public class CustomerFX{
	public static void initCustomerForm(Stage stage,Map<Integer,Customer> customerMap) {
		
		
		// Table view
        final TableView<Customer> table = new TableView<>();
        final ObservableList<Customer> tvObservableList = FXCollections.observableArrayList();
        table.setEditable(true);
		
		AnchorPane root = new AnchorPane();
	    MenuFX.sceneCustomer = new Scene(root);
	   
	    // Add Customer
        CustomerFX.addCustomerFX(stage, customerMap,tvObservableList);
	    
	    HBox hb = new HBox();
	    Label label1 = new Label("Manage Customer");
	    label1.setFont(new Font("Verdana", 24)); //Arial
	    label1.setTextFill(Color.web("#0000FF"));
	    
	    Button btn1 = new Button("Add");
	    Button btn2 = new Button("Delete");
	    Button btn3 = new Button("Close");
	    Button btn4 = new Button("test");
	    btn1.setPrefWidth(100);
	    btn2.setPrefWidth(100);
	    btn3.setPrefWidth(100);
	   
	    // Add button action
	    btn1.setOnMouseClicked(event -> {
	    	//change scene for add new customer 
	    	MenuFX.setScene(stage,MenuFX.sceneAddCustomer);
	    });
	    
	    // Delete button action
	    btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//Checking map data before deleting 
            		System.out.println("Before deleting");
            	for(Integer key: customerMap.keySet()){
          			System.out.println("ID:" + key +" "+ customerMap.get(key));
            	}
            	 
            	if(table.getSelectionModel().getSelectedItem() != null) {
            		Customer currentCustomer = table.getSelectionModel().getSelectedItem();
            		
            		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        	        alert.setTitle("Confirmation");
        	        alert.setHeaderText(null);
        	        alert.setContentText("Are you sure to delete this customer: "+ currentCustomer.getFirstName()+ " " + currentCustomer.getLastName() +"?");

        	        Optional<ButtonType> result = alert.showAndWait();
        	        if (result.get() == ButtonType.OK) {
        	        	tvObservableList.remove(currentCustomer);
              	    	customerMap.remove(currentCustomer.getCustomerId());
              	    	
              	    //Database delete
    			        try {
    			        	//Databases connection
    			    		Class.forName("com.mysql.jdbc.Driver");  
    			    		Connection con=DriverManager.getConnection(  
    			    		"jdbc:mysql://localhost:3306/videorental","root","");   //password root
    			    			
    			        	String query = "DELETE FROM customers "
    			        			+ "WHERE customerID=?";
    			        			
    			     
    					    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
    					    preparedStmt.setInt(1,currentCustomer.getCustomerId());
    					    
    					    preparedStmt.executeUpdate();
    					    con.close();
    			        	
    			        } catch (Exception err) {
    			        	System.out.println(err);
    			        }
              	    	
    			       
    			        
            	        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            	        alert2.setTitle("Information");
            	        alert2.setHeaderText(null);
            	        alert2.setContentText("Deleted customer successfully! ");

            	        Optional<ButtonType> result2 = alert2.showAndWait();
            	        if (result2.get() == ButtonType.OK) {
            	        	
            	        	System.out.println("Deleted customer successfully");
                  	    	
            	        } else {
            	        	
            	        }
              	    	
              	    	
              	    	
        	        } else {
        	        	System.out.println("Canceled deleteing customer");
        	        }
       	    	 
        	        
        	        
        	        //System.out.println("Deleted customer successfully");
        	        //Checking after map data deleting	 
        	        System.out.println("After deleting (include cancel action");
        	        for(Integer key: customerMap.keySet()){
        	        	System.out.println("ID:" + key +" "+ customerMap.get(key));
        	        }
     	    
            	} //end if
            }
	    });
	    
	    // close button action
	    btn3.setOnMouseClicked(event -> {
	    	stage.close();
	    	
	  
	    });
	    
	    // test button action for testing selected item
	    btn4.setOnMouseClicked(event -> {
	    	 //System.out.println(customerMap.get(101));
	         if(table.getSelectionModel().getSelectedItem() != null) { 
	    	 Customer customer1 = table.getSelectionModel().getSelectedItem();
	    	 System.out.println(customer1.getFirstName());   
	         }
	    });
	    hb.getChildren().addAll(btn1,btn2,btn3);
	  
	    
	   
        // Set TableView appearance
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(950);
        table.setPrefHeight(250);
   
        /*
        // Data set for TableView
        for(Integer key: customerMap.keySet()){
			//System.out.println("ID:" + key +" "+ customerMap.get(key));
			tvObservableList.add(customerMap.get(key));
			//data.add("ID:" + key +" "+ customerMap.get(key).toLine());
	    }
	    */
       
		
        //Set Data from database
        ResultSet resultSet = null;
        try {
        	//Databases connection
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/videorental","root","");   //password root
    		
    		Statement stmt=con.createStatement();  
        	resultSet=stmt.executeQuery("select * from customers");
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
        
        //ArrayList<Customer> data =  new ArrayList<>();
        try {
        while (resultSet.next()) {
        	Customer customer = new Customer();
        	customer.setCustomerId(resultSet.getInt("customerID"));
        	customer.setFirstName(resultSet.getString("firstName"));
        	customer.setLastName(resultSet.getString("lastName"));
        	customer.setEmail(resultSet.getString("email"));
        	customer.setPhone(resultSet.getString("phone"));
        	customer.setAddress (resultSet.getString("address"));
        	customer.setPostcode (resultSet.getString("postcode"));
        	customer.setDOB(LocalDate.parse(resultSet.getString("DOB")));
        	
            //data.add(customer);
        	customerMap.put(resultSet.getInt("customerID"), customer);
        	tvObservableList.add(customer);
        	
        	}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //ObservableList dbData = FXCollections.observableArrayList(data);
        //tvObservableList = FXCollections.observableArrayList(data);
        table.setItems(tvObservableList);
        //table.setItems(dbData);
 
        TableColumn<Customer,Integer> colId = new TableColumn<>("ID");
        colId.setPrefWidth(40);
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
        
     
        
        TableColumn<Customer,String> colFName = new TableColumn<>("First Name");
        colFName.setPrefWidth(80);
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
        //colLName.setMinWidth(100);
        colLName.setPrefWidth(80);
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
        
        TableColumn<Customer,String> colEmail = new TableColumn<>("email");
        colEmail.setPrefWidth(300);
        colEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(
            new EventHandler<CellEditEvent<Customer, String>>() {
                @Override
                public void handle(CellEditEvent<Customer, String> t) {
                    ((Customer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setLastName(t.getNewValue());
                }
            }
        );
        
        TableColumn<Customer,String> colPhone = new TableColumn<>("phone");
        colPhone.setPrefWidth(100);
        colPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(
            new EventHandler<CellEditEvent<Customer, String>>() {
                @Override
                public void handle(CellEditEvent<Customer, String> t) {
                    ((Customer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setLastName(t.getNewValue());
                }
            }
        );
        
        TableColumn<Customer,String> colAddress = new TableColumn<>("Address");
        colAddress.setPrefWidth(200);
        colAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setOnEditCommit(
            new EventHandler<CellEditEvent<Customer, String>>() {
                @Override
                public void handle(CellEditEvent<Customer, String> t) {
                    ((Customer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setLastName(t.getNewValue());
                }
            }
        );
        
        TableColumn<Customer,String> colPostcode = new TableColumn<>("Post code");
        colPostcode.setPrefWidth(40);
        colPostcode.setCellValueFactory(new PropertyValueFactory<Customer, String>("postcode"));
        colPostcode.setCellFactory(TextFieldTableCell.forTableColumn());
        colPostcode.setOnEditCommit(
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
        colDOB.setPrefWidth(50);
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
        
       
        
        //table.setEditable(true);
        table.getColumns().addAll(colId,colFName,colLName,colEmail,colPhone,colAddress,colPostcode,colDOB);
        root.getChildren().addAll(table);
        
	 
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label1, hb,table);
        root.getChildren().addAll(vbox);
  
	  } // End initCustomerForm
	
	public static void addCustomerFX(Stage stage, Map<Integer,Customer> customerMap, ObservableList tvObservableList) {

		GridPane grid = new GridPane();
		MenuFX.sceneAddCustomer = new Scene(grid);
	
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 30, 10));
		grid.setAlignment(Pos.TOP_CENTER); 
	
		// Title form
		Label labelTitle = new Label("Add New Customer");
		labelTitle.setFont(new Font("Haettenschweiler", 48)); //Arial
		labelTitle.setTextFill(Color.web("#0000FF"));
	 
		//Grid for text fields and labels of text fields
		GridPane gridFields = new GridPane();
		gridFields.setHgap(10);
		gridFields.setVgap(10);
		gridFields.setPadding(new Insets(10, 10, 10, 10));
		gridFields.setAlignment(Pos.TOP_CENTER);
		
				
		// Set Customer information fields
		String [] labelNames = {"First Name","Last Name","email","phone","Address","Postcode","DOB(dd/mm/yyyy)"};
		int fieldsLength= labelNames.length;
		Label [] labels  = new Label[fieldsLength];
		TextField []textFields = new TextField[fieldsLength];		
		
		for(int i=0;i<fieldsLength;i++) {
			labels[i]= new Label(labelNames[i]);
			textFields[i]= new TextField();
			textFields[i].setPrefWidth(400);
			gridFields.add(labels[i], 0, i);
			gridFields.add(textFields[i], 1, i);
		}
		
		
		
		Button btn1 = new Button("Apply");
		btn1.setOnMouseClicked(event -> {
			boolean empty=false;
			for(TextField s: textFields) {
				if(s.getText()=="") empty=true;
			}
			for(int i=0;i<fieldsLength;i++) {
				if(textFields[i].getText()=="") {
					labels[i].setText(labelNames[i]+"*");
					labels[i].setTextFill(Color.web("#FF0000"));
				}else {
				labels[i].setText(labelNames[i]);
				labels[i].setTextFill(Color.web("#000000"));
			}
			}
			if(empty==false) {
				
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				 
				 for(Label lbl: labels) {
			    		lbl.setTextFill(Color.web("#000000"));
				 }
				 
				 
				//Database add
			        try {
			        	//Databases connection
			    		Class.forName("com.mysql.jdbc.Driver");  
			    		Connection con=DriverManager.getConnection(  
			    		"jdbc:mysql://localhost:3306/videorental","root","");   //password root
			    			
			        	String query = "INSERT INTO customers "
			        			+ "(firstName,lastName,email,phone,address,postcode,DOB)"
			        			+  "values(?,?,?,?,?,?,?)";
			        	
					    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
					    preparedStmt.setString(1,textFields[0].getText() );
					    preparedStmt.setString(2, textFields[1].getText());
					    preparedStmt.setString(3,textFields[2].getText() );
					    preparedStmt.setString(4, textFields[3].getText());
					    preparedStmt.setString(5,textFields[4].getText() );
					    preparedStmt.setString(6, textFields[5].getText());
					    DateTimeFormatter dbformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					    LocalDate dbDOB = LocalDate.parse(textFields[6].getText(),formatter);
					    preparedStmt.setString(7,dbDOB.format(dbformatter));
					    
					    preparedStmt.executeUpdate();
					    
					    int last_inserted_id=0;
					    //ResultSet rs = preparedStmt.getGeneratedKeys();
					    Statement st = con.createStatement();
			            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() FROM customers");
					    if(rs.next())
			            {

			                last_inserted_id=rs.getInt(1);

			            }
			            
			            
			             Customer customer1 = new Customer(last_inserted_id,textFields[0].getText(),textFields[1].getText(),textFields[2].getText(),textFields[3].getText(),textFields[4].getText(),textFields[5].getText(),LocalDate.parse(textFields[6].getText(),formatter));
						 customerMap.put(last_inserted_id,customer1);
						 tvObservableList.add(customer1);
						 
					    
					    con.close();
			        	
			        } catch (Exception e) {
			        	System.out.println(e);
			        }
			     
			     for(TextField s: textFields) s.clear(); 
				 MenuFX.setScene(stage,MenuFX.sceneCustomer);	
				 System.out.println("Added customer successful");
				 
			}
			
 	    });

	    Button btn2 = new Button("Close"); //Button close then go back customer menu
	    btn2.setOnMouseClicked(event -> {
	    	for(TextField tf: textFields) {
	    		tf.clear();
			}
	    	for(Label lbl: labels) {
	    		lbl.setTextFill(Color.web("#000000"));
			}
	    	
	    	MenuFX.setScene(stage,MenuFX.sceneCustomer);
	    });
	 
	    //add buttons to hbox
	    HBox hb = new HBox();
	    hb.getChildren().addAll(btn1,btn2);
	    
	    //add objects to grid
	    grid.add(labelTitle, 1, 0);
	    grid.add(gridFields, 1, 1);
	    grid.add(hb, 1, 2);
	 
	}
	
}

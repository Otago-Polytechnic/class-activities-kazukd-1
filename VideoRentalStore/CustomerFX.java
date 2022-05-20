package VideoRentalStore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

public class CustomerFX{
	public static void initCustomerForm(Stage stage,Map<Integer,Customer> customerMap) {
		/** Object for storing customers data using HashMap */
		//Map<Integer,Customer> customerMap = new HashMap<>();
		
		// Table view
        final TableView<Customer> table = new TableView<>();
        final ObservableList<Customer> tvObservableList = FXCollections.observableArrayList();
        table.setEditable(true);
		
		AnchorPane root = new AnchorPane();
	    MenuFX.sceneCustomer = new Scene(root);
	   // sceneAddCustomer = new Scene(root);

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
              	    	System.out.println("Deleted customer successfully");
        	        } else {
        	        	System.out.println("Canceled deleteing customer");
        	        }
       	    	 
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
	    	
	    //	setScene(stage,sceneMain);
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
	    //AnchorPane.setTopAnchor(btn, 30.0);
	    //AnchorPane.setTopAnchor(lbl, 10.0);
	    //root.getChildren().addAll(btn,lbl);
	    
	    // Load customer data for test
	    Main.addCustomerTestData(customerMap);
	    
	    /*
		//test listView
	    final ListView listView = new ListView(data);
	    listView.setPrefWidth(1000);
	    Main.addCustomerTestData(customerMap);
	    for(Integer key: customerMap.keySet()){
			System.out.println("ID:" + key +" "+ customerMap.get(key));
			data.add("ID:" + key +" "+ customerMap.get(key).toLine());
	    }
        
       
        listView.setItems(data);
        
        */
	    
	   
        // Set TableView appearance
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(950);
        table.setPrefHeight(250);
        
        /*ScrollBar table1VerticalScrollBar = findScrollBar( table, Orientation.VERTICAL);
        ScrollBar scroll = new ScrollBar();
        scroll.setMin(0);
        scroll.setMax(400);
        scroll.setValue(50);
        */
        
        // Data set for TableView
        for(Integer key: customerMap.keySet()){
			//System.out.println("ID:" + key +" "+ customerMap.get(key));
			tvObservableList.add(customerMap.get(key));
			//data.add("ID:" + key +" "+ customerMap.get(key).toLine());
	    }
        table.setItems(tvObservableList);
  
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
        
        
        //System.out.println(customerMap.get(101));
        /*
        TableColumn<Customer, String> colLName = new TableColumn<>("Last Name");
        colLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
*/
        
        //table.setEditable(true);
        table.getColumns().addAll(colId,colFName,colLName,colEmail,colPhone,colAddress,colPostcode,colDOB);
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
		
		//gridFields.add(label1, 0, 0);
		//gridFields.add(textField1, 1, 0);
         /*
         Button btn = new Button();
         btn.setText("Submit");
         btn.setOnAction(new EventHandler() {
             @Override
             public void handle(Event arg0) {
                 System.out.println(textField.getText());
             }
         });
     
        bt1.setOnMouseClicked(event -> {
 	    	MenuFX.setScene(stage,MenuFX.sceneCustomer);
 	    });
 	 	*/
		
		
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
				 Customer customer1 = new Customer(Main.customerID,textFields[0].getText(),textFields[1].getText(),textFields[2].getText(),textFields[3].getText(),textFields[4].getText(),textFields[5].getText(),LocalDate.parse(textFields[6].getText(),formatter));
				 customerMap.put(Main.customerID,customer1);
				 tvObservableList.add(customer1);
				 Main.customerID++;
				 
				 for(TextField s: textFields) s.clear(); 
				 MenuFX.setScene(stage,MenuFX.sceneCustomer);	
				 System.out.println("Added customer successful");
				 
			}
			
 	    });

	    Button btn2 = new Button("Close"); //Button close then go back customer menu
	    btn2.setOnMouseClicked(event -> {
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

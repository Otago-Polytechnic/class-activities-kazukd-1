package VideoRentalStore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

/**
 *  This is class using manage video for UI(JavaFX)
 * 
 */
public class VideoFX {
	public static void initVideoForm(Stage stage,Map<Integer,rentalVideo> rentalVideoMap) {
		
		
		// Table view
        final TableView<rentalVideo> table = new TableView<rentalVideo>();
        final ObservableList<rentalVideo> tvObservableList2 = FXCollections.observableArrayList();
        table.setEditable(true);
		
		AnchorPane root = new AnchorPane();
	    MenuFX.sceneVideo = new Scene(root);
	   
	    // Add Video
        VideoFX.addVideoFX(stage, rentalVideoMap,tvObservableList2);
	    
	    HBox hb = new HBox();
	    Label label1 = new Label("Manage Video");
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
	    	//change scene for add new video 
	    	MenuFX.setScene(stage,MenuFX.sceneAddVideo);
	    });
	    
	    // Delete button action
	    btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//Checking map data before deleting 
            		System.out.println("Before deleting");
            	for(Integer key: rentalVideoMap.keySet()){
          			System.out.println("ID:" + key +" "+ rentalVideoMap.get(key));
            	}
            	 
            	if(table.getSelectionModel().getSelectedItem() != null) {
            		rentalVideo currentVideo = table.getSelectionModel().getSelectedItem();
            		
            		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        	        alert.setTitle("Confirmation");
        	        alert.setHeaderText(null);
        	        alert.setContentText("Are you sure to delete this title: "+ currentVideo.getTitle() +"?");

        	        Optional<ButtonType> result = alert.showAndWait();
        	        if (result.get() == ButtonType.OK) {
        	        	tvObservableList2.remove(currentVideo);
              	    	rentalVideoMap.remove(currentVideo.getRentalvideoId());
              	    	System.out.println("Deleted video successfully");
        	        } else {
        	        	System.out.println("Canceled deleteing video");
        	        }
       	    	 
        	        //Checking after map data deleting	 
        	        System.out.println("After deleting (include cancel action");
        	        for(Integer key: rentalVideoMap.keySet()){
        	        	System.out.println("ID:" + key +" "+ rentalVideoMap.get(key));
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
	    	 rentalVideo rentalvideo1 = table.getSelectionModel().getSelectedItem();
	    	 System.out.println(rentalvideo1.getTitle());   
	         }
	    });
	    hb.getChildren().addAll(btn1,btn2,btn3);
	
	    for(Integer key: rentalVideoMap.keySet()){
			System.out.println("ID:" + key +" "+ rentalVideoMap.get(key));
			
	    }
	    
 
	   
        // Set TableView appearance
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(950);
        table.setPrefHeight(250);
        
 
        // Data set for TableView
        for(Integer key: rentalVideoMap.keySet()){
			//System.out.println("ID:" + key +" "+ customerMap.get(key));
			tvObservableList2.add(rentalVideoMap.get(key));
			//data.add("ID:" + key +" "+ customerMap.get(key).toLine());
	    }
        table.setItems(tvObservableList2);
  
        TableColumn<rentalVideo,Integer> colId = new TableColumn<>("ID");
        colId.setPrefWidth(40);
        colId.setCellValueFactory(new PropertyValueFactory<rentalVideo, Integer>("rentalvideoId"));
        //colId.setCellFactory(TextFieldTableCell.forTableColumn());
        //colId.setCellFactory(col-> new IntegerEditingCell());
        //colId.setCellFactory(Integer.parseInt(TextFieldTableCell.forTableColumn()));
        colId.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, Integer>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, Integer> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setRentalvideoId(t.getNewValue());
                }
            }
        );
        
    
        
        TableColumn<rentalVideo,String> colTitle = new TableColumn<>("Title");
        colTitle.setPrefWidth(80);
        colTitle.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("title"));
        colTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        colTitle.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setTitle(t.getNewValue());
                }
            }
        );
        
        
        TableColumn<rentalVideo,String> colMedia = new TableColumn<>("Media");
        
        colMedia.setPrefWidth(80);
        colMedia.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("media"));
        colMedia.setCellFactory(TextFieldTableCell.forTableColumn());
        colMedia.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setMedia(t.getNewValue());
                }
            }
        );
        
       
        
   
        
        table.getColumns().addAll(colId,colTitle,colMedia);
        root.getChildren().addAll(table);
   
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label1, hb,table);
        root.getChildren().addAll(vbox);
     
        
    
        
	  } // end initVideoForm
	
	public static void addVideoFX(Stage stage, Map<Integer,rentalVideo> rentalVideoMap, ObservableList tvObservableList) {

		GridPane grid = new GridPane();
		MenuFX.sceneAddVideo = new Scene(grid);
	
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 30, 10));
		grid.setAlignment(Pos.TOP_CENTER); 
	
		// Title form
		Label labelTitle = new Label("Add New Video");
		labelTitle.setFont(new Font("Haettenschweiler", 48)); //Arial
		labelTitle.setTextFill(Color.web("#0000FF"));
	 
		//Grid for text fields and labels of text fields
		GridPane gridFields = new GridPane();
		gridFields.setHgap(10);
		gridFields.setVgap(10);
		gridFields.setPadding(new Insets(10, 10, 10, 10));
		gridFields.setAlignment(Pos.TOP_CENTER);
		
				
		// Set Customer information fields
		String [] labelNames = {"Title","Media"};
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
				
				 
				 rentalVideo rentalvideo1 = new rentalVideo(Main.rentalVideoId,textFields[0].getText(),textFields[1].getText());
				 rentalVideoMap.put(Main.rentalVideoId,rentalvideo1);
				 tvObservableList.add(rentalvideo1);
				 Main.rentalVideoId++;
				 
				 for(TextField s: textFields) s.clear(); 
				 MenuFX.setScene(stage,MenuFX.sceneVideo);	
				 System.out.println("Added video successful");
				 
			}
			
 	    });

	    Button btn2 = new Button("Close"); //Button close then go back customer menu
	    btn2.setOnMouseClicked(event -> {
	    	MenuFX.setScene(stage,MenuFX.sceneVideo);
	    });
	 
	    //add buttons to hbox
	    HBox hb = new HBox();
	    hb.getChildren().addAll(btn1,btn2);
	    
	    //add objects to grid
	    grid.add(labelTitle, 1, 0);
	    grid.add(gridFields, 1, 1);
	    grid.add(hb, 1, 2);
	 
	}

	public static void rentVideoForm(Stage stage, Map<Integer,rentalVideo> rentalVideoMap, Map<Integer,Customer> customerMap) {

		GridPane grid = new GridPane();
		MenuFX.sceneRental = new Scene(grid);
	
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 30, 10));
		grid.setAlignment(Pos.TOP_CENTER); 
	
		// Title form
		Label labelTitle = new Label("Rent Video");
		labelTitle.setFont(new Font("Haettenschweiler", 48)); //Arial
		labelTitle.setTextFill(Color.web("#0000FF"));
	 
		//Grid for text fields and labels of text fields
		GridPane gridFields = new GridPane();
		gridFields.setHgap(10);
		gridFields.setVgap(10);
		gridFields.setPadding(new Insets(10, 10, 10, 10));
		gridFields.setAlignment(Pos.TOP_CENTER);
		
				
		// Set Customer information fields
		String [] labelNames = {"Video call Number","Customer Membership Number"};
		int fieldsLength= labelNames.length;
		Label [] labels  = new Label[fieldsLength];
		TextField []textFields = new TextField[fieldsLength];
		Label [] lblMessage = new Label[fieldsLength];
		
		boolean [] errFlag= {false};
		
		for(int i=0;i<fieldsLength;i++) {
			labels[i]= new Label(labelNames[i]);
			textFields[i]= new TextField();
			textFields[i].setPrefWidth(100);
			lblMessage[i] = new Label("");
			gridFields.add(labels[i], 0, i*2);
			gridFields.add(textFields[i], 1, i*2);
			gridFields.add(lblMessage[i], 1, i*2+1);
		}
		
		textFields[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	int vkey = Integer.parseInt(textFields[0].getText());
                String msg="";
    			if(rentalVideoMap.containsKey(vkey)) {
    				msg = "Title:" + rentalVideoMap.get(vkey).getTitle();
    				lblMessage[0].setTextFill(Color.web("#000000"));
    			}else {
    				msg="Video not found";
    				lblMessage[0].setTextFill(Color.web("#FF0000"));
    				errFlag[0] = true;
    			}
    			lblMessage[0].setText(msg);
            }
        });
		
		textFields[1].setOnAction((ActionEvent)-> {
			int ckey = Integer.parseInt(textFields[1].getText());
            String msg="";
			if(customerMap.containsKey(ckey)) {
				msg = "Customer: " + customerMap.get(ckey).getFirstName() + " " + customerMap.get(ckey).getLastName();
				lblMessage[1].setTextFill(Color.web("#000000"));
			}else {
				msg="Customer not found";
				lblMessage[1].setTextFill(Color.web("#FF0000"));
				errFlag[0] = true;
			}
			lblMessage[1].setText(msg);
        });
		
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
			if(empty==false&&errFlag[0]==false) {
				
				
				int vkey = Integer.parseInt(textFields[0].getText()); //video id 
				int ckey = Integer.parseInt(textFields[1].getText());
				
				if(rentalVideoMap.get(vkey).get_isRented()) {
					lblMessage[0].setText("Unavailable for rental! Someone is renting this video.");
					labels[0].setTextFill(Color.web("#FF0000"));
					lblMessage[0].setTextFill(Color.web("#FF0000"));
					System.out.println("Unavailable for rental! Someone is renting this video.");
					System.out.println("Try again");
							
				}else {
					if(customerMap.containsKey(ckey)) {
						
								int countVideo=0;
								for(Integer key: rentalVideoMap.keySet()){
									if(rentalVideoMap.get(key).get_renterId()==ckey) {
										//if(countVideo==0) System.out.println("This customer is currently renting the following video(s)");
										countVideo++;
										//System.out.println("* "+ rentalVideoMap.get(key).getTitle());
									}
									
								}	
								
								if(countVideo==2) {
									Alert alert = new Alert(Alert.AlertType.WARNING);
				        	        alert.setTitle("Warning");
				        	        alert.setHeaderText(null);
				        	        alert.setContentText("Renting video is not available more than two videos.");

				        	        Optional<ButtonType> result = alert.showAndWait();
								
									System.out.println("Renting video is not available more than two videos.");
								}else {
									rentalVideoMap.get(vkey).rent(customerMap.get(ckey), LocalDate.now());
									System.out.println("Successful rented!");
									for(TextField s: textFields) s.clear(); 
									for(Label s: lblMessage) s.setText("");
									 
									Alert alert = new Alert(Alert.AlertType.INFORMATION);
				        	        alert.setTitle("Information");
				        	        alert.setHeaderText(null);
				        	        alert.setContentText("Rented video successfully! ");

				        	        Optional<ButtonType> result = alert.showAndWait();
				        	        if (result.get() == ButtonType.OK) {
				        	        	
				              	    	System.out.println("Rented video successfully");
				              	    	
				        	        } else {
				        	        	
				        	        }
																	 
								}
							}
				}
				 
			}
			
 	    });

	    Button btn2 = new Button("Close"); //Button close then go back customer menu
	    btn2.setOnMouseClicked(event -> {
	    	//MenuFX.setScene(stage,MenuFX.sceneMain);
	    	for(TextField tf: textFields) {
	    		tf.clear();
			}
	    	for(Label lbl: labels) {
	    		lbl.setTextFill(Color.web("#000000"));
			}
	    	for(Label lblmsg: lblMessage) {
	    		lblmsg.setText("");
	    		lblmsg.setTextFill(Color.web("#000000"));
			}
	    	
	    	stage.close();
	    });
	 
	    //add buttons to hbox
	    HBox hb = new HBox();
	    hb.getChildren().addAll(btn1,btn2);
	    
	    //add objects to grid
	    grid.add(labelTitle, 1, 0);
	    grid.add(gridFields, 1, 1);
	    grid.add(hb, 1, 2);
	 
	} // End rentVideoForm
	
	public static void returnVideoForm(Stage stage, Map<Integer,rentalVideo> rentalVideoMap, Map<Integer,Customer> customerMap) {

		GridPane grid = new GridPane();
		MenuFX.sceneReturn = new Scene(grid);
	
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 30, 10));
		grid.setAlignment(Pos.TOP_CENTER); 
	
		// Title form
		Label labelTitle = new Label("Return Video");
		labelTitle.setFont(new Font("Haettenschweiler", 48)); //Arial
		labelTitle.setTextFill(Color.web("#0000FF"));
	 
		//Grid for text fields and labels of text fields
		GridPane gridFields = new GridPane();
		gridFields.setHgap(10);
		gridFields.setVgap(10);
		gridFields.setPadding(new Insets(10, 10, 10, 10));
		gridFields.setAlignment(Pos.TOP_CENTER);
		
				
		// Set label and text fields
		String [] labelNames = {"Video call Number","Overdue fine"};
		int fieldsLength= labelNames.length;
		Label [] labels  = new Label[fieldsLength];
		TextField []textFields = new TextField[fieldsLength];
		Label [] lblMessage = new Label[fieldsLength];
		
		boolean [] errFlag= {false};
		
		for(int i=0;i<fieldsLength;i++) {
			labels[i]= new Label(labelNames[i]);
			textFields[i]= new TextField();
			textFields[i].setPrefWidth(100);
			lblMessage[i] = new Label("");
			gridFields.add(labels[i], 0, i*2);
			gridFields.add(textFields[i], 1, i*2);
			gridFields.add(lblMessage[i], 1, i*2+1);
		}
			
		//Video call Number
		textFields[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	int vkey = Integer.parseInt(textFields[0].getText());
                String msg="";
    			if(rentalVideoMap.containsKey(vkey)) {
    				msg = "Title:" + rentalVideoMap.get(vkey).getTitle();
    				lblMessage[0].setTextFill(Color.web("#000000"));
    				errFlag[0] = false;
    				if(!rentalVideoMap.get(vkey).get_isRented()) {
    					msg += " Not rented this video.";
    					//labels[0].setTextFill(Color.web("#FF0000"));
    					lblMessage[0].setTextFill(Color.web("#FF0000"));
    					System.out.println("Not rented this video.");
    					errFlag[0] = true;
    				}else {
    					//Calculate fine
    					textFields[1].setText("$"+Integer.toString(rentalVideoMap.get(vkey).calculateFine(LocalDate.now())));
				
    				}
    				
    			}else {
    				msg="Video not found";
    				lblMessage[0].setTextFill(Color.web("#FF0000"));
    				errFlag[0] = true;
    			}
    			lblMessage[0].setText(msg);
            }
        });
		
		// fine is not editable
		textFields[1].setEditable(false);	
		
		Button btn1 = new Button("Apply");
		btn1.setOnMouseClicked(event -> {
			boolean empty=false;
			for(TextField s: textFields) {
				if(s.getText()=="") empty=true;
			}
			
			//for(int i=0;i<fieldsLength;i++) {
				if(textFields[0].getText()=="") {
					labels[0].setText(labelNames[0]+"*");
					labels[0].setTextFill(Color.web("#FF0000"));
				}else {
				labels[0].setText(labelNames[0]);
				labels[0].setTextFill(Color.web("#000000"));
			}
			//}
			if(empty==false&&errFlag[0]==false) {
				
				
				int vkey = Integer.parseInt(textFields[0].getText()); //video id 
				
							
			
					rentalVideoMap.get(vkey).returnRental();
					for(TextField tf: textFields) {
			    		tf.clear();
					}
			    	for(Label lbl: labels) {
			    		lbl.setTextFill(Color.web("#000000"));
					}
			    	for(Label lblmsg: lblMessage) {
			    		lblmsg.setText("");
			    		lblmsg.setTextFill(Color.web("#000000"));
					}
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	        alert.setTitle("Information");
        	        alert.setHeaderText(null);
        	        alert.setContentText("Return video successfully! ");

        	        Optional<ButtonType> result = alert.showAndWait();
					System.out.println("Return video successfully");
				}
	 
			
 	    });

	    Button btn2 = new Button("Close"); //Button close then go back customer menu
	    btn2.setOnMouseClicked(event -> {
	    	//MenuFX.setScene(stage,MenuFX.sceneMain);
	    	for(TextField tf: textFields) {
	    		tf.clear();
			}
	    	for(Label lbl: labels) {
	    		lbl.setTextFill(Color.web("#000000"));
			}
	    	for(Label lblmsg: lblMessage) {
	    		lblmsg.setText("");
	    		lblmsg.setTextFill(Color.web("#000000"));
			}
	    	
	    	
	    	
	    	stage.close();
	    });
	 
	    //add buttons to hbox
	    HBox hb = new HBox();
	    hb.getChildren().addAll(btn1,btn2);
	    
	    //add objects to grid
	    grid.add(labelTitle, 1, 0);
	    grid.add(gridFields, 1, 1);
	    grid.add(hb, 1, 2);
	 
	} // End returnVideoForm
	
	
	public static void initShowRentingVideoListsForm(Stage stage,Map<Integer,rentalVideo> rentalVideoMap) {
		/** Object for storing customers data using HashMap */
		//Map<Integer,Customer> customerMap = new HashMap<>();
		
		// Table view
        final TableView<rentalVideo> table = new TableView<rentalVideo>();
        final ObservableList<rentalVideo> tvObservableList2 = FXCollections.observableArrayList();
        //table.setEditable(true);
		
		AnchorPane root = new AnchorPane();
	    MenuFX.sceneShowRenting = new Scene(root);
	   // sceneAddCustomer = new Scene(root);

	    // Add Video
        VideoFX.addVideoFX(stage, rentalVideoMap,tvObservableList2);
	    
	    HBox hb = new HBox();
	    Label label1 = new Label("Show Renting Lists");
	    label1.setFont(new Font("Verdana", 24)); //Arial
	    label1.setTextFill(Color.web("#0000FF"));
	    
	    Button btn1 = new Button("Add");
	    Button btn2 = new Button("Delete");
	    Button btn3 = new Button("Close");
	    Button btn4 = new Button("Show Renting Lists");
	    btn1.setPrefWidth(100);
	    btn2.setPrefWidth(100);
	    btn3.setPrefWidth(100);
	   
	    // Add button action
	    btn1.setOnMouseClicked(event -> {
	    	//change scene for add new customer 
	    	MenuFX.setScene(stage,MenuFX.sceneAddVideo);
	    });
	    
	    // Delete button action
	    btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//Checking map data before deleting 
            		System.out.println("Before deleting");
            	for(Integer key: rentalVideoMap.keySet()){
          			System.out.println("ID:" + key +" "+ rentalVideoMap.get(key));
            	}
            	 
            	if(table.getSelectionModel().getSelectedItem() != null) {
            		rentalVideo currentVideo = table.getSelectionModel().getSelectedItem();
            		
            		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        	        alert.setTitle("Confirmation");
        	        alert.setHeaderText(null);
        	        alert.setContentText("Are you sure to delete this title: "+ currentVideo.getTitle() +"?");

        	        Optional<ButtonType> result = alert.showAndWait();
        	        if (result.get() == ButtonType.OK) {
        	        	tvObservableList2.remove(currentVideo);
              	    	rentalVideoMap.remove(currentVideo.getRentalvideoId());
              	    	System.out.println("Deleted video successfully");
        	        } else {
        	        	System.out.println("Canceled deleteing video");
        	        }
       	    	 
        	        //Checking after map data deleting	 
        	        System.out.println("After deleting (include cancel action");
        	        for(Integer key: rentalVideoMap.keySet()){
        	        	System.out.println("ID:" + key +" "+ rentalVideoMap.get(key));
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
	    	table.getItems().clear();
	    	 for(Integer key: rentalVideoMap.keySet()){
	    		 if(rentalVideoMap.get(key).isRented == true) {
	 	         	System.out.println(rentalVideoMap.get(key).isRented);
	 	         	tvObservableList2.add(rentalVideoMap.get(key));
	 	 			System.out.println(rentalVideoMap.get(key));
	 	 			}
	    		 if(rentalVideoMap.get(key).isRented==true) System.out.println("true");
	    		 System.out.println("ID:" + key +" "+ rentalVideoMap.get(key) + rentalVideoMap.get(key).isRented);
	 			
	 	    }
	    	 
	    	 
	    	 
	    });
	    hb.getChildren().addAll(btn3);
	  
	    for(Integer key: rentalVideoMap.keySet()){
			System.out.println("ID:" + key +" "+ rentalVideoMap.get(key));
			
	    }
	  
	    
	   
        // Set TableView appearance
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(950);
        table.setPrefHeight(250);
        
        
        // Data set for TableView
        for(Integer key: rentalVideoMap.keySet()){
			//System.out.println("ID:" + key +" "+ customerMap.get(key));
			if(rentalVideoMap.get(key).isRented == true) {
        	System.out.println(rentalVideoMap.get(key).isRented);
        	tvObservableList2.add(rentalVideoMap.get(key));
			System.out.println(rentalVideoMap.get(key));
			}
			//data.add("ID:" + key +" "+ customerMap.get(key).toLine());
	    }
        table.setItems(tvObservableList2);
  
        TableColumn<rentalVideo,Integer> colId = new TableColumn<>("ID");
        colId.setPrefWidth(40);
        colId.setCellValueFactory(new PropertyValueFactory<rentalVideo, Integer>("rentalvideoId"));
        //colId.setCellFactory(TextFieldTableCell.forTableColumn());
        //colId.setCellFactory(col-> new IntegerEditingCell());
        //colId.setCellFactory(Integer.parseInt(TextFieldTableCell.forTableColumn()));
        
        
        TableColumn<rentalVideo,String> colTitle = new TableColumn<>("Title");
        colTitle.setPrefWidth(80);
        colTitle.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("title"));
        colTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        colTitle.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setTitle(t.getNewValue());
                }
            }
        );
        
        
        TableColumn<rentalVideo,String> colMedia = new TableColumn<>("Media");
        //colLName.setMinWidth(100);
        colMedia.setPrefWidth(80);
        colMedia.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("media"));
        colMedia.setCellFactory(TextFieldTableCell.forTableColumn());
        colMedia.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setMedia(t.getNewValue());
                }
            }
        );
        
        TableColumn<rentalVideo,String> colRenterName = new TableColumn<>("Renter");
        //colLName.setMinWidth(100);
        colRenterName.setPrefWidth(80);
        colRenterName.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("renterName"));
        colRenterName.setCellFactory(TextFieldTableCell.forTableColumn());
        colRenterName.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setMedia(t.getNewValue());
                }
            }
        );
        
       
        TableColumn<rentalVideo,LocalDate> colRentDate = new TableColumn<>("Rent Date");
        colRentDate.setPrefWidth(80);
        colRentDate.setCellValueFactory(new PropertyValueFactory<rentalVideo, LocalDate>("rentDate"));
   
        
        table.getColumns().addAll(colId,colTitle,colMedia,colRenterName,colRentDate);
        root.getChildren().addAll(table);
        

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label1, hb,table);
        root.getChildren().addAll(vbox);
   
        
    
	  } // End Show renting video lists

	public static void initShowOverdueVideoListsForm(Stage stage,Map<Integer,rentalVideo> rentalVideoMap) {
		
		// Table view
        final TableView<rentalVideo> table = new TableView<rentalVideo>();
        final ObservableList<rentalVideo> tvObservableList2 = FXCollections.observableArrayList();
        
		
		AnchorPane root = new AnchorPane();
	    MenuFX.sceneShowOverdue = new Scene(root);
	    
	    HBox hb = new HBox();
	    Label label1 = new Label("Show Overdue Lists");
	    label1.setFont(new Font("Verdana", 24)); //Arial
	    label1.setTextFill(Color.web("#0000FF"));
	    
	    Button btn3 = new Button("Close");
	    Button btn4 = new Button("Show Renting Lists");
	    
	    btn3.setPrefWidth(100);

	    // close button action
	    btn3.setOnMouseClicked(event -> {
	    	stage.close();
	    });
	    
	    // test button action for testing selected item
	    btn4.setOnMouseClicked(event -> {
	    	 //System.out.println(customerMap.get(101));
	    	table.getItems().clear();
	    	 for(Integer key: rentalVideoMap.keySet()){
	    		 if(rentalVideoMap.get(key).isOverdue(LocalDate.now()) == true) {
	 	         	System.out.println(rentalVideoMap.get(key).isRented);
	 	         	tvObservableList2.add(rentalVideoMap.get(key));
	 	 			System.out.println(rentalVideoMap.get(key));
	 	 			}
	    		 if(rentalVideoMap.get(key).isOverdue(LocalDate.now())==true) System.out.println("true");
	    		 System.out.println("ID:" + key +" "+ rentalVideoMap.get(key) + rentalVideoMap.get(key).isRented);
	 			
	 	    }
	    	 
	    	 
	    	 
	    });
	    hb.getChildren().addAll(btn3);
	
	    
	    for(Integer key: rentalVideoMap.keySet()){
			System.out.println("ID:" + key +" "+ rentalVideoMap.get(key));
			
	    }
	
	    
	   
        // Set TableView appearance
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(950);
        table.setPrefHeight(250);
  
        // Data set for TableView
        for(Integer key: rentalVideoMap.keySet()){
			
			if(rentalVideoMap.get(key).isOverdue(LocalDate.now()) == true) {
        	System.out.println(rentalVideoMap.get(key).getRentDate());
        	tvObservableList2.add(rentalVideoMap.get(key));
			System.out.println("Overdue:" + rentalVideoMap.get(key));
			}
			
	    }
        table.setItems(tvObservableList2);
 
        TableColumn<rentalVideo,Integer> colId = new TableColumn<>("ID");
        colId.setPrefWidth(40);
        colId.setCellValueFactory(new PropertyValueFactory<rentalVideo, Integer>("rentalvideoId"));
        //colId.setCellFactory(TextFieldTableCell.forTableColumn());
        //colId.setCellFactory(col-> new IntegerEditingCell());
        //colId.setCellFactory(Integer.parseInt(TextFieldTableCell.forTableColumn()));
        colId.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, Integer>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, Integer> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setRentalvideoId(t.getNewValue());
                }
            }
        );
        
      
     
        
        TableColumn<rentalVideo,String> colTitle = new TableColumn<>("Title");
        colTitle.setPrefWidth(80);
        colTitle.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("title"));
        colTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        colTitle.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setTitle(t.getNewValue());
                }
            }
        );
        
        
        TableColumn<rentalVideo,String> colMedia = new TableColumn<>("Media");
        //colLName.setMinWidth(100);
        colMedia.setPrefWidth(80);
        colMedia.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("media"));
        colMedia.setCellFactory(TextFieldTableCell.forTableColumn());
        colMedia.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setMedia(t.getNewValue());
                }
            }
        );
        
        TableColumn<rentalVideo,String> colRenterName = new TableColumn<>("Renter");
        //colLName.setMinWidth(100);
        colRenterName.setPrefWidth(80);
        colRenterName.setCellValueFactory(new PropertyValueFactory<rentalVideo, String>("renterName"));
        colRenterName.setCellFactory(TextFieldTableCell.forTableColumn());
        colRenterName.setOnEditCommit(
            new EventHandler<CellEditEvent<rentalVideo, String>>() {
                @Override
                public void handle(CellEditEvent<rentalVideo, String> t) {
                    ((rentalVideo) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setMedia(t.getNewValue());
                }
            }
        );
        TableColumn<rentalVideo,LocalDate> colRentDate = new TableColumn<>("Rent Date");
        colRentDate.setPrefWidth(80);
        colRentDate.setCellValueFactory(new PropertyValueFactory<rentalVideo, LocalDate>("rentDate"));
             
        table.getColumns().addAll(colId,colTitle,colMedia,colRenterName,colRentDate);
        root.getChildren().addAll(table);
        
	   
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label1, hb,table);
        root.getChildren().addAll(vbox);
       
    
	  } // End Show overdue video lists
	
	
	
}

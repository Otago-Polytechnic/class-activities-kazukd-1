package VideoRentalStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *  This is class using saving and reading data by file.
 *  Includes UI (JavaFX)
 *  	File full paths
 *  		 C:\\TMA3_Project_Q3_Sol\\video.txt for video data
 *   
 */
public class FileFX {
	public static void fileForm(Stage stage, Map<Integer,rentalVideo> rentalVideoMap, Map<Integer,Customer> customerMap) {

		GridPane grid = new GridPane();
		MenuFX.sceneFile = new Scene(grid);
	
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 30, 10));
		grid.setAlignment(Pos.TOP_CENTER); 
	
		// Title form
		Label labelTitle = new Label("File Management");
		labelTitle.setFont(new Font("Haettenschweiler", 48)); //Change from Arial
		labelTitle.setTextFill(Color.web("#0000FF"));
	 
		//Grid for text fields and labels of text fields
		GridPane gridFields = new GridPane();
		gridFields.setHgap(10);
		gridFields.setVgap(10);
		gridFields.setPadding(new Insets(10, 10, 10, 10));
		gridFields.setAlignment(Pos.TOP_CENTER);
		
				
		// Set Menu
		String [] labelNames = {"Video data","Customer Data"};
		int fieldsLength= labelNames.length;
		Label [] labels  = new Label[fieldsLength];
		Button [] btnRead = new Button[fieldsLength];
		Button [] btnSave = new Button[fieldsLength];
		TextField []textFields = new TextField[fieldsLength];
		Label [] lblMessage = new Label[fieldsLength];
		
		boolean [] errFlag= {false};
		
		for(int i=0;i<fieldsLength;i++) {
			labels[i]= new Label(labelNames[i]);
			btnRead[i]= new Button("Read");
			btnSave[i]= new Button("Save");
			textFields[i]= new TextField();
			textFields[i].setPrefWidth(100);
			lblMessage[i] = new Label("");
			gridFields.add(labels[i], 0, i*2);
			gridFields.add(btnRead[i], 1, i*2);
			gridFields.add(btnSave[i], 2, i*2);
			gridFields.add(lblMessage[i], 1, i*2+1);
		}
		
		
		//Button for reading video data from file 
		btnRead[0].setOnMouseClicked(event -> {
			readRentalVideosFX(rentalVideoMap,customerMap);
		});
		
		//Button for saving video data into file 
		btnSave[0].setOnMouseClicked(event -> {
			saveRentalVideosFX(rentalVideoMap);
		});
		
		//Button for reading customer data from file 
		btnRead[1].setOnMouseClicked(event -> {
			readCustomersFX(customerMap);
		});
				
		//Button for saving customer data into file 
		btnSave[1].setOnMouseClicked(event -> {
			saveCustomersFX(customerMap);
		});		
		
		
		//Button for close this window then go back menu
	    Button btn2 = new Button("Close"); 
	    btn2.setOnMouseClicked(event -> {
	    	
	    	//clear message and change color default
	    	for(Label lblmsg: lblMessage) {
	    		lblmsg.setText("");
	    		lblmsg.setTextFill(Color.web("#000000"));
			}
	    	
	    	stage.close();
	    });
	 
	    //add buttons to hbox
	    HBox hb = new HBox();
	    hb.getChildren().addAll(btn2);
	    
	    //add objects to grid
	    grid.add(labelTitle, 1, 0);
	    grid.add(gridFields, 1, 1);
	    grid.add(hb, 1, 2);
	 
	} // End rentVideoForm
	
	/**
	 * This method is used to read video data on the file.
	 * @param rentalVideoMap the video map is stored video data
	 * @param customerMap the customer map is stored customer data
	 * @return Nothing
	 */
	public static void readRentalVideosFX(Map<Integer, rentalVideo> rentalVideoMap, Map<Integer, Customer> customerMap) {
		// Set date format for New Zealand /
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String msg="The current video data will be replaced by a file.\n";
		msg += "Are you sure to continue to read video data file?";
		
		// Dialog for Checking confirmation for replace system data by file. 
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	rentalVideoMap.clear();
    		
			try{
				BufferedReader file = new BufferedReader(new FileReader("C:\\TMA3_Project_Q3_Sol\\video.txt"));
				String line = file.readLine();

				do {
					line = file.readLine();
				
					if(line == null) break; 
			
				String [] tokens = line.split(";");
			
				int videoNumber = Integer.parseInt(tokens[0]); //video call number
				//System.out.println(videoNumber);
				
				Customer customer1;
				if(tokens[4].equals("null")) customer1 = null;
				else customer1 = customerMap.get(Integer.parseInt(tokens[4]));
				//System.out.println(customer1);
				LocalDate rentDate;
				if(tokens[5].equals("null")) rentDate = null;
				else rentDate = LocalDate.parse(tokens[5],formatter);
				
				rentalVideo rentalvideo1 = new rentalVideo(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Boolean.parseBoolean(tokens[3]),customer1,rentDate);
				rentalVideoMap.put(videoNumber,rentalvideo1);	
				} while(true);
				file.close();
			
				//set next new video call number
				Main.rentalVideoId = 0;
				for(Integer key: rentalVideoMap.keySet()){
					if(key > Main.rentalVideoId) Main.rentalVideoId = key;
				}
				Main.rentalVideoId++;
			    //System.out.println(Main.rentalVideoId++);
			    
			    //Dialog for successful reading file
			    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		        alert2.setTitle("Information");
		        alert2.setHeaderText(null);
		        alert2.setContentText("Read video data successfully.");

		        Optional<ButtonType> result2 = alert2.showAndWait();
			    System.out.println("Read video data successfully");
			
			}catch(Exception e){
				System.out.println("File cannot be opened");
			}	
  	    	
        } else {
        	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
	        alert2.setTitle("Information");
	        alert2.setHeaderText(null);
	        alert2.setContentText("Reading video data has canceled.");

	        Optional<ButtonType> result2 = alert2.showAndWait();
	        if (result2.get() == ButtonType.OK) {
        	
        	System.out.println("Canceled reading video data");
	        }
        }
	
	} //end readRentalVideosFX
	
	/**
	 * This method is used to save video data on the file.
	 * @param rentalVideoMap the video map is stored video data
	 * @return Nothing
	 */
	public static void saveRentalVideosFX(Map<Integer, rentalVideo> rentalVideoMap) {
		
		// checking file exists
		boolean fileExists;
		File filename = new File("C:\\TMA3_Project_Q3_Sol\\video.txt");
		fileExists = filename.exists();
		boolean cancel=false;
		if(fileExists) {
			System.out.println("File already exists.");
			String msg="The current video data file already exists.\n";
			msg += "Are you sure to overwrite video data file?";
			
			// Dialog for Checking confirmation for overwrite data into file. 
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Confirmation");
	        alert.setHeaderText(null);
	        alert.setContentText(msg);

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == ButtonType.OK) {
	        	cancel = false;
	        }else {
	        	cancel = true;
	        	//Dialog for canceling information
	        	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		        alert2.setTitle("Information");
		        alert2.setHeaderText(null);
		        alert2.setContentText("Canceled to save video data on the file.");

		        Optional<ButtonType> result2 = alert2.showAndWait();
	        	System.out.println("Canceled to save video data on the file");
	        }
		
	        //Save video data into file
	        if(cancel == false) {
			try{
    			FileWriter file = new FileWriter("C:\\TMA3_Project_Q3_Sol\\video.txt");
    			file.write("ID;title;media;isRented;renter;rentDate\n");
    			for(Integer key: rentalVideoMap.keySet()){
    				file.write(key+";"+ rentalVideoMap.get(key).toLine());
    			}
		
    			file.close();
    			//Dialog for successful saving file
			    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		        alert2.setTitle("Information");
		        alert2.setHeaderText(null);
		        alert2.setContentText("Saved video data successfully.");

		        Optional<ButtonType> result2 = alert2.showAndWait();
			    System.out.println("Saved video data successfully");
    			
    		}catch(Exception e){
    			System.out.println("Error: File cannot be saved!");
    		}
	        } //end if cancel==true
		} //end if file exists
	}//end saveRentalVideosFX

	/**
	 * This method is used to read customer data on the file.
	 * @param customerMap the customer map is stored customer data
	 * @return Nothing
	 */
	public static void readCustomersFX(Map<Integer, Customer>	customerMap) {
		// Set date format for New Zealand /
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String msg="The current customer data will be replaced by a file.\n";
		msg += "Are you sure to continue to read customer data file?";
		
		// Dialog for Checking confirmation for replace system data by file. 
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	customerMap.clear();
    		
        	try{
				BufferedReader file = new BufferedReader(new FileReader("C:\\TMA3_Project_Q3_Sol\\customer.txt"));
				String line = file.readLine();

				do {
					line = file.readLine();
					
					if(line == null) break; 
				
				String [] tokens = line.split(";");
				
				int customerNumber = Integer.parseInt(tokens[0]); //customer membership number
				
				LocalDate DOB;
				if(tokens[7].equals("null")) DOB = null;
				else DOB = LocalDate.parse(tokens[7],formatter);
				
				Customer customer1 = new Customer(customerNumber,tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],DOB);
				customerMap.put(customerNumber,customer1);	
				} while(true);
				file.close();
				
				//set next new customerID
				Main.customerID = 0;
				for(Integer key: customerMap.keySet()){
					if(key > Main.customerID) Main.customerID = key;
				}
				Main.customerID++;
			    
			    //Dialog for successful reading file
			    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		        alert2.setTitle("Information");
		        alert2.setHeaderText(null);
		        alert2.setContentText("Read customer data successfully.");

		        Optional<ButtonType> result2 = alert2.showAndWait();
			    System.out.println("Read customer data successfully");
			
			}catch(Exception e){
				System.out.println("File cannot be opened");
			}	
  	    	
        } else {
        	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
	        alert2.setTitle("Information");
	        alert2.setHeaderText(null);
	        alert2.setContentText("Reading customer data has canceled.");

	        Optional<ButtonType> result2 = alert2.showAndWait();
	        if (result2.get() == ButtonType.OK) {
        	
        	System.out.println("Canceled reading customer data");
	        }
        }
	
	} //end readCustomersFX
	
	/**
	 * This method is used to save customer data on the file.
	 * @param customerMap the video map is stored customer data
	 * @return Nothing
	 */
	public static void saveCustomersFX(Map<Integer, Customer>	customerMap) {
		
		// checking file exists
		boolean fileExists;
		File filename = new File("C:\\TMA3_Project_Q3_Sol\\customer.txt");
		fileExists = filename.exists();
		boolean cancel=false;
		if(fileExists) {
			System.out.println("File already exists.");
			String msg="The current customer data file already exists.\n";
			msg += "Are you sure to overwrite customer data file?";
			
			// Dialog for Checking confirmation for overwrite data into file. 
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Confirmation");
	        alert.setHeaderText(null);
	        alert.setContentText(msg);

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == ButtonType.OK) {
	        	cancel = false;
	        }else {
	        	cancel = true;
	        	//Dialog for canceling information
	        	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		        alert2.setTitle("Information");
		        alert2.setHeaderText(null);
		        alert2.setContentText("Canceled to save customer data on the file.");

		        Optional<ButtonType> result2 = alert2.showAndWait();
	        	System.out.println("Canceled to save customer data on the file");
	        }
		
	        //Save customer data into file
	        if(cancel == false) {
	        	try{
	    			FileWriter file = new FileWriter("C:\\TMA3_Project_Q3_Sol\\customer.txt");
	    			file.write("ID;firstName;lastName;email;phone;address;postcode;DOB\n");
	    			for(Integer key: customerMap.keySet()){
	    				file.write(key+";"+ customerMap.get(key).toLine());
	    			}
			
	    			file.close();
	    			
    			//Dialog for successful saving file
			    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		        alert2.setTitle("Information");
		        alert2.setHeaderText(null);
		        alert2.setContentText("Saved customer data successfully.");

		        Optional<ButtonType> result2 = alert2.showAndWait();
		        System.out.println("Saved customer data successfully");
    			
    		}catch(Exception e){
    			System.out.println("Error: File cannot be saved!");
    		}
	        } //end if cancel==true
		} //end if file exists
	}//end saveCustomersFX	
	
}

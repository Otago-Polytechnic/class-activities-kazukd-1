package VideoRentalStore;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CustomerFX{
	
	public static void addCustomerFX(Stage stage, TableView<Customer>  table) {

		GridPane grid = new GridPane();
		MenuFX.sceneAddCustomer = new Scene(grid);
	
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 30, 10));
		grid.setAlignment(Pos.TOP_CENTER); 
	
		// Title form
		Label label = new Label("Add New Customer");
		label.setFont(new Font("Haettenschweiler", 48)); //Arial
		label.setTextFill(Color.web("#0000FF"));
	 
		//Grid for text fields
		GridPane gridFields = new GridPane();
		gridFields.setHgap(10);
		gridFields.setVgap(10);
		gridFields.setPadding(new Insets(10, 10, 10, 10));
		gridFields.setAlignment(Pos.TOP_CENTER);
		
		
		// Set Customer information fields
		Label label1 = new Label("First Name");
		TextField textField1 = new TextField();
		
		gridFields.add(label1, 0, 0);
		gridFields.add(textField1, 1, 0);
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
			System.out.println(textField1.getText());
 	    });

	    Button btn2 = new Button("Close"); //Button close then go back customer menu
	    btn2.setOnMouseClicked(event -> {
	    	MenuFX.setScene(stage,MenuFX.sceneCustomer);
	    });
	 
	    //add buttons to hbox
	    HBox hb = new HBox();
	    hb.getChildren().addAll(btn1,btn2);
	    
	    //add objects to grid
	    grid.add(label, 1, 0);
	    grid.add(gridFields, 1, 1);
	    grid.add(hb, 1, 2);
	 
	}
	
}

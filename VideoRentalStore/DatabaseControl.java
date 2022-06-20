package VideoRentalStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatabaseControl {
	static Connection con=null;
	
	//public Connection openDatabase() {
	public void openDatabase() {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/videorental","root","");   //password root
			
		}catch(Exception e)
		{ System.out.println(e);}  
	
		//return con;
	}
		
	public void closeDatabase() {
		try {
			con.close();
		}catch(Exception e)
		{ System.out.println(e);}
		
	}
	
	public String[] getCustomers() {
		String customers[]=null;
		
		openDatabase();
		
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
        	resultSet=stmt.executeQuery("select * from customers");
        	
        	// get customers number and set array
            resultSet.last();
            int number_of_row = resultSet.getRow();
            resultSet.beforeFirst();
            customers = new String[number_of_row];
            System.out.println("number of row" + number_of_row);
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
        
        
        
        //ArrayList<Customer> data =  new ArrayList<>();
        try {
        	int i=0;
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
        	System.out.println(customer);
        	customers[i++]=customer.toSocket();
        	
            //data.add(customer);
        	//customerMap.put(resultSet.getInt("customerID"), customer);
        	//tvObservableList.add(customer);
        	
        	}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabase();
        return customers;
	}
	
	public long addCustomer(String tokens[]) {
		long customerID=0;
		boolean result=false;
		openDatabase();
	
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
		String query = "INSERT INTO customers "
    			+ "(firstName,lastName,email,phone,address,postcode,DOB)"
    			+  "values(?,?,?,?,?,?,?)";
    	
	    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
	    preparedStmt.setString(1,tokens[1] ); //firstname
	    preparedStmt.setString(2,tokens[2]); //lastname
	    preparedStmt.setString(3,tokens[3]); //email
	    preparedStmt.setString(4,tokens[4]); //phone
	    preparedStmt.setString(5,tokens[5]); //address
	    preparedStmt.setString(6,tokens[6]); //postcode
	    
	    //data format change
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //New Zealand
		LocalDate targetDate = LocalDate.parse(tokens[7], formatter);
		System.out.println(targetDate);  // 2012-05-01
		System.out.println(targetDate.format(formatter));  // 
		DateTimeFormatter dbformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //for database
		System.out.println(targetDate.format(dbformatter));
	    preparedStmt.setString(7,targetDate.format(dbformatter)); //DOB
	    preparedStmt.executeUpdate();
	    
	    long last_inserted_id=0;
	    //ResultSet rs = preparedStmt.getGeneratedKeys();
	    Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() FROM customers");
	    if(rs.next())
        {

            last_inserted_id=rs.getInt(1);

        }
	    customerID= last_inserted_id;
        } catch (Exception e) {
        	System.out.println(e);
        }
		
		return  customerID;
	}
	
	public void updateCustomer(String tokens[]) {
		
		
		System.out.println(tokens[1]);
		System.out.println(tokens[2]);
		System.out.println(tokens[8]);
		
		openDatabase();
		//System.out.println(customer);
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
    		String query = "UPDATE customers "
        			+ " SET firstName = ?  , lastName = ? , email = ?, phone = ?,"
    				+ " address = ? , postcode = ?, DOB= ?  "
        			+ " WHERE customerID=?";
        	System.out.println(query);
    		//System.out.println(customer.getFirstName());
    		//System.out.println(customer.getLastName());
    		//System.out.println(customer.getCustomerId());
		    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		    //preparedStmt.setString(1,customer.getFirstName() );
		    //preparedStmt.setString(2,customer.getLastName());
		    //preparedStmt.setInt(3,customer.getCustomerId());
		    preparedStmt.setString(1,tokens[2] ); //firstname
		    preparedStmt.setString(2,tokens[3]); //lastname
		    preparedStmt.setString(3,tokens[4]); //email
		    preparedStmt.setString(4,tokens[5]); //phone
		    preparedStmt.setString(5,tokens[6]); //address
		    preparedStmt.setString(6,tokens[7]); //postcode
		    
		    //data format change
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //New Zealand
			LocalDate targetDate = LocalDate.parse(tokens[8], formatter);
			System.out.println(targetDate);  // 2012-05-01
			System.out.println(targetDate.format(formatter));  // 
			DateTimeFormatter dbformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //for database
			System.out.println(targetDate.format(dbformatter));
		    preparedStmt.setString(7,targetDate.format(dbformatter)); //DOB
		    preparedStmt.setInt(8,Integer.parseInt(tokens[1]));
		    
		    System.out.println("update done");
		    preparedStmt.executeUpdate();
        	
        	// get customers number and set array
           
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
        
        
        
       
        closeDatabase();
       
	}
	
	public void deleteCustomer(String tokens[]) {
		
		openDatabase();
	
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
    		String query = "DELETE FROM customers "
        			+ "WHERE customerID=?";
       
        	System.out.println(query);
        	PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		
		    preparedStmt.setInt(1,Integer.parseInt(tokens[1]));
		    
		    System.out.println("delete done");
		    preparedStmt.executeUpdate();
      	
        } catch (Exception e) {
        	System.out.println(e);
        }

        closeDatabase();
		
	}
	
	public long checkExistCustomer(String tokens[]) {
		long customerID=0;
		boolean result=false;
		openDatabase();
		
		//Set Data from database
	    ResultSet resultSet = null;
	    try {
	    	
			Statement stmt=con.createStatement();  
			String query = "SELECT * from customers WHERE customerID = ?";
	    	System.out.println(query);
	    	System.out.println(tokens[1]);
	    	
	    	
		    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		    preparedStmt.setInt(1,Integer.parseInt(tokens[1]));
		    
		    //preparedStmt.executeUpdate();
		    //resultSet = stmt.getResultSet();
		    resultSet = preparedStmt.executeQuery();
		
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    
	    
	    
	    //ArrayList<Customer> data =  new ArrayList<>();
	    try {
	    	//System.out.println("try in database control");
	    	//if(resultSet != null) {
	    	if(resultSet.next()) {	
	    	
	    		customerID=(long)(resultSet.getInt("customerID"));
	    	System.out.println(customerID);
	    	}
	    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    closeDatabase();
	    System.out.println(customerID);
	    return customerID;
	} //end exist customer
	
	public String[] getVideos() {
		String rentalvideos[]=null;
		
		openDatabase();
		
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
        	resultSet=stmt.executeQuery("select * from rentalvideos");
        	
        	// get customers number and set array
            resultSet.last();
            int number_of_row = resultSet.getRow();
            resultSet.beforeFirst();
            rentalvideos = new String[number_of_row];
            System.out.println("number of row" + number_of_row);
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
   
       try {
        	int i=0;
        while (resultSet.next()) {
        	rentalVideo rentalvideo = new rentalVideo();
        	rentalvideo.setRentalvideoId(resultSet.getInt("rentalVideoID"));
        	rentalvideo.setTitle(resultSet.getString("title"));
        	rentalvideo.setMedia(resultSet.getString("media"));
        	
        	System.out.println(rentalvideo);
        	rentalvideos[i++]=rentalvideo.toSocket();
        	
          
        	}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabase();
        return rentalvideos;
	} //end get videos
	
	public String[] getRentingVideos() {
		String rentalvideos[]=null;
		
		openDatabase();
		
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
    		String query = "SELECT rentalVideoID, title, media,CONCAT (customers.customerID,\": \",firstName,\" \",lastName) as Renter ,rentDate FROM rentalvideos "
              		+ "JOIN customers ON rentalvideos.customerID = customers.customerID "
              		+ "WHERE isRented = TRUE";
              		
              
            resultSet = con.createStatement().executeQuery(query);
        	
        	// get customers number and set array
            resultSet.last();
            int number_of_row = resultSet.getRow();
            resultSet.beforeFirst();
            rentalvideos = new String[number_of_row];
            System.out.println("number of row" + number_of_row);
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
   
       try {
        	int i=0;
        while (resultSet.next()) {
        	rentalVideo rentalvideo = new rentalVideo();
        	rentalvideo.setRentalvideoId(resultSet.getInt("rentalVideoID"));
        	rentalvideo.setTitle(resultSet.getString("title"));
        	rentalvideo.setMedia(resultSet.getString("media"));
        	rentalvideo.setIsRented(true);
        	LocalDate targetDate = LocalDate.parse(resultSet.getString("rentDate"));
        		rentalvideo.setRentDate(targetDate);
        	System.out.println(resultSet.getString("Renter"));
        	rentalvideo.setRenterName(resultSet.getString("Renter"));
        	System.out.println(rentalvideo);
        	rentalvideos[i++]=rentalvideo.toSocket();
        	
          
        	}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabase();
        return rentalvideos;
	} //end get rented videos
	
	
	public String[] getOverdueVideos() {
		String rentalvideos[]=null;
		
		openDatabase();
		
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
    		   String query = "SELECT rentalVideoID, title, media,CONCAT (customers.customerID,\": \",firstName,\" \",lastName) as Renter ,rentDate FROM rentalvideos "
    	          		+ "JOIN customers ON rentalvideos.customerID = customers.customerID "
    	          		+ "WHERE datediff(NOW(),rentalvideos.rentDate)>5";
              		
              
            resultSet = con.createStatement().executeQuery(query);
        	
        	// get customers number and set array
            resultSet.last();
            int number_of_row = resultSet.getRow();
            resultSet.beforeFirst();
            rentalvideos = new String[number_of_row];
            System.out.println("number of row" + number_of_row);
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
   
       try {
        	int i=0;
        while (resultSet.next()) {
        	rentalVideo rentalvideo = new rentalVideo();
        	rentalvideo.setRentalvideoId(resultSet.getInt("rentalVideoID"));
        	rentalvideo.setTitle(resultSet.getString("title"));
        	rentalvideo.setMedia(resultSet.getString("media"));
        	rentalvideo.setIsRented(true);
        	LocalDate targetDate = LocalDate.parse(resultSet.getString("rentDate"));
        		rentalvideo.setRentDate(targetDate);
        	System.out.println(resultSet.getString("Renter"));
        	rentalvideo.setRenterName(resultSet.getString("Renter"));
        	System.out.println(rentalvideo);
        	rentalvideos[i++]=rentalvideo.toSocket();
        	
          
        	}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDatabase();
        return rentalvideos;
	} //end get overdue videos
	public long addVideo(String tokens[]) {
		long videoID=0;
		boolean result=false;
		openDatabase();
	
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
		String query = "INSERT INTO rentalvideos "
    			+ "(title,media)"
    			+  "values(?,?)";
    	
	    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
	    preparedStmt.setString(1,tokens[1] ); //title
	    preparedStmt.setString(2,tokens[2]); //media
	    
	    preparedStmt.executeUpdate();
	    
	    long last_inserted_id=0;
	    //ResultSet rs = preparedStmt.getGeneratedKeys();
	    Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID() FROM rentalvideos");
	    if(rs.next())
        {

            last_inserted_id=rs.getInt(1);

        }
	    videoID= last_inserted_id;
        } catch (Exception e) {
        	System.out.println(e);
        }
		
		return  videoID;
	} // end add video
	
	public void updateVideo(String tokens[]) {
		
		
		System.out.println(tokens[1]);
		System.out.println(tokens[2]);
		
		
		openDatabase();
		//System.out.println(customer);
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
    		String query = "UPDATE rentalvideos "
        			+ " SET title = ?  , media = ?" 
        			+ " WHERE rentalVideoID=?";
        	System.out.println(query);
    		//System.out.println(customer.getFirstName());
    		//System.out.println(customer.getLastName());
    		//System.out.println(customer.getCustomerId());
		    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		    //preparedStmt.setString(1,customer.getFirstName() );
		    //preparedStmt.setString(2,customer.getLastName());
		    //preparedStmt.setInt(3,customer.getCustomerId());
		    preparedStmt.setString(1,tokens[2] ); //title
		    preparedStmt.setString(2,tokens[3]); //media
		    preparedStmt.setInt(3,Integer.parseInt(tokens[1]));
		    
		    System.out.println("update done");
		    preparedStmt.executeUpdate();
        	
        	// get customers number and set array
           
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
        
        
        
       
        closeDatabase();
       
	} //end video update
	
	public void deleteVideo(String tokens[]) {
		
		openDatabase();
	
		//Set Data from database
        ResultSet resultSet = null;
        try {
        	
    		Statement stmt=con.createStatement();  
    		String query = "DELETE FROM rentalvideos "
        			+ "WHERE rentalVideoID=?";
       
        	System.out.println(query);
        	PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		
		    preparedStmt.setInt(1,Integer.parseInt(tokens[1]));
		    
		    System.out.println("delete done");
		    preparedStmt.executeUpdate();
      	
        } catch (Exception e) {
        	System.out.println(e);
        }

        closeDatabase();
		
	} // end delete video
	
	
	public long checkExistVideo(String tokens[]) {
		long videoID=0;
		boolean result=false;
		openDatabase();
		
		//Set Data from database
	    ResultSet resultSet = null;
	    try {
	    	
			Statement stmt=con.createStatement();  
			String query = "SELECT * from rentalvideos WHERE rentalVideoID = ?";
	    	System.out.println(query);
	    	System.out.println(tokens[1]);
	    	
	    	
		    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		    preparedStmt.setInt(1,Integer.parseInt(tokens[1]));
		    
		    //preparedStmt.executeUpdate();
		    //resultSet = stmt.getResultSet();
		    resultSet = preparedStmt.executeQuery();
		
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    
	    
	    
	    //ArrayList<Customer> data =  new ArrayList<>();
	    try {
	    	//System.out.println("try in database control");
	    	//if(resultSet != null) {
	    	if(resultSet.next()) {	
	    	
	    	videoID=(long)(resultSet.getInt("rentalVideoID"));
	    	System.out.println(videoID);
	    	}
	    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    closeDatabase();
	    System.out.println(videoID);
	    return videoID;
	} //end exist video
	
	public long checkisRentedVideo(String tokens[]) {
		long videoID=0;
		boolean result=false;
		openDatabase();
		
		//Set Data from database
	    ResultSet resultSet = null;
	    try {
	    	
			Statement stmt=con.createStatement();  
			String query = "SELECT * from rentalvideos WHERE rentalVideoID = ? and isRented=1";
	    	System.out.println(query);
	    	System.out.println(tokens[1]);
	    	
	    	
		    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		    preparedStmt.setInt(1,Integer.parseInt(tokens[1]));
		    
		    //preparedStmt.executeUpdate();
		    //resultSet = stmt.getResultSet();
		    resultSet = preparedStmt.executeQuery();
		
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    
	    
	    
	    //ArrayList<Customer> data =  new ArrayList<>();
	    try {
	    	//System.out.println("try in database control");
	    	//if(resultSet != null) {
	    	if(resultSet.next()) {	
	    	
	    	videoID=(long)(resultSet.getInt("rentalVideoID"));
	    	System.out.println(videoID);
	    	}
	    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    closeDatabase();
	    System.out.println(videoID);
	    return videoID;
	} //end is rented video
	
	public void rentalVideo(String tokens[]) {
		
		boolean result=false;
		
		openDatabase();

		//Set Data from database
	    ResultSet resultSet = null;
	        try {
	    	
		String query = "UPDATE rentalvideos "
				+ "SET isRented=TRUE , rentDate = ? , customerID = ? WHERE rentalVideoID = ?";
		
	    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
	    DateTimeFormatter dbformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //for database
		preparedStmt.setString(1,LocalDate.now().format(dbformatter)); //Date
	    
	    preparedStmt.setLong(2,Long.parseLong(tokens[2]));
	    preparedStmt.setLong(3,Long.parseLong(tokens[1]));
	    preparedStmt.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println(e);
	        }

	}// end rental video
	
	public void returnVideo(String tokens[]) {
		
		boolean result=false;
		
		openDatabase();

		//Set Data from database
	    ResultSet resultSet = null;
	        try {
	    	
		String query = "UPDATE rentalvideos "
				+ "SET isRented=FALSE , rentDate = NULL , customerID = NULL WHERE rentalVideoID = ?";
		
	    PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
	    
	    preparedStmt.setLong(1,Long.parseLong(tokens[1]));
	    preparedStmt.executeUpdate();
	        } catch (Exception e) {
	        	System.out.println(e);
	        }

	}// end return video
	
	public static void main (String[] args) {
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/videorental","root","");   //password root
		
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from customers");  
		while(rs.next())  {
			System.out.println(rs.getString("firstName")+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt("customerID"));  
			System.out.println(rs.getString(1)+"  "+rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)
			+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));
		}
			/*
		String query = "update customer set email = ?, name = ? where ID = 1";
	    PreparedStatement preparedStmt = con.prepareStatement(query);
	    preparedStmt.setString(1, "RezaRafeh@gmail.com");
	    preparedStmt.setString(2, "Reza Rafeh");
	    preparedStmt.executeUpdate();
	    query = "INSERT INTO customer (ID,name,email) values(2,'Kazuhisa','kaz@gmail.com')";
	    preparedStmt = con.prepareStatement(query);
	    preparedStmt.executeUpdate();
	    
	    query = "INSERT INTO customer (ID,name,email) values(?,?,?)";
	    preparedStmt = con.prepareStatement(query);
	  
	    preparedStmt.setInt(1, 3);
	    preparedStmt.setString(2, "Kazuhias Kondo");
	    preparedStmt.setString(3, "kazkon@otago.com");
	    
	    
	      // execute the java preparedstatement
	    preparedStmt.executeUpdate(); */
		con.close();  
	}catch(Exception e)
		{ System.out.println(e);}  
	}
}
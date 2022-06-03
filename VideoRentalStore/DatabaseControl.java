package VideoRentalStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseControl {
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
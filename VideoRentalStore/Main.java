package VideoRentalStore;

/**
 * This is a main class for adding and getting customer and video information.
 *  
 * @author Kazuhisa Kondo
 * @version 1.0, 12 May 2022
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.*;

public class Main {
	/** Set date format for New Zealand */
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
	/** customer membership number which starts from 100 */
	static int customerID = 100; 
	
	/** key as video call number for rentalVideoMap which starts from 1 */
		static int rentalVideoId = 1; 
		
		
	/**
	 * This method is used to add new customer from input using HashMap.
	 * @param customerMap the customer map is stored customer data
	 * @return Nothing
	 */
	public static void addCustomer(Map<Integer, Customer> customerMap) {
		/** input from key board */
		Scanner input = new Scanner(System.in);
		
		/** id for customer */
		int customerMembershipNumber = customerID;
		
	
		
		/** first name for customer */
		String firstName;
		
		/** last name  for customer */
		String lastName;
		
		/** email  for customer */
		String email;
		
		/** phone number  for customer */
		String phone;
		
		/** address  for customer */
		String address;
		
		/** post code  for customer */
		String postcode;
		
		/** DOB  for customer */
		String DOB;
		
		System.out.println("Add New Customer");
		
		// Set valuables for customer
		System.out.print("Enter firstName:");
		firstName = input.nextLine();
		System.out.print("Enter lastName:");
		lastName = input.nextLine();
		System.out.print("Enter email:");
		email = input.nextLine();
		System.out.print("Enter phone:");
		phone = input.nextLine();
		System.out.print("Enter address:");
		address = input.nextLine();
		System.out.print("Enter postcode:");
		postcode = input.next();
		System.out.print("Enter DOB(dd/MM/yyyy):");
		DOB = input.next();
					
		/** object for customer to add customer */
		Customer customer1 = new Customer(customerMembershipNumber,firstName,lastName,email,phone,address,postcode,LocalDate.parse(DOB, formatter));
		customerMap.put(customerMembershipNumber,customer1);
		System.out.println("Customer Membership Number:" + customerMembershipNumber + " " + customer1);
		System.out.println("Added New customer successfully!");
		customerMembershipNumber++;
		customerID = customerMembershipNumber;
	}
	
	/**
	 * This method is used to delete customer data using HashMap.
	 * @param customerMap the customer map is stored customer data
	 * @return Nothing
	 */
	public static void deleteCustomer(Map<Integer, Customer> customerMap) {
		/** input information from key board */
		Scanner input = new Scanner(System.in);
		
		/** confirm deleting y or Y as deleting, n or N as cancel deleting*/
		char yn;
		
		/** key as customer membership number for customerMap*/
		int customerMembershipNumber; 
			
		System.out.println("Delete customer");
		
		// Set valuables for customer
		System.out.print("Enter customer membership number:");
		customerMembershipNumber = input.nextInt();	
	
		if (customerMap.containsKey(customerMembershipNumber)) {
        	System.out.println("Customer found");
        	System.out.println(customerMap.get(customerMembershipNumber));
        	
        	System.out.print("Are you sure to delete this customer?(Y/N):");
        	yn= input.next().charAt(0);
        	if(yn=='y'|| yn=='Y') {
        		customerMap.remove(customerMembershipNumber);
        		System.out.println("Deleted the customer successfully");
        	}else {
        		System.out.println("Canceled deleting the customer.");
        	}
		}
        else {
        	System.out.println("Customer not found");
			System.out.println("Try again");
        }		
	}
	
	/**
	 * This method is used to print all customer data using HashMap.
	 * @param customerMap the customer map is stored customer data
	 * @return Nothing
	 */
	public static void printCustomer(Map<Integer, Customer> customerMap) {
		System.out.println("*** All Customers lists ***");
		for(Integer key: customerMap.keySet()){
			System.out.println("ID:" + key +" "+ customerMap.get(key));
		}
		
		System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
		
	}
	
	/**
	 * This method is used to save customer data on the file.
	 * @param customerMap the video map is stored customer data
	 * @return Nothing
	 */
	public static void saveCustomers(Map<Integer, Customer>	customerMap) {
		Scanner input = new Scanner(System.in);
		
		/** answer for overwrite question y or Y as yes, n or N as no */ 
		char yn = 0;
		
		// checking file exists
		boolean fileExists;
		File filename = new File("C:\\TMA3_Project_Q3_Sol\\customer.txt");
		fileExists = filename.exists();
		if(fileExists) {
			System.out.println("File already exists.");
			// loop condition 0:loop 1:exit loop
			int option =0;
			while(option ==0) {
				System.out.print("Are you sure to overwrite this file?(Y/N):");
				yn= input.next().charAt(0);
				if(yn=='n' || yn=='N' || yn=='y' || yn=='Y') option =1;
				else System.out.println("Wrong character!  Try again.");
			}
		}
		if(yn=='n' || yn=='N') {
			System.out.println("Canceled to save customer data on the file");
		}
		else {
			try{
    			FileWriter file = new FileWriter("C:\\TMA3_Project_Q3_Sol\\customer.txt");
    			file.write("ID;firstName;lastName;email;phone;address;postcode;DOB\n");
    			for(Integer key: customerMap.keySet()){
    				file.write(key+";"+ customerMap.get(key).toLine());
    			}
		
    			file.close();
    			System.out.println("Saved customer data successfully");
    		}catch(Exception e){
    			System.out.println("Error: File cannot be saved!");
    		}
		}
	}
	
	/**
	 * This method is used to read customer data on the file.
	 * @param customerMap the customer map is stored customer data
	 * @return Nothing
	 */
	public static void readCustomers(Map<Integer, Customer>	customerMap) {
		Scanner input = new Scanner(System.in);
		
		/** answer for overwrite question y or Y as yes, n or N as no */ 
		char yn = 0;
		int option =0;
		while(option ==0) {
			System.out.println("The current customer data will be replaced by a file.");
			System.out.print("Are you sure to continue to read customer data file?(Y/N):");
			yn= input.next().charAt(0);
			if(yn=='n' || yn=='N' || yn=='y' || yn=='Y') option =1;
			else System.out.println("Wrong character!  Try again.");
		}
	
	if(yn=='n' || yn=='N') {
		System.out.println("Canceled to read customer data on the file");
	}
	else {	
		//clear customerMap
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
					customerID = 0;
					for(Integer key: customerMap.keySet()){
						if(key > customerID) customerID = key;
					}
					customerID++;
					
					System.out.println("Read customer data successfully");
					
				}catch(Exception e){
					System.out.println("File cannot be opened");
				}
		}
	}
	
	/**
	 * This method is used to add customer data using HashMap for testing.
	 * @return Nothing
	 */
	public static void addCustomerTestData(Map<Integer, Customer> customerMap) {
		
		/** Customers data for testing */
		String [][] custData = {{"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200","10/11/1980"},
				                {"Mark", "Brown","mark.brown1998@gmail.com","021401201","450 Queen Street, Auckland","1010","05/08/1995"},
				                {"Richard", "Garcia","richard.garcia2509@gmail.com","022197433","31 Kapanga Rd Coromandel","3506","25/09/1985"}};
			    
		for(int i=0; i< custData.length; i++) {	
			/** object for customer to add customer data */
			Customer customer1 = new Customer(customerID,custData[i][0],custData[i][1],custData[i][2],custData[i][3],custData[i][4],custData[i][5],LocalDate.parse(custData[i][6], formatter));
			customerMap.put(customerID,customer1);
			customerID++;
		}
	}


	/**
	 * This method is used to add new video data using HashMap.
	 * @param rentalVideoMap the rental Video map is stored rental video data
	 * @return Nothing
	 */
	public static void addRentalVideo(Map<Integer, rentalVideo> rentalVideoMap) {
		/** input information from key board */
		Scanner input = new Scanner(System.in);
		
		/** key as video call number for rentalVideoMap*/
		int videoCallNumber = rentalVideoId; 
		
		/** title for rental video */
		String title;
		
		/** media type for rental video */
		String media;
		
		System.out.println(" Add new rental video.");
		
		// Set valuables for customer
		System.out.print("Enter title:");
		title = input.nextLine();
		System.out.print("Enter media:");
		media = input.nextLine();
				

		/** object for video to add video data */
		rentalVideo rentalvideo1 = new rentalVideo(videoCallNumber,title,media);
		rentalVideoMap.put(videoCallNumber, rentalvideo1);
		
		System.out.println("video call number:" + videoCallNumber + " " + rentalvideo1);
		System.out.println("Added New video successfully!");
		videoCallNumber++;
							
		rentalVideoId = videoCallNumber;
	
	}

	/**
	 * This method is used to delete rental video data using HashMap.
	 * @param rentalVideoMap the rental Video map is stored rental video data
	 * @return Nothing
	 */
	public static void deleteRentalVideo(Map<Integer, rentalVideo> rentalVideoMap) {
		/** input information from key board */
		Scanner input = new Scanner(System.in);
		
		/** confirm deleting y or Y as deleting, n or N as cancel deleting*/
		char yn;
		
		/** key as video call number for rentalVideoMap*/
		int videoCallNumber; 
			
		System.out.println(" Delete rental video.");
		
		// Set valuables for customer
		System.out.print("Enter video call number:");
		videoCallNumber = input.nextInt();	
	
		if (rentalVideoMap.containsKey(videoCallNumber)) {
        	System.out.println("Video found");
        	System.out.println(rentalVideoMap.get(videoCallNumber));
        	System.out.print("Are you sure to delete this video?(Y/N):");
        	yn= input.next().charAt(0);
        	if(yn=='y'|| yn=='Y') {
        		rentalVideoMap.remove(videoCallNumber);
        		System.out.println("Deleted the video successfully");
        	}else {
        		System.out.println("Canceled deleting the video.");
        	}
		}
        else {
        	System.out.println("Video not found");
			System.out.println("Try again");
        }
	}
	
	/**
	 * This method is used to delete rental video data using HashMap.
	 * @param rentalVideoMap the rental Video map is stored rental video data
	 * @return Nothing
	 */
	public static void printRentalVideo(Map<Integer, rentalVideo> rentalVideoMap) {
	    
		System.out.println("*** All Videos lists ***");
		
		for(Integer key: rentalVideoMap.keySet()){
			System.out.println("Video call number:" + key +" "+ rentalVideoMap.get(key));
		}	
		System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
        
	}
	
	/**
	 * This method is used to rent video
	 * @param rentalVideoMap the rental video map is stored rental video data
	 * @param customerMap the customer map is stored customer data 
	 * @return Nothing
	 */
	public static void rentVideo(Map<Integer,rentalVideo> rentalVideoMap, Map<Integer,Customer> customerMap) {
		/** input from key board */
		Scanner input = new Scanner(System.in);
		
		/** customer membership number for hashmap key */
		int ckey; 
		
		/** rental video call number for hashmap key */
		int vkey;
		
		System.out.print("Enter Video call number:");
		vkey = input.nextInt();
		if(!rentalVideoMap.containsKey(vkey)) {
			System.out.println("Video not found.");
			System.out.println("Try again");
		}else {
			System.out.println("Video title is "+ rentalVideoMap.get(vkey).getTitle());
		
			if(rentalVideoMap.get(vkey).get_isRented()) {
				System.out.println("Unavailable for rental! Someone is renting this video.");
				System.out.println("Try again");
				
			}else {
				System.out.print("Enter Customer membership number:");
				ckey = input.nextInt();
				if(!customerMap.containsKey(ckey)) {
					System.out.println("Customer not found.");
					System.out.println("Try again");
				}else {
					System.out.println("Customer name is "+ customerMap.get(ckey).getFirstName());
					
					// counting customer rented video
					int countVideo=0;
					for(Integer key: rentalVideoMap.keySet()){
						if(rentalVideoMap.get(key).get_renterId()==ckey) {
							if(countVideo==0) System.out.println("This customer is currently renting the following video(s)");
							countVideo++;
							System.out.println("* "+ rentalVideoMap.get(key).getTitle());
						}
						
					}	
					
					if(countVideo==2) {
						System.out.println("Renting video is not available more than two videos.");
					}else {
						rentalVideoMap.get(vkey).rent(customerMap.get(ckey), LocalDate.now());
						System.out.println("Successful rented!");
					}
					System.out.println("Press Enter key to continue...");
			        try
			        {
			            System.in.read();
			        }  
			        catch(Exception e)
			        {}  
				}
			}
		}
	}
	
	/**
	 * This method is used to rent video
	 * @param rentalVideoMap the rental video map is stored rental video data 
	 * @return Nothing
	 */
	public static void returnVideo(Map<Integer,rentalVideo> rentalVideoMap) {
		/** input from key board */
		Scanner input = new Scanner(System.in);
		
		/** rental video call number for hashmap key */
		int vkey;
		
		 //return date for testing overdue calculation
		//String returnDate;
		
		System.out.println("Return video");
		System.out.print("Enter Video call number:");
		vkey = input.nextInt();
		if(!rentalVideoMap.containsKey(vkey)) {
			System.out.println("Video not found.");
			System.out.println("Try again");
		}else {
			if(!rentalVideoMap.get(vkey).get_isRented()) {
				System.out.println("Video title is "+ rentalVideoMap.get(vkey).getTitle());
				System.out.println("No one is rented this video!");
				System.out.println("Try again");
			}else {
				System.out.println("Rented video information:"+rentalVideoMap.get(vkey));
				/* test overdue fine calculation
				System.out.print("Enter return date (dd/MM/yyyy):");
				returnDate = input.next();
				System.out.println("Overdue fine is $" + rentalVideoMap.get(vkey).calculateFine(LocalDate.parse(returnDate, formatter)));
				*/
				System.out.println("Overdue fine is $" + rentalVideoMap.get(vkey).calculateFine(LocalDate.now()));
				rentalVideoMap.get(vkey).returnRental();
				System.out.println("Successful returned!");
			}
		}
	}
	
	/**
	 * This method is used to save video data on the file.
	 * @param rentalVideoMap the video map is stored video data
	 * @return Nothing
	 */
	public static void saveVideos(Map<Integer, rentalVideo> rentalVideoMap) {
		Scanner input = new Scanner(System.in);
		
		/** answer for overwrite question y or Y as yes, n or N as no */ 
		char yn = 0;
		
		// checking file exists
		boolean fileExists;
		File filename = new File("C:\\TMA3_Project_Q3_Sol\\video2.txt");
		fileExists = filename.exists();
		if(fileExists) {
			System.out.println("File already exists.");
			// loop condition 0:loop 1:exit loop
			int option =0;
			while(option ==0) {
				System.out.print("Are you sure to overwrite this file?(Y/N):");
				yn= input.next().charAt(0);
				if(yn=='n' || yn=='N' || yn=='y' || yn=='Y') option =1;
				else System.out.println("Wrong character!  Try again.");
			}
		}
		if(yn=='n' || yn=='N') {
			System.out.println("Canceled to save video data on the file");
		}
		else {
			try{
    			FileWriter file = new FileWriter("C:\\TMA3_Project_Q3_Sol\\video2.txt");
    			file.write("ID;title;media;isRented;renter;rentDate\n");
    			for(Integer key: rentalVideoMap.keySet()){
    				file.write(key+";"+ rentalVideoMap.get(key).toLine());
    			}
		
    			file.close();
    			System.out.println("Saved video data successfully");
    		}catch(Exception e){
    			System.out.println("Error: File cannot be saved!");
    		}
		}
	}
	
	/**
	 * This method is used to read video data on the file.
	 * @param rentalVideoMap the video map is stored video data
	 * @return Nothing
	 */
	public static void readRentalVideos(Map<Integer, rentalVideo> rentalVideoMap, Map<Integer, Customer> customerMap) {
		Scanner input = new Scanner(System.in);
		
		/** answer for overwrite question y or Y as yes, n or N as no */ 
		char yn = 0;
		int option =0;
		while(option ==0) {
			System.out.println("The current video data will be replaced by a file.");
			System.out.print("Are you sure to continue to read video data file?(Y/N):");
			yn= input.next().charAt(0);
			if(yn=='n' || yn=='N' || yn=='y' || yn=='Y') option =1;
			else System.out.println("Wrong character!  Try again.");
		}
	
		if(yn=='n' || yn=='N') {
			System.out.println("Canceled to read video data on the file");
		}
		else {	
			//clear rentalVideoMap
			rentalVideoMap.clear();
		
			try{
				BufferedReader file = new BufferedReader(new FileReader("C:\\TMA3_Project_Q3_Sol\\video.txt"));
				String line = file.readLine();

				do {
					line = file.readLine();
				
					if(line == null) break; 
			
				String [] tokens = line.split(";");
			
				int videoNumber = Integer.parseInt(tokens[0]); //video call number
				
				
				Customer customer1;
				if(tokens[4].equals("null")) customer1 = null;
				else customer1 = customerMap.get(Integer.parseInt(tokens[4]));
				
				LocalDate rentDate;
				if(tokens[5].equals("null")) rentDate = null;
				else rentDate = LocalDate.parse(tokens[5],formatter);
				
				rentalVideo rentalvideo1 = new rentalVideo(tokens[1],tokens[2],Boolean.parseBoolean(tokens[3]),customer1,rentDate);
				rentalVideoMap.put(videoNumber,rentalvideo1);	
				} while(true);
				file.close();
			
				//set next new videoId
				rentalVideoId = 0;
				for(Integer key: rentalVideoMap.keySet()){
					if(key > rentalVideoId) rentalVideoId = key;
				}
				rentalVideoId++;
			
				System.out.println("Read video data successfully");
			
			}catch(Exception e){
			System.out.println("File cannot be opened");
			}	
		}
	}
	/**
	 * This method is used to add video data using HashMap for testing.
	 * @return Nothing
	 */
	public static void addRentalVideoTestData(Map<Integer,rentalVideo> rentalVideoMap) {
	      
		
		/** Videos data for testing */
		String [][] rentvidData = {{"Star Wars ", "VHS"},
				                {"La La Land", "DVD"},
				                {"Kite Runner", "DVD"},
				                {"The Trip to Spain", "DVD"}};
		
	   
		for(int i=0; i< rentvidData.length; i++) {
			
			/** object for video to add video data */
			rentalVideo rentalvideo1 = new rentalVideo(rentalVideoId,rentvidData[i][0],rentvidData[i][1]);
			rentalVideoMap.put(rentalVideoId,rentalvideo1);
			rentalVideoId++;
		}

	}
	
	
	
	/** Object for storing new release data using HashMap */
	static Map<Integer,newRelease> newreleases = new HashMap<>();

	 /** rental video ID which starts from 1 */
	static int newReleaseId = 1; 

	/**
	 * This method is used to add new video data using HashMap.
	 * @return Nothing
	 */
	public static void addNewRelease() {
		/** input from key board */
		Scanner input = new Scanner(System.in);
		
		/** title for rental video */
		String title;
		
		/** media type */
		String media;
		
		
		// Set valuables for customer
		System.out.print("Enter title:");
		title = input.nextLine();
		System.out.print("Enter media:");
		media = input.nextLine();
				

		/** object for video to add video data */
		newRelease newrelease1 = new newRelease(title,media);
		newreleases.put(newReleaseId,newrelease1);
		newReleaseId++;					
		input.close();
	}

	/**
	 * This method is used to add video data using HashMap for testing.
	 * @return Nothing
	 */
	public static void addNewReleaseTestData() {
	      
		
		/** Videos data for testing */
		String [][] rentvidData = {{"Star Wars ", "VHS"},
				                {"La La Land", "DVD"},
				                {"Kite Runner", "DVD"},
				                {"The Trip to Spain", "DVD"}};
		
	   
		for(int i=0; i< rentvidData.length; i++) {
			
			/** object for video to add video data */
			newRelease newrelease1 = new newRelease(rentvidData[i][0],rentvidData[i][1]);
			newreleases.put(newReleaseId,newrelease1);
			newReleaseId++;
		}

	}
	/**
	 * main methods for new release
	 * @return Nothing
	 */
	public static void NewReleaseMain(Map<Integer,Customer> customerMap) {
				// Add video test data 
				addNewReleaseTestData();
				
				/** select menu number for menu */
				int menuNum =0;
				
				/** input from keyboard */
				Scanner input = new Scanner(System.in);
				
			while(menuNum!=6) {
			try {
				System.out.println("*** New Release Menu ***");
				System.out.println("1 Add New Video");
				System.out.println("2 Show all Video");
				System.out.println("--------------");
				System.out.println("3 Rental Video");
				System.out.println("4 Return Video");
				System.out.println("5 Show overdue Lists");
				System.out.println("6 Exit");
				System.out.print("Select Menu:");
				
				menuNum = input.nextInt();
								
				switch (menuNum) {
				case 1:
					// main methods for customer
					addNewRelease();
					break;
				case 2:
					// Show all videos data
					for(Integer key: newreleases.keySet()){
						System.out.println("ID:" + key +" "+ newreleases.get(key));
					}
				     break;
				
				case 3:
					// Rental Video
					/** customer id for hashmap */
					int ckey; 
					
					/** rental video id for hashmap */
					int vkey;
					String sdate;
									
					System.out.print("Enter Customer ID:");
					ckey = input.nextInt();
					System.out.println("Customer name is "+ customerMap.get(ckey).getFirstName());
					
					System.out.print("Enter rantal Video ID:");
					vkey = input.nextInt();
					System.out.println("Video title is "+ newreleases.get(vkey).getTitle());
										
					System.out.print("Enter rental date (dd/MM/yyyy):");
					sdate = input.next();
					
					if(newreleases.get(vkey).get_isRented()) {
						System.out.println("Unavailable rental! Someone is renting this video");
					}else {
						System.out.println(ckey + "customer" + customerMap.get(ckey));
						newreleases.get(vkey).rent(customerMap.get(ckey), LocalDate.parse(sdate, formatter));
						System.out.println("Successful rented!");
					}
						
									
					break;
				
				case 4:
					// return video
					
					System.out.print("Enter rantal Video ID:");
					vkey = input.nextInt();
					System.out.print("Enter return date (dd/MM/yyyy):");
					sdate = input.next();
					System.out.println("Overdue fine is $" + newreleases.get(vkey).calculateFine(LocalDate.parse(sdate, formatter)));
					
					newreleases.get(vkey).returnRental();
						
					break;
					
				case 5:
					// Show overdue lists for rental video
					for(Integer key: newreleases.keySet()){
						if(newreleases.get(key).isOverdue(LocalDate.parse("23/08/2017", formatter))){
							System.out.println("ID:" + key +" "+ newreleases.get(key)+ " fine:$" + newreleases.get(key).calculateFine(LocalDate.parse("23/08/2017", formatter)));
						}
					}
						
					break;
				
				
				
				case 6:
					// exit menu
					System.out.println("Exit");
					break;
				
				default:
					System.out.println("Wrong number!");
					break;
				}
				}
			catch (Exception e) {
				input.nextLine(); // Clear buffer to avoid infinite loop
				System.out.println("Enter correct number");
					
			} 
			}// end while
				
	}
	
	
	
	/**
	 * This is the main method which adding and showing customer and video data.
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		
		/** Object for storing rental videos data using HashMap */
		Map<Integer,rentalVideo> rentalVideoMap = new HashMap<>();
		
		/** Object for storing customers data using HashMap */
		Map<Integer,Customer> customerMap = new HashMap<>();
		
		/** select menu number for menu */
		int menuNum =0;
		
		/** input from keyboard */
		Scanner input = new Scanner(System.in);

		/** read test data for customer */
		addCustomerTestData(customerMap);
		
		/** read test data for rental video */
		addRentalVideoTestData(rentalVideoMap);
		
		while(menuNum!=13) {
			try {
			System.out.println("********  Video Rental Menu  ********");
			System.out.println(" 1- Add a new rental video");
			System.out.println(" 2- Delete an existing rental video");
			System.out.println(" 3- Print the list of rental videos");
			System.out.println(" ------------------------------------");
			System.out.println(" 4- Add a new customer");
			System.out.println(" 5- Delete an existing customer");
			System.out.println(" 6- Print the list of customers");
			System.out.println(" ------------------------------------");
			System.out.println(" 7- Rent a video");
			System.out.println(" 8- Return a video");
			System.out.println(" ------------------------------------");
			System.out.println(" 9- Save all rental videos on the file");
			System.out.println("10- Read rental videos from file");
			System.out.println("11- Save all customers on the file");
			System.out.println("12- Read customers from file");
			System.out.println(" ------------------------------------");
			System.out.println("13- Quit");
			System.out.print("Select Menu:");
			menuNum = input.nextInt();
		
		
		switch (menuNum) {
		case 1:
			// add rental video for rentalVideo class
			addRentalVideo(rentalVideoMap);
			break;
		case 2:
			// delete rental video for rentalVideo class
			deleteRentalVideo(rentalVideoMap);
		     break;
		
		case 3:
			// print all rental video for rentalVideo class
			printRentalVideo(rentalVideoMap);
		    break;
		
		case 4: 
			// Add customer
			addCustomer(customerMap);
		    break;
		    
		case 5:
			// Delete customer
			deleteCustomer(customerMap);
			break;
		
		case 6:
			// Print all customers information
			printCustomer(customerMap);
			break;
		
		case 7:
			// Rent video
			rentVideo(rentalVideoMap,customerMap);
			break;	
		
		case 8:
			// Return video
			returnVideo(rentalVideoMap);
			break;	
		
		case 9:
			// Save rental videos data on the file
			saveVideos(rentalVideoMap);
			break;
			
		case 10:
			//read rental video data from file
			readRentalVideos(rentalVideoMap,customerMap);
			break;
		
		case 11:
			// Save customer data on the file
			saveCustomers(customerMap);
			break;
			
		case 12:
			// Read customer data from file
			readCustomers(customerMap);
			break;	
		
		default:
			System.out.println("Wrong number!");
			break;
		}
		} catch(Exception e) {
			input.nextLine(); // Clear buffer to avoid infinite loop
			System.out.println("Enter correct number!");
		} 
		}// end while		
	}
	
}
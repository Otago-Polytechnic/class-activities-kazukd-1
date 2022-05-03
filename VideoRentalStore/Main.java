package VideoRentalStore;

/**
 * This is a main class for adding and getting customer and video information.
 *  
 * @author Kazuhisa Kondo
 * @version 1.0, 3 May 2022
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	/** Object for storing customers data using HashMap */
	static Map<Integer,Customer> customers = new HashMap<>();
	
	/** customer ID which starts from 100 */
	static int customerID = 100; 
	
	/**
	 * This method is used to add new customer from input using HashMap.
	 * @return Nothing
	 */
	public static void addCustomer() {
		/** input from key board */
		Scanner input = new Scanner(System.in);

		/** id for customer */
		int id;
		
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
		
		
		// Set valuables for customer
		System.out.print("Enter firstName:");
		firstName = input.next();
		System.out.print("Enter lastName:");
		lastName = input.next();
		System.out.print("Enter email:");
		email = input.next();
		System.out.print("Enter phone:");
		phone = input.next();
		System.out.print("Enter address:");
		address = input.next();
		System.out.print("Enter postcode:");
		postcode = input.next();
					
		/** object for customer to add customer */
		Customer customer1 = new Customer(firstName,lastName,email,phone,address,postcode);
		customers.put(customerID,customer1);
		customerID++;
	}
	
	
	/**
	 * This method is used to add customer data using HashMap for testing.
	 * @return Nothing
	 */
	public static void addCustomerTestData() {
		
		/** Customers data for testing */
		String [][] custData = {{"Maria", "Brown","maria@gmail.com","0211239876","350 Queen St.,Auckland","1010"},
				                {"Mark", "Moore","mark.moore@gmail.com","0220551234","502 Mt Eden Road, Mount Eden, Auckland","1024"}};
		
	    
		for(int i=0; i< custData.length; i++) {
			
			/** object for customer to add customer data */
			Customer customer1 = new Customer(custData[i][0],custData[i][1],custData[i][2],custData[i][3],custData[i][4],custData[i][5]);
			customers.put(customerID,customer1);
			customerID++;
		}
		
		
		
	}
	
	/**
	 * Test setter Method for customer
	 * @return Nothing
	 */
	public static void testCustomerSetterMethods() {
		
		/** id for customer */
		int id;
		
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
		
		
		// Set valuables for testing data for customer
		firstName = "Andy";
		lastName= "Walters";
		email= "andy.walters@outlook.com";
		phone= "0218532002";
		address= "67 Quey Street, CBD, Auckland";
		postcode= "1010";
		
		// Testing setter methods for customer id is 101
		id=101;
		
		// test setter methods
		customers.get(id).setFirstName(firstName);
		customers.get(id).setLastName(lastName);
		customers.get(id).setEmail(email);
		customers.get(id).setPhone(phone);
		customers.get(id).setAddress(address);
		customers.get(id).setPostcode(postcode);
		
		
		System.out.println("Testing setterMethods changing data for customer id is " + id);
		
		// Display customer id 101 data
		System.out.println("ID:" + id + " " + customers.get(id));
		
		
	}
	
	/**
	 * Test getter methods for customer
	 * @return Nothing
	 */
	public static void testCustomerGetterMethods() {
		System.out.println("Testing getter Methods");
		System.out.println("------");
		for(Integer key: customers.keySet()) {
			
			System.out.println("ID:"+ key);
			System.out.println("FirstName:"+ customers.get(key).getFirstName());
			System.out.println("LastName:"+ customers.get(key).getLastName());
			System.out.println("email:"+ customers.get(key).getEmail());
			System.out.println("Phone:"+ customers.get(key).getPhone());
			System.out.println("Address:"+ customers.get(key).getAddress());
			System.out.println("Postcode:"+ customers.get(key).getPostcode());
			System.out.println("------");
			
		}
	}
	
	public static void customerMain() {
				// Add customer data for testing
				addCustomerTestData();
				
				/** select menu number for menu */
				int menuNum =0;
				
				/** input from keyboard */
				Scanner input = new Scanner(System.in);

				while(menuNum!=5) {
				System.out.println("*** Customer Menu ***");
				System.out.println("1 Add New Customer");
				System.out.println("2 Show all Customers");
				System.out.println("--------------");
				System.out.println("3 Test Setter methods");
				System.out.println("4 Test Getter methods");
				System.out.println("5 Exit");
				System.out.print("Select Menu:");
				menuNum = input.nextInt();
				
				
				switch (menuNum) {
				case 1:
					// main methods for customer
					addCustomer();
					break;
				case 2:
					// Show all customers data
					for(Integer key: customers.keySet()){
						System.out.println("ID:" + key +" "+ customers.get(key));
					}
				     break;
				
				case 3:
				  // Testing setter methods
					testCustomerSetterMethods();
					
					// Show all customers data after testing setter methods
					System.out.println("Show all data after testing setter methods.");
					for(Integer key: customers.keySet()){
						System.out.println("ID:" + key +" "+ customers.get(key));
					}
					
					break;
				
				case 4:
					// Testing getter methods for customer
					testCustomerGetterMethods();
						
					break;
					
				case 5:
					// exit menu
					System.out.println("Exit");
					break;
				
				default:
					System.out.println("Wrong number!");
					break;
				}
				}// end while
					
	}
	
	
	/** Object for storing videos data using HashMap */
	static Map<Integer,Video> videos = new HashMap<>();
	
	 /** video ID which starts from 1 */
	static int videoId = 1; 
	
	/**
	 * This method is used to add new video data using HashMap.
	 * @return Nothing
	 */
	public static void addVideo() {
		/** input from key board */
		Scanner input = new Scanner(System.in);
		
		/** title for video */
		String title;
		
		/** genre for video */
		String genre;
		
		/** release date for video */
		int releaseYear;
		
		/** length for video */
		int length;
		
		/** rental fee for video */
		double rentalFee;
		
		/** rented date for video */
		String rentedDate;
		
		/** due date for video */
		String dueDate;
		
		/** returned date  for video */
		String returnedDate;
		
		/** customer id  for video */
		int customerId;
		
		/** penaltyFee  for video */
		double penaltyFee;
		
		// Set valuables for customer
		System.out.print("Enter title:");
		title = input.next();
		System.out.print("Enter genre:");
		genre = input.next();
		System.out.print("Enter releaseYear:");
		releaseYear = input.nextInt();
		System.out.print("Enter length:");
		length = input.nextInt();
		System.out.print("Enter rentalFee:");
		rentalFee = input.nextDouble();
		System.out.print("Enter rentedDate:");
		rentedDate = input.next();
		System.out.print("Enter dueDate:");
		dueDate = input.next();
		System.out.print("Enter returnedDate:");
		returnedDate = input.next();
		System.out.print("Enter customerId:");
		customerId = input.nextInt();
		System.out.print("Enter penaltyFee:");
		penaltyFee = input.nextDouble();
		

		/** object for video to add video data */
		Video video1 = new Video(title,genre,releaseYear,length,rentalFee,rentedDate,dueDate,returnedDate,customerId,penaltyFee);
		videos.put(videoId,video1);
		videoId++;					
		
	}
	
	/**
	 * This method is used to add video data using HashMap for testing.
	 * @return Nothing
	 */
	public static void addVideoTestData() {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	      
		
		/** Videos data for testing */
		String [][] vidData = {{"Transcendence", "Action, Mystery, Thriller, Sci-Fi","2014","119","3.00","26/04/2022","01/05/2022","30/04/2022","100","0"},
				                {"Replicas", "Sci-Fi, Drama, Mystery, Thriller","2018","107","4.00","20/04/2022","25/04/2022","","101","8.00"},
				                {"Archive", "Drama, Mystery, Sci-Fi, Thriller","2020","105","5.00","20/04/2022","25/04/2022","","101","8.00"}};
		
	   
		for(int i=0; i< vidData.length; i++) {
			
			/** object for video to add video data */
			Video video1 = new Video(vidData[i][0],vidData[i][1],Integer.parseInt(vidData[i][2]),Integer.parseInt(vidData[i][3]),Double.parseDouble(vidData[i][4]),vidData[i][5],vidData[i][6],vidData[i][7],Integer.parseInt(vidData[i][8]),Double.parseDouble(vidData[i][9]));
			videos.put(videoId,video1);
			videoId++;
		}

	}

	
	/**
	 * Test setter Method for customer
	 * @return Nothing
	 */
	public static void testVideoSetterMethods() {
		/** title for video */
		String title;
		
		/** genre for video */
		String genre;
		
		/** release date for video */
		int releaseYear;
		
		/** length for video */
		int length;
		
		/** rental fee for video */
		double rentalFee;
		
		/** rented date for video */
		String rentedDate;
		
		/** due date for video */
		String dueDate;
		
		/** returned date  for video */
		String returnedDate;
		
		/** customer id  for video */
		int customerId;
		
		/** penaltyFee  for video */
		double penaltyFee;
		
		// Set valuables for testing data for video
		title = "THE MATRIX RESURRECTIONS";
		genre= "Sci-Fi, Action";
		releaseYear= 2021;
		length= 148;
		rentalFee= 2.50;
		rentedDate= "20/03/2022";
		dueDate= "25/03/2022";
		returnedDate= "28/03/2022";
		customerId= 200;
		penaltyFee= 3;
				
				// Testing setter methods for video id is 3
				int id=3;
				
				// test setter methods
				videos.get(id).setTitle(title);
				videos.get(id).setGenre(genre);
				videos.get(id).setReleaseYear(releaseYear);
				videos.get(id).setLength(length);
				videos.get(id).setRentalFee(rentalFee);
				videos.get(id).setRentedDate(rentedDate);
				videos.get(id).setDueDate(dueDate);
				videos.get(id).setReturnedDate(returnedDate);
				videos.get(id).setCustomerId(customerId);
				videos.get(id).setPenaltyFee(penaltyFee);
				
				
				
				System.out.println("Testing setterMethods changing data for video id is " + id);
				
				// Display video id 3 data
				System.out.println("ID:" + id + " " + videos.get(id));
		
		
		
	}
	
	
	/**
	 * Test getter methods for video
	 * @return Nothing
	 */
	public static void testVideoGetterMethods() {
		System.out.println("------------------------------------");
		System.out.println("Testing getter Methods for video");
		System.out.println("------------------------------------");
		for(Integer key: videos.keySet()) {
			
			System.out.println("ID:"+ key);
			System.out.println("title :"+ videos.get(key).getTitle());
			System.out.println("genre :"+ videos.get(key).getGenre());
			System.out.println("ReleaseYear :"+ videos.get(key).getReleaseYear());
			System.out.println("length(minutes) :"+ videos.get(key).getLength());
			System.out.println("rentalFee:$"+ videos.get(key).getRentalFee());
			System.out.println("rentedDate:"+ videos.get(key).getRentedDate());
			System.out.println("dueDate:"+ videos.get(key).getDueDate());
			System.out.println("returnedDate:"+ videos.get(key).getReturnedDate());
			System.out.println("customerId:"+ videos.get(key).getCustomerId());
			System.out.println("penaltyFee:$"+ videos.get(key).getPenaltyFee());
			System.out.println("------");
			
		}
	}
	
	/**
	 * main methods for video
	 * @return Nothing
	 */
	public static void videoMain() {
				// Add video test data 
				addVideoTestData();
				
				/** select menu number for menu */
				int menuNum =0;
				
				/** input from keyboard */
				Scanner input = new Scanner(System.in);

				while(menuNum!=5) {
				System.out.println("*** Video Menu ***");
				System.out.println("1 Add New Video");
				System.out.println("2 Show all Video");
				System.out.println("--------------");
				System.out.println("3 Test Setter methods");
				System.out.println("4 Test Getter methods");
				System.out.println("5 Exit");
				System.out.print("Select Menu:");
				menuNum = input.nextInt();
				
				
				switch (menuNum) {
				case 1:
					// main methods for customer
					addVideo();
					break;
				case 2:
					// Show all videos data
					for(Integer key: videos.keySet()){
						System.out.println("ID:" + key +" "+ videos.get(key));
					}
				     break;
				
				case 3:
					// Testing setter methods for video
					testVideoSetterMethods();
						
					// Show all videos data after testing setter methods
					System.out.println("Show all data after testing setter methods for video.");
					for(Integer key: videos.keySet()){
						System.out.println("ID:" + key +" "+ videos.get(key));
					}
					
					break;
				
				case 4:
					// Testing getter methods for video
					testVideoGetterMethods();
						
					break;
					
				case 5:
					// exit menu
					System.out.println("Exit");
					break;
				
				default:
					System.out.println("Wrong number!");
					break;
				}
				}// end while
				
					
	}
	
	/**
	 * This is the main method which adding and showing customer and video data.
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		/** select menu number for menu */
		int menuNum =0;
		
		/** input from keyboard */
		Scanner input = new Scanner(System.in);

		while(menuNum!=3) {
		System.out.println("*** Video Rental Main Menu ***");
		System.out.println("1 Manage Customer");
		System.out.println("2 Manage Video");
		System.out.println("3 Exit");
		System.out.print("Select Menu:");
		menuNum = input.nextInt();
		
		
		switch (menuNum) {
		case 1:
			// main methods for customer
			customerMain();
			break;
		case 2:
			// main methods for video
			videoMain();
		     break;
		
		case 3:
			// exit menu
			System.out.println("Exit");
			break;
		
		default:
			System.out.println("Wrong number!");
			break;
		}
		}// end while	
		
	}

}
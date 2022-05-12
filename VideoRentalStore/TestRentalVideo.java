package VideoRentalStore;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class TestRentalVideo {

	// Test get_isRented method after add rental video
	@Test
	void test_get_isRented() {
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
		assertTrue("isRented must return false after add rentalVideo", rentalvideo1.get_isRented() == false );
		//fail("Not yet implemented");
	}
	
	// Test get_renterId method after add rental video
	@Test
	void test_get_renterId() {
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
		assertTrue("renterId must be 0 because no one rent this video after add rentalVideo", rentalvideo1.get_renterId() == 0 );
	}
	
	
	// Test get_title method after add rental video
	@Test
	void test_get_title() {
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
		assertTrue("title must be Matrix", rentalvideo1.get_title().equals("Matrix"));
	}
	
	// Test get_isRented method after rent video 
	@Test
	void test_rent1() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
	
		/** object for rentalVideo to add video data */
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
		
		rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
		
		assertTrue("isRented must be true after rent Video", rentalvideo1.get_isRented() == true );
		
	}
	
	// Test get_renterId method after rent video 
	@Test
	void test_rent2() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		
		/** object for rentalVideo to add video data */
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
			
		rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
			
		assertTrue("renterId must be 100 because customerId 100 rent this video", rentalvideo1.get_renterId() == 100 );
			
	}
	
	
	// Test isOverdue method rentDate 13/05/2022 returnDate 13/05/2022 
	@Test
	void test_isOverdue1() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
			
		/** object for rentalVideo to add video data */
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
				
		rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
			
		assertTrue("isOverdue must be false", rentalvideo1.isOverdue(LocalDate.parse("13/05/2022", formatter))== false );
				
	}	
	
	// Test isOverdue method rentDate 13/05/2022 returnDate 18/05/2022 
	@Test
	void test_isOverdue2() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		
		/** object for rentalVideo to add video data */
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
			
		rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
		
		assertTrue("isOverdue must be false", rentalvideo1.isOverdue(LocalDate.parse("18/05/2022", formatter))== false );
			
	}	
	
	// Test isOverdue method rentDate 13/05/2022 returnDate 19/05/2022 
	@Test
	void test_isOverdue3() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
			
		/** object for rentalVideo to add video data */
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
				
		rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
			
		assertTrue("isOverdue must be true", rentalvideo1.isOverdue(LocalDate.parse("19/05/2022", formatter))== true );
				
	}	

	// Test calculateFine method rentDate 13/05/2022 returnDate 18/05/2022 
	@Test
	void test_calculateFine1() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
				
		/** object for rentalVideo to add video data */
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
					
		rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
				
		assertTrue("Fine must be 0", rentalvideo1.calculateFine(LocalDate.parse("18/05/2022", formatter))== 0 );
					
	}		
	
	// Test calculateFine method rentDate 13/05/2022 returnDate 21/05/2022 
	@Test
	void test_calculateFine2() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
					
		/** object for rentalVideo to add video data */
		rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
						
		rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
					
		assertTrue("Fine must be 3", rentalvideo1.calculateFine(LocalDate.parse("21/05/2022", formatter))== 3 );
						
	}		

	
	// Test isRented after returnRental method 
		@Test
		void test_isRented_after_returnRental() {
			/** Set date format for New Zealand */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							
			/** object for customer to add customer data */
			Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
						
			/** object for rentalVideo to add video data */
			rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
							
			rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
			rentalvideo1.returnRental();		
			assertTrue("isRented must be false after return Video", rentalvideo1.get_isRented() == false);
							
		}		
		
		// Test get_renterId method after return video 
		@Test
		void test_getRenterId_after_returnRental() {
			/** Set date format for New Zealand */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
			/** object for customer to add customer data */
			Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
			
			/** object for rentalVideo to add video data */
			rentalVideo rentalvideo1 = new rentalVideo("Matrix","DVD");
				
			rentalvideo1.rent(customer1,LocalDate.parse("13/05/2022", formatter));
			rentalvideo1.returnRental();
			assertTrue("renterId must be 0", rentalvideo1.get_renterId() == 0 );
				
		}
		
		
}

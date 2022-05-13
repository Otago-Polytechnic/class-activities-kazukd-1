package VideoRentalStore;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class TestCustomer {

	// Test getCustomerId method 
	@Test
	void test_getCustomerId() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
					
		assertTrue("getCustomerId must be 100", customer1.getCustomerId() == 100 );
			
	}
	
	// Test getFirstName method 
	@Test
	void test_getFirstName() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
					
		assertTrue("getFirstName must be Alison", customer1.getFirstName().equals("Alison"));
			
	}
	
	// Test getLastName method 
	@Test
	void test_getLastName() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
						
		assertTrue("getLastName must be Strange", customer1.getLastName().equals("Strange"));
				
	}
	
	// Test geteMail method 
	@Test
	void test_geteMail() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
					
		assertTrue("geteMail must be alison.strange@adjunct.openpolytechnic.ac.nz", customer1.getEmail().equals("alison.strange@adjunct.openpolytechnic.ac.nz"));
			
	}

	// Test getPhone method 
	@Test
	void test_getPhone() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
					
		assertTrue("getPhone must be 022452178", customer1.getPhone().equals("022452178"));
			
	}

	
	// Test getAddress method 
	@Test
	void test_getAddress() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
					
		assertTrue("getAddress must be alison.strange@adjunct.openpolytechnic.ac.nz", customer1.getAddress().equals("3 Tristram Street, Hamilton"));
			
	}

	// Test getPostcode method 
	@Test
	void test_getPostcode() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
					
		assertTrue("getPostcode must be 3200", customer1.getPostcode().equals("3200"));
			
	}

	// Test getDOB method 
	@Test
	void test_getDOB() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
				
		assertTrue("getDOB must be 10/11/1980", customer1.getDOB().format(formatter).equals("10/11/1980"));
				
	}

	
	
	// Test setFirstName method 
	@Test
	void test_setFirstName() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		customer1.setFirstName("Mark");			
		assertTrue("getFirstName must be Mark", customer1.getFirstName().equals("Mark"));
			
	}
	
	// Test setLastName method 
	@Test
	void test_setLastName() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		customer1.setLastName("Brown");					
		assertTrue("getLastName must be Brown", customer1.getLastName().equals("Brown"));
				
	}
	
	// Test seteMail method 
	@Test
	void test_seteMail() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		customer1.setEmail("mark.brown1998@gmail.com");			
		assertTrue("geteMail must be mark.brown1998@gmail.com", customer1.getEmail().equals("mark.brown1998@gmail.com"));
			
	}

	// Test setPhone method 
	@Test
	void test_setPhone() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		customer1.setPhone("021401201");			
		assertTrue("getPhone must be 021401201", customer1.getPhone().equals("021401201"));
			
	}

	
	// Test setAddress method 
	@Test
	void test_setAddress() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		customer1.setAddress("450 Queen Street, Auckland");			
		assertTrue("getAddress must be 450 Queen Street, Auckland", customer1.getAddress().equals("450 Queen Street, Auckland"));
			
	}

	// Test setPostcode method 
	@Test
	void test_setPostcode() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		customer1.setPostcode("1010");			
		assertTrue("getPostcode must be 1010", customer1.getPostcode().equals("1010"));
			
	}

	// Test setDOB method 
	@Test
	void test_setDOB() {
		/** Set date format for New Zealand */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		/** object for customer to add customer data */
		Customer customer1 = new Customer(100,"Alison", "Strange","alison.strange@adjunct.openpolytechnic.ac.nz","022452178","3 Tristram Street, Hamilton","3200",LocalDate.parse("10/11/1980", formatter));
		customer1.setDOB(LocalDate.parse("05/08/1995", formatter));			
		assertTrue("getDOB must be 05/08/1995", customer1.getDOB().format(formatter).equals("05/08/1995"));
				
	}
	
}

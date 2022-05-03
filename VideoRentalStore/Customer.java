package VideoRentalStore;

/**
 * This is a class for adding and getting customer information.
 *  
 * @author Kazuhisa Kondo
 * @version 1.0, 2 May 2022
 *
 */
public class Customer {
	/** first name for customer */
	private String firstName;
	
	/** last name  for customer */
	private String lastName;
	
	/** email  for customer */
	private String email;
	
	/** phone number  for customer */
	private String phone;
	
	/** address  for customer */
	private String address;
	
	/** post code  for customer */
	private String postcode;
	
	/**
     * This is the constructor.
	 *
	 *@param firstName the first name for  customer
	 *@param lastName the last name for customer
	 *@param email the email for customer
	 *@param phone the phone number for customer
	 *@param address the address for customer
	 *@param postcode the post code for customer
     */
	public Customer( String firstName, String lastName, String email, String phone, String address, String postcode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.postcode = postcode;
		
	}
	
	
	/**
     * Set the first name.
     * @param _firstName  This is the parameter first name of customer.
     */
	public void setFirstName(String _firstName) {
		firstName = _firstName;
	}
	
	/**
     * Set the last name.
     * @param _lastName  This is the parameter last name of customer.
     */
	public void setLastName(String _lastName) {
		lastName = _lastName;
	}
	
	/**
     * Set the email.
     * @param _email  This is the parameter email of customer.
     */
	public void setEmail(String _email) {
		email = _email;
	}
	
	/**
     * Set the phone number.
     * @param _phone  This is the parameter phone number of customer.
     */
	public void setPhone(String _phone) {
		phone = _phone;
	}
	
	/**
     * Set the address.
     * @param _address  This is the parameter address of customer.
     */
	public void setAddress(String _address) {
		address = _address;
	}
	
	/**
     * Set the post code.
     * @param _postcode  This is the parameter post code of customer.
     */
	public void setPostcode(String _postcode) {
		postcode = _postcode;
	}
	
	
	/**
     * Get the first name of the customer.
     * @return String first name.
     */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Get the last name of the customer
	 * @return String last name.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Get the email of the customer
	 * @return String email.
	 */
	
	public String getEmail() {
		return email;
	}
	
	/**
	 * Get the phone number of the customer
	 * @return String phone number.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Get the address of the customer
	 * @return String address.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Get the post code of the customer
	 * @return String post code.
	 */
	public String getPostcode() {
		return postcode;
	}
	
	/**
	 * Get the all stored data of the customer
	 * @return String all data.
	 */
	public String toString() {
		String s;
		s = "FirstName:"+ firstName + " LastName:"+ lastName;
		s = s + " email:" + email + " Phone:" + phone;
		s = s + " address:" + address + " postcode:" + postcode;
		return s;
	}
}

package VideoRentalStore;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *  This is class using interface class: rental.
 * 
 */
class rentalVideo implements rental {
	 
	/** video title */
	protected String title;
	
	/** video is rented true otherwise false */
	protected boolean isRented;
	
	/** rent date */
	protected LocalDate rentDate;
	
	/** media type */
	protected String media;
	
	/** who is renting */
	protected String renter;

	/**
	 *  This is constructor for rentalVideo
	 */
	 public rentalVideo() {}
	 public rentalVideo(String title, String media) {
		this.title = title;
		this.media = media;
		this.isRented = false;
	 }
	
	public String get_title() {
			return title;
	}
	  
	public boolean get_isRented() {
		return isRented;
	}
	
	 public boolean isOverdue(LocalDate today) {
		 if(this.rentDate == null) return false;
		 return (today.isAfter(this.rentDate.plusDays(5)));
		 
	 }
	 public int calculateFine(LocalDate today) {
		 if(!isOverdue(today)) return 0;
		 
		 long duration = ChronoUnit.DAYS.between(this.rentDate, today)-5;
		 return (int)duration * 1 ;
	 }
	 
	 public void rent(Customer p, LocalDate today) {
		 this.renter = p.getFirstName();
		 this.isRented = true;
		 this.rentDate = today;
	 }
	 
	 public  void returnRental() {
		 this.isRented = false;
		 this.renter = null;
		 this.rentDate = null;
	 } 
	 
	 public String toString() {
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   String strRentDate;
		   try {
		   strRentDate = rentDate.format(formatter);
		   }catch (Exception e) {strRentDate = null;}
		   String s;
			s = "title:"+ title  + " media:" + media + " renter:" + renter+ " rentDate:" + strRentDate;
			return s;
		}
}




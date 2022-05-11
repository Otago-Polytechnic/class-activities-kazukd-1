package VideoRentalStore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


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
	protected Customer renter;

	/**
	 *  This is constructor for rentalVideo
	 */
	 public rentalVideo() {}
	 public rentalVideo(String title, String media) {
		this.title = title;
		this.media = media;
		this.isRented = false;
	 }
	 
	 public rentalVideo(String title, String media, boolean isRented, Customer renter,LocalDate rentDate) {
			this.title = title;
			this.media = media;
			this.isRented = isRented;
			this.renter = renter;
			this.rentDate = rentDate;
	 }
	
	public String get_title() {
			return title;
	}
	  
	public boolean get_isRented() {
		return isRented;
	}
	
	public int get_renterId() {
		
		 try {
			 return renter.getCustomerId();
		   }
		   catch (Exception e) {
			   return 0;
		   }
		
		
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
		 this.renter = p;
		 this.isRented = true;
		 this.rentDate = today;
	 }
	 
	 public  void returnRental() {
		 this.isRented = false;
		 this.renter = null;
		 this.rentDate = null;
	 } 
	 
	 public String toString() {
		   // set date format for New Zealand
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   String strRentDate;
		   String strRenter;
		   
		   try {
			   strRentDate = rentDate.format(formatter);
		   }
		   catch (Exception e) {
			   strRentDate = null;
		   }
		   
		   try {
			   strRenter = renter.getCustomerId()+ " " + renter.getFirstName() + " "+ renter.getLastName();
		   }
		   catch (Exception e) {
			   strRenter = null;
		   }
		   
			return "title:"+ title  + " media:" + media + " renter:" + strRenter + " rentDate:" + strRentDate;
			
		}
	 
	 public String toLine() {
		   // set date format for New Zealand
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   String strRentDate;
		   String strRenter;
		   
		   try {
			   strRentDate = rentDate.format(formatter);
		   }
		   catch (Exception e) {
			   strRentDate = "null";
		   }
		   
		   try {
			   strRenter = Integer.toString(renter.getCustomerId());
		   }
		   catch (Exception e) {
			   strRenter = null;
		   }
		   
			return title  + ";"+ media + ";" + isRented +";" + strRenter + ";" + strRentDate + "\n";
			
		}
 
}




package VideoRentalStore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class newRelease extends rentalVideo {
	public newRelease(String title, String media) {
		super(title,media);
		
	}
	
	public boolean isOverdue(LocalDate today) {
		 if(this.rentDate == null) return false;
		 return (today.isAfter(this.rentDate.plusDays(2)));
		 
	 }
	
	public int calculateFine(LocalDate today) {
		 if(!isOverdue(today)) return 0;
		 
		 long duration = ChronoUnit.DAYS.between(super.rentDate, today)-2;
		 return (int)duration * 2 ;
	 }
}

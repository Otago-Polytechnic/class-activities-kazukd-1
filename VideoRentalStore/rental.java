package VideoRentalStore;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
/**
 * This is interface class for rental
 * Checking overdue, calculating fine, registering who and when rents,  
 * 
 * @author Kazuhisa Kondo
 * @version 1.0, 04 May 2022
 */
public interface  rental
{ 
 public abstract boolean isOverdue(LocalDate today);
 public abstract int calculateFine(LocalDate today);
 public abstract void rent(Customer p, LocalDate today);
 public abstract void returnRental();
   
  
}





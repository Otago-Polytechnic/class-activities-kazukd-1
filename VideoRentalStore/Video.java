package VideoRentalStore;


/**
 * This is a class for adding and getting video information.
 *  
 * @author Kazuhisa Kondo
 * @version 1.0, 3 May 2022
 *
 */

public class Video {
	/** title for video */
	private String title;
	
	/** genre for video */
	private String genre;
	
	/** release date for video */
	private int releaseYear;
	
	/** length for video */
	private int length;
	
	/** rental fee for video */
	private double rentalFee;
	
	/** rented date for video */
	private String rentedDate;
	
	/** due date for video */
	private String dueDate;
	
	/** returned date  for video */
	private String returnedDate;
	
	/** customer id  for video */
	private int customerId;
	
	/** penaltyFee  for video */
	private double penaltyFee;
	
	
	/**
     * This is the constructor.
	 *
	 *@param title the title for  video
	 *@param genre the genre for video
	 *@param releaseYear the release year for video
	 *@param length the length (minutes) for video
	 *@param rentalFee the rental fee for video
	 *@param rentedDate the rented Date for video
	 *@param dueDate the due date for video
	 *@param returnedDate the returned date for video.  It may null until customer returns video.
	 *@param customerId the customer id for video
	 *@param penaltyFee the penalty Fee for video
     */
	public Video( String title, String genre, int releaseYear, int length, double rentalFee, String rentedDate, String dueDate, String returnedDate, int customerId,double penaltyFee) {
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.length = length;
		this.rentalFee = rentalFee;
		this.rentedDate = rentedDate;
		this.dueDate = dueDate;
		this.returnedDate = returnedDate;
		this.customerId = customerId;
		this.penaltyFee = penaltyFee;
		
	}
	
	/**
     * Set the title.
     * @param _title  This is the parameter title of video.
     */
	public void setTitle(String _title) {
		title = _title;
	}
	
	/**
     * Set the genre.
     * @param _genre  This is the parameter genre of video.
     */
	public void setGenre(String _genre) {
		genre = _genre;
	}
	
	
	/**
     * Set the releaseYear.
     * @param _releaseYear  This is the parameter releaseYear of video.
     */
	public void setReleaseYear(int _releaseYear) {
		releaseYear = _releaseYear;
	}
	
	/**
     * Set the length.
     * @param _length  This is the parameter length(minutes) of video.
     */
	public void setLength(int _length) {
		length = _length;
	}
	
	
	/**
     * Set the RentalFee.
     * @param _rentalFee  This is the parameter rental fee of video.
     */
	public void setRentalFee(double _rentalFee) {
		rentalFee = _rentalFee;
	}
	
	/**
     * Set the rented Date.
     * @param _rentedDate  This is the parameter rentedDate of video.
     */
	public void setRentedDate(String _rentedDate) {
		rentedDate = _rentedDate;
	}
	
	/**
     * Set the due Date.
     * @param _dueDate  This is the parameter due Date of video.
     */
	public void setDueDate(String _dueDate) {
		dueDate = _dueDate;
	}
	
	/**
     * Set the returned Date.
     * @param _returnedDate  This is the parameter returned Date of video.
     */
	public void setReturnedDate(String _returnedDate) {
		returnedDate = _returnedDate;
	}
	
	/**
     * Set the customer Id who is renting video.
     * @param _customerId  This is the parameter customer id of video.
     */
	public void setCustomerId(int _customerId) {
		customerId = _customerId;
	}
	
	/**
     * Set the penalty Fee.
     * @param _penaltyFee  This is the parameter penalty Fee of video.
     */
	public void setPenaltyFee(double _penaltyFee) {
		penaltyFee = _penaltyFee;
	}
	
	
	/**
     * Get the title  of the video.
     * @return String title.
     */
	public String getTitle() {
		return title;
	}
	
	/**
     * Get the genre  of the video.
     * @return String genre.
     */
	public String getGenre() {
		return genre;
	}
	
	/**
     * Get the releaseYear  of the video.
     * @return int releaseYear.
     */
	public int getReleaseYear() {
		return releaseYear;
	}
	
	/**
     * Get the length  of the video.
     * @return double length.
     */
	public int getLength() {
		return length;
	}
	
	/**
	 * Get the rental Fee  of the video
	 * @return double rentalFee .
	 */
	public double getRentalFee () {
		return rentalFee ;
	}
	
	/**
	 * Get the rentedDate  of the video
	 * @return String rentedDate.
	 */
	
	public String getRentedDate () {
		return rentedDate;
	}
	
	/**
	 * Get the due date  of the video
	 * @return String dueDate 
	 */
	public String getDueDate() {
		return dueDate;
	}
	
	/**
	 * Get the returnedDate  of the video
	 * @return String returnedDate 
	 */
	public String getReturnedDate() {
		return returnedDate;
	}
	
	/**
	 * Get the customerId of the video
	 * @return int customerId.
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	/**
	 * Get the penaltyFee of the video
	 * @return double penaltyFee.
	 */
	public double getPenaltyFee() {
		return penaltyFee;
	}
	
	/**
	 * Get the all stored data of the video
	 * @return String all video's data.
	 */
	public String toString() {
		String s;
		s = "title:"+ title  + " genre:" + genre+ " release year:"+ releaseYear+ " length:"+ length;
		s = s +  "rentalFee:$"+ rentalFee + " rentedDate:" + rentedDate + " dueDate:" + dueDate;
		s = s + " returnedDate:" + returnedDate + " customerId:" + customerId + " penaltyFee:$" + penaltyFee ;
		return s;
	}
}

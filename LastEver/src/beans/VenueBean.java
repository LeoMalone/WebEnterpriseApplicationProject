package beans;

/**
 * The VenueBean class is meant for passing venue information
 * between DAOs and Servlets
 */
public class VenueBean {
	
	//Venue bean parameters
	private String venueID;
	private String venueName;
	private String venuePicture;
	private String address1;
	private String address2;
	private String city;
	private String postal;
	private String province;
	private String country;
	private String venueAddress;
	private String venueAbout;
	private String venueContact;
	private String venuePhoneNumber;
	private String venueEmail;
	
/**************************** CONTRUCTORS *****************************/
	public VenueBean() {}
	
/**************************** GETTERS *****************************/
	public String getVenueID() {
		return this.venueID;
	}
	
	public String getVenueName() {
		return this.venueName;
	}
	
	public String getVenuePicture() {
		return this.venuePicture;
	}
	
	public String getAddress1() {
		return this.address1;
	}
	
	public String getAddress2() {
		return this.address2;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getProvince() {
		return this.province;
	}
	
	public String getPostal() {
		return this.postal;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getVenueAddress() {
		return this.venueAddress;
	}
	
	public String getVenueAbout() {
		return this.venueAbout;
	}
	
	public String getVenueContact() {
		return this.venueContact;
	}
	
	public String getVenueEmail() {
		return this.venueEmail;
	}
	
	public String getVenuePhoneNumber() {
		return this.venuePhoneNumber;
	}

/**************************** SETTERS *****************************/
	public void setVenueID(String vID) {
		this.venueID = vID;
	}
	
	public void setVenueName(String venue) {
		this.venueName = venue;
	}
	
	public void setVenuePicture(String pict) {
		this.venuePicture = pict;
	}
	
	public void setAddress1(String add1) {
		this.address1 = add1;
	}
	
	public void setAddress2(String add2) {
		this.address2 = add2;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public void setPostal(String postal) {
		this.postal = postal;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setVenueAddress(String add1, String add2, String city, String prov, String postal, String country) {
		this.venueAddress = add1 + " " + add2 + " " + city + " " + prov + " " + postal + " " + country;
	}
	
	public void setVenueAbout(String ab) {
		this.venueAbout = ab;
	}
	
	public void setVenueContact(String con) {
		this.venueContact = con;
	}
	
	public void setVenueEmail(String em) {
		this.venueEmail = em;
	}
	
	public void setVenuePhoneNumber(String pn) {
		this.venuePhoneNumber = pn;
	}
}

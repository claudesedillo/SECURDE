package beans;

public class Customer {
	private String email;
	private String hashedpassword;
	private String firstname;
	private String lastname;
	private String securityquestion;
	private String securityanswer;
	private String streetaddress;
	private int postalcode;
	private String city;
	private String province;
	private String phonenumber;
	
	public Customer() {}

	public Customer(String email, String hashedpassword, String firstname, String lastname, String securityquestion,
			String securityanswer, String streetaddress, int postalcode, String city, String province,
			String phonenumber) {
		this.email = email;
		this.hashedpassword = hashedpassword;
		this.firstname = firstname;
		this.lastname = lastname;
		this.securityquestion = securityquestion;
		this.securityanswer = securityanswer;
		this.streetaddress = streetaddress;
		this.postalcode = postalcode;
		this.city = city;
		this.province = province;
		this.phonenumber = phonenumber;
	}

	public Customer(String email, String firstname, String lastname, String streetaddress, String city, String province, 
			String phonenumber, int postalcode) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.postalcode = postalcode;
		this.city = city;
		this.province = province;
		this.phonenumber = phonenumber;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedpassword() {
		return hashedpassword;
	}

	public void setHashedpassword(String hashedpassword) {
		this.hashedpassword = hashedpassword;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSecurityquestion() {
		return securityquestion;
	}

	public void setSecurityquestion(String securityquestion) {
		this.securityquestion = securityquestion;
	}

	public String getSecurityanswer() {
		return securityanswer;
	}

	public void setSecurityanswer(String securityanswer) {
		this.securityanswer = securityanswer;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}

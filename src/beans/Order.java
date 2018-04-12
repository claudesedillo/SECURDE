package beans;

import java.sql.Date;

public class Order {

	private int orderID;
	private String email;
	private java.sql.Date orderDate;
	private int total;
	private java.sql.Date dateCompleted;
	private String streetAddress;
	private String city;
	private String province;
	private int postalCode;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	
	public Order(int orderID, String email, Date orderDate, int total, String streetAddress, String city, String province,
			int postalCode, String phoneNumber, String firstName, String lastName) {

		this.orderID = orderID;
		this.email = email;
		this.orderDate = orderDate;
		this.total = total;
		this.streetAddress = streetAddress;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Order(int orderID, String email, String firstName, String lastName, String streetAddress, String city, String province,
			int postalcode, String phoneNumber, int total) {
		
		this.orderID = orderID;
		this.email = email;
		this.total = total;
		this.streetAddress = streetAddress;
		this.city = city;
		this.province = province;
		this.postalCode = postalcode;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Order(String email, String firstName, String lastname, String address, String city, int total) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastname;
		this.streetAddress = address;
		this.city = city;
		this.total = total;
	}
	public Order(String email, Date orderDate, int total, String streetAddress, String city, String province,
			int postalCode, String phoneNumber) {

		this.email = email;
		this.orderDate = orderDate;
		this.total = total;
		this.streetAddress = streetAddress;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
	}

	public Order(String email, String firstName, String lastName,  String streetAddress, 
			String city, String province, int postalCode, String phoneNumber, int total) {

		this.email = email;
		this.total = total;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
	}
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public java.sql.Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(java.sql.Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "orderID=" + orderID + ", email=" + email + ", orderDate=" + orderDate + ", total=" + total
				+ ", streetAddress=" + streetAddress + ", city=" + city
				+ ", province=" + province + ", postalCode=" + postalCode + ", phoneNumber=" + phoneNumber
				+ ", firstName=" + firstName + ", lastName=" + lastName;
	}
}

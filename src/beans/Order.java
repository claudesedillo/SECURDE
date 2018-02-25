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
	
	public Order(int orderID, String email, Date orderDate, int total, String streetAddress, String city, String province,
			int postalCode, String phoneNumber) {

		this.orderID = orderID;
		this.email = email;
		this.orderDate = orderDate;
		this.total = total;
		this.streetAddress = streetAddress;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
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
	

}

package beans;

public class Shoppingcart {
	int bookid;
	float price;
	String email;
	int quantity;
	
	public Shoppingcart(){}
	
	public Shoppingcart(int bookid, float price, String email, int quantity) {
		this.bookid = bookid;
		this.price = price;
		this.email = email;
		this.quantity = quantity;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

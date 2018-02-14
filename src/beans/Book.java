package beans;

public class Book {
	private int bookID;
	private String title;
	private String isbn;
	private String genre;
	private String format;
	private float price;
	private int stock;
	private java.sql.Date tempDate;
	private int authorID;
	private int publisherID;
	
	public Book() {}
	
	public Book(String title, String isbn, String genre, String format, float price, int stock, java.sql.Date published) {
		this.title = title;
		this.isbn = isbn;
		this.genre = genre;
		this.format = format;
		this.price = price;
		this.stock = stock;
		tempDate = published;
	}
	
	public Book(String title, String isbn, String genre, String format, float price, int stock) {
		this.title = title;
		this.isbn = isbn;
		this.genre = genre;
		this.format = format;
		this.price = price;
		this.stock = stock;;
	}
	
	public java.sql.Date getSQLDate() {
		return tempDate;
	}

	public void setSQLDate(String publishedDate) {
		this.tempDate = java.sql.Date.valueOf(publishedDate);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", title=" + title + ", isbn=" + isbn + ", genre=" + genre + ", format="
				+ format + ", price=" + price + ", stock=" + stock + ", tempDate=" + tempDate + ", authorID=" + authorID
				+ ", publisherID=" + publisherID + "]";
	}

}

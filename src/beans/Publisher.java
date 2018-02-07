package beans;

public class Publisher {

	private int publisherId;
	private String publisherName;
	
	public Publisher() {}
	
	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
}

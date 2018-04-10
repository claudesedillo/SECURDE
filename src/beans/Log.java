package beans;

import java.sql.Date;
import java.sql.Timestamp;

public class Log {
	private String ip_address;
	private java.sql.Timestamp attempt_date;
	private boolean successful;
	private String email;
	private String type;

	public Log(){}

	public Log(String ip_address, Timestamp attempt_date, boolean successful, String email, String type) {
		super();
		this.ip_address = ip_address;
		this.attempt_date = attempt_date;
		this.successful = successful;
		this.email = email;
		this.type = type;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public java.sql.Timestamp getAttempt_date() {
		return attempt_date;
	}

	public void setAttempt_date(java.sql.Timestamp attempt_date) {
		this.attempt_date = attempt_date;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

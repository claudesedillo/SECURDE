package beans;

import java.sql.Date;
import java.sql.Timestamp;

public class LoginAttempt {
	private String ip_address;
	private java.sql.Timestamp attempt_date;
	private int attempt_count;
	
	public LoginAttempt(){}
	
	public LoginAttempt(String ip_address, Timestamp attempt_date, int attempt_count) {
		super();
		this.ip_address = ip_address;
		this.attempt_date = attempt_date;
		this.attempt_count = attempt_count;
	}
	
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public Timestamp getAttempt_date() {
		return attempt_date;
	}
	public void setAttempt_date(Timestamp attempt_date) {
		this.attempt_date = attempt_date;
	}
	public int getAttempt_count() {
		return attempt_count;
	}
	public void setAttempt_count(int attempt_count) {
		this.attempt_count = attempt_count;
	}
	
	
	
}

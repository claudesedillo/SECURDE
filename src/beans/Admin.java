package beans;

public class Admin {
	private String email;
	private String hashedpassword;
	private String firstname;
	private String lastname;
	private String role;
	
	public Admin(){}

	public Admin(String email, String hashedpassword, String firstname, String lastname, String role) {
		this.email = email;
		this.hashedpassword = hashedpassword;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

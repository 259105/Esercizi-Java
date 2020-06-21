package diet;

/**
 * Represent a take-away system user
 *  
 */
public class User  {
	private String lastname,firstname,email,phone;
	
	public User(String firstname, String lastname, String email, String phone) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * get user's last name
	 * @return last name
	 */
	public String getLastName() {
		return lastname;
	}
	
	/**
	 * get user's first name
	 * @return first name
	 */
	public String getFirstName() {
		return firstname;
	}
	
	/**
	 * get user's email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * get user's phone number
	 * @return  phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * change user's email
	 * @param email new email
	 */
	public void SetEmail(String email) {
		this.email=email;
	}
	
	/**
	 * change user's phone number
	 * @param phone new phone number
	 */
	public void setPhone(String phone) {
		this.phone=phone;
	}

	@Override
	public String toString() {
		return firstname+" "+lastname;
	}
	
}

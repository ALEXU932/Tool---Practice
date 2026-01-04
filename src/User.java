

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class User {

	public Address address;
	protected String email;
	private String passwordHash;
	private String phoneNumber;
	private Permission role;
	private String userId;
	public String userName;

	public User(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param email
	 * @param password
	 */
	public boolean login(String email, String password){
		return false;
	}

	public void updateProfile(){

	}

	// Getters and Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Example usage
	public static void main(String[] args) {
		User user = new User();
		user.setUserName("John Doe");
		user.setEmail("john@example.com");

		System.out.println("User Name: " + user.getUserName());
		System.out.println("Email: " + user.getEmail());
	}
}//end User
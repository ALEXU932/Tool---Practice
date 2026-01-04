

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:29 PM
 */
public class User {

	public Address address;
	protected string email;
	private string passwordHash;
	private string phoneNumber;
	private Permission role;
	private string userId;
	public string userName;

	public User(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param email
	 * @param password
	 */
	public boolean login(String email, string password){
		return false;
	}

	/**
	 * 
	 * @param passw
	 */
	public void setPassword(String passw){

	}

	public void updateProfile(){

	}
}//end User
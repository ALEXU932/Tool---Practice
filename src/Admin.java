

import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:08 PM
 */
public class Admin extends User {

	private int adminId;
	private String permission;

	public Admin(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * 
	 * @param type
	 */
	public String generateReport(String type){
		return "";
	}

	public List<String> manageSystemLog(){
		return null;
	}

	/**
	 * 
	 * @param userId
	 * @param action
	 */
	public void manageUser(String userId, String action){

	}

	/**
	 * 
	 * @param settings
	 */
	public void updateSystemSettings(String settings){

	}

	public void viewProduct(){

	}
}//end Admin
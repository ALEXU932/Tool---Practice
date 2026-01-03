

import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class Inventory {

	private int inventoryId;
	private Date lastRestoked;
	private String productId;
	public int quantity;

	public Inventory(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param imageUrl
	 */
	public void addImage(String imageUrl){

	}

	/**
	 * 
	 * @param productId
	 */
	public int checkStockLevel(String productId){
		return 0;
	}

	public Product getProductInfo(){
		return null;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void updateStock(int quantity){

	}

	/**
	 * 
	 * @param productId
	 * @param quantity
	 */
	public void updateStock(String productId, int quantity){

	}
}//end Inventory
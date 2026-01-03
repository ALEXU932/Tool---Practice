

import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class Seller extends User {

	public Date joinDate;
	private String sellerId;
	public String storeName;
	public Product m_Product;

	public Seller(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * 
	 * @param product
	 */
	public void addProduct(Product product){

	}

	/**
	 * 
	 * @param productId
	 * @param quantity
	 */
	public void updateInventory(String productId, int quantity){

	}

	/**
	 * 
	 * @param productId
	 * @param updates
	 */
	public void updateProduct(String productId, String updates){

	}

	public void viewProduct(){

	}
}//end Seller
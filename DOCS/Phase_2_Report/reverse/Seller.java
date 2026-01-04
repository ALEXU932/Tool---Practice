/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:29 PM
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
	
	// Getter and Setter methods
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Product getM_Product() {
		return m_Product;
	}

	public void setM_Product(Product m_Product) {
		this.m_Product = m_Product;
	}
}//end Seller
// Customer.java
import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class Customer extends User {

	public Address billingAddress;
	private String customerId;
	public Language preferencedLanguage;
	public Address shippingAddress;
	public Cart m_Cart;
	public Order m_Order;

	public Customer(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * 
	 * @param productId
	 * @param quantity
	 */
	public void addTocart(String productId, int quantity){

	}

	/**
	 * 
	 * @param category
	 */
	public List<Product> browseProduct(String category){
		return null;
	}

	/**
	 * 
	 * @param cartId
	 */
	public Order placeOrder(String cartId){
		return null;
	}

	/**
	 * 
	 * @param item
	 * @param price
	 * @param quantity
	 */
	public List<Order> viewOrderHistory(String item, float price, int quantity){
		return null;
	}

	public void viewProduct(){

	}
	
	// Getter and Setter methods
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Language getPreferencedLanguage() {
		return preferencedLanguage;
	}

	public void setPreferencedLanguage(Language preferencedLanguage) {
		this.preferencedLanguage = preferencedLanguage;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}//end Customer
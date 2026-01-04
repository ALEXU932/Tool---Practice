// Cart.java
import java.util.Date;
import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class Cart {

	private String cartId;
	Date createdAt;
	private List<CartItem> items;
	private String userId;
	public CartItem m_CartItem;
	public Product m_Product;

	public Cart(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param productId
	 * @param quantity
	 */
	public void addproduct(String productId, String quantity){

	}

	public float calaculateTotal(){
		return 0.0f;
	}

	public List<CartItem> getItems(){
		return items;
	}

	/**
	 * 
	 * @param productId
	 */
	public void removeitem(String productId){

	}
	
	// Getter and Setter methods
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}//end Cart
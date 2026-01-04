// CartItem.java
/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class CartItem {

	private String cartId;
	private String cartItemId;
	private float priceAtAddition;
	private String productId;
	private int quantity;

	public CartItem(){

	}

	public void finalize() throws Throwable {

	}
	public float getSubtotal(){
		return priceAtAddition * quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void updateQuantity(int quantity){
		this.quantity = quantity;
	}
	
	// Getter and Setter methods
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public float getPriceAtAddition() {
		return priceAtAddition;
	}

	public void setPriceAtAddition(float priceAtAddition) {
		this.priceAtAddition = priceAtAddition;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}
}//end CartItem
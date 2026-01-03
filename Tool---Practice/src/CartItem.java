

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:08 PM
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
		return 0.0f;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void updateQuantity(int quantity){

	}
}//end CartItem
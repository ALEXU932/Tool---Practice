

import java.util.Date;
import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:08 PM
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
		return null;
	}

	/**
	 * 
	 * @param productId
	 */
	public void removeitem(String productId){

	}
}//end Cart
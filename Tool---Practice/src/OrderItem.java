

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class OrderItem {

	private String orderId;
	private String orderItemId;
	private String productId;
	private int quantity;
	private float subtotal;

	public OrderItem(){

	}

	public void finalize() throws Throwable {

	}
	public float calculateSubtotal(){
		return 0.0f;
	}

	public Order getOrderDetails(){
		return null;
	}

	public Product getProductInfo(){
		return null;
	}
}//end OrderItem


import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class Order {

	private Address billibngAddress;
	public Date orderDate;
	private String orderId;
	private paymentMethod paymentMethod;
	public Address shippingAddress;
	private String userId;
	public OrderItem m_OrderItem;
	public Payment m_Payment;

	public Order(){

	}

	public void finalize() throws Throwable {

	}
	public void cancelOrder(){

	}

	public Order getOrderDetails(){
		return null;
	}

	/**
	 * 
	 * @param cartId
	 * @param shippingInfo
	 */
	public Order placeOrder(String cartId, String shippingInfo){
		return null;
	}

	/**
	 * 
	 * @param newStatus
	 */
	public void updateStatus(String newStatus){

	}
}//end Order
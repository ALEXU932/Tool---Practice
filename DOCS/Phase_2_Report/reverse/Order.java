import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class Order {

	private Address billibngAddress;
	public Date orderDate;
	private String orderId;
	private PaymentMethod paymentMethod;
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
		return this;
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
	
	// Getter and Setter methods
	public Address getBillibngAddress() {
		return billibngAddress;
	}

	public void setBillibngAddress(Address billibngAddress) {
		this.billibngAddress = billibngAddress;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	
	
}//end Order
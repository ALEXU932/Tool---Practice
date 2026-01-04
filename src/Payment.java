

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class Payment {

	private String gatewayName;
	private String orderId;
	private String paymentId;
	private paymentMethod paymentMethod;
	private String transactionId;
	private String userId;
	public PaymentGateway m_PaymentGateway;
	public Transaction m_Transaction;

	public Payment(){

	}

	public void finalize() throws Throwable {

	}
	public Order getOrderDetails(){
		return null;
	}

	/**
	 * 
	 * @param userId
	 */
	public void getPaymentHistory(String userId){

	}

	/**
	 * 
	 * @param orderId
	 * @param paymentDetails
	 */
	public void processPayment(String orderId, String paymentDetails){

	}

	/**
	 * 
	 * @param paymentId
	 * @param amount
	 */
	public void refundPayment(String paymentId, float amount){

	}

	/**
	 * 
	 * @param newStatus
	 */
	public void updateStatus(String newStatus){

	}

	/**
	 * 
	 * @param transactionId
	 */
	public void verifyTransaction(String transactionId){

	}
}//end Payment
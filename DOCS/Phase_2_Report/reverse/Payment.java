// Payment.java
/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class Payment {

	private float amount;
	private String gatewayName;
	private String orderId;
	private String paymentId;
	private PaymentMethod paymentMethod;
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
	
	// Getter and Setter methods
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}//end Payment
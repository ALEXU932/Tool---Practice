import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:29 PM
 */
public class Transaction {

	private float amount;
	private String gatewayResponse;
	private String orderId;
	private PaymentMethod paymentMethod;
	private Date transactionDate;
	private String transactionId;

	public Transaction(){

	}

	public void finalize() throws Throwable {

	}
	public TransactionStatus getStatus(){
		return null;
	}

	/**
	 * 
	 * @param amount
	 * @param method
	 */
	public void initiateStatus(float amount, PaymentMethod method){
		this.amount = amount;
		this.paymentMethod = method;
	}
	
	// Getter and Setter methods
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getGatewayResponse() {
		return gatewayResponse;
	}

	public void setGatewayResponse(String gatewayResponse) {
		this.gatewayResponse = gatewayResponse;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}//end Transaction
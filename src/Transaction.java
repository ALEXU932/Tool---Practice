

import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class Transaction {

	private float amount;
	private String gatewayResponse;
	private String orderId;
	private paymentMethod paymentMethod;
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
	public void initiateStatus(float amount, paymentMethod method){

	}
}//end Transaction
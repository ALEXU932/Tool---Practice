
/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public interface PaymentProcessor {

	/**
	 * 
	 * @param amount
	 */
	Transaction processPayment(float amount);

	/**
	 * 
	 * @param transactionId
	 */
	boolean refund(String transactionId);
	
}//end PaymentProcessor
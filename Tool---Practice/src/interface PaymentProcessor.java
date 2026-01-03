

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:08 PM
 */
public interface PaymentProcessor {

	/**
	 * 
	 * @param amount
	 */
	Transaction processpayment(float amount);

	/**
	 * 
	 * @param transactionId
	 */
	boolean refund(String transactionId);
}
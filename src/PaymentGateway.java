

import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class PaymentGateway implements PaymentProcessor {

	private String apiKey;
	private String gatewayId;
	private String name;
	private String secretKey;
	private List<String> supportedCurrencies;
	private float transactionFee;

	public PaymentGateway(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param transactionId
	 */
	public TransactionStatus                                          getTransactionStatus(String transactionId){
		return null;
	}

	/**
	 * 
	 * @param amount
	 * @param currency
	 */
	public PaymentResponse                        processPayment(float amount, float currency){
		return null;
	}

	/**
	 * 
	 * @param transactionId
	 */
	public boolean refund(String transactionId){
		return false;
	}

	/**
	 * 
	 * @param transactionId
	 * @param amount
	 */
	public void refundPayment(String transactionId, float amount){

	}

	/**
	 * 
	 * @param cardNumber
	 * @param cvv
	 */
	public void validateCard(String cardNumber, String cvv){

	}
}//end PaymentGateway

// PaymentGateway.java
import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
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
	public TransactionStatus getTransactionStatus(String transactionId){
		return null;
	}

	/**
	 * 
	 * @param amount
	 * @param currency
	 */
	public PaymentResponse processPayment(float amount, float currency){
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
	
	// Getter and Setter methods
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public List<String> getSupportedCurrencies() {
		return supportedCurrencies;
	}

	public void setSupportedCurrencies(List<String> supportedCurrencies) {
		this.supportedCurrencies = supportedCurrencies;
	}

	public float getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(float transactionFee) {
		this.transactionFee = transactionFee;
	}
}//end PaymentGateway
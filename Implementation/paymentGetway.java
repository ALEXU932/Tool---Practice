import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class PaymentGateway {
    private String apikey;
    private String gatewayid;
    private String name;
    private String secretkey;
    private List<String> supportedcurrencies;
    private float transactionfree;
    
    public PaymentGateway() {
        this.supportedcurrencies = new ArrayList<>(Arrays.asList("USD", "EUR", "GBP"));
        this.transactionfree = 0.029f; 
    }
  
    public void Router() {
        System.out.println("Routing payment through gateway: " + name);
    }
    
    public String getTransactionStatus(String transactionId) {
        System.out.println("Getting transaction status for: " + transactionId);
        return "COMPLETED"; 
    }
    
    public PaymentResponse processPayment(float amount, String currency) {
        System.out.println("Processing payment of " + amount + " " + currency);
        String transactionId = "TXN_" + System.currentTimeMillis();
        return new PaymentResponse(true, "Payment successful", transactionId);
    }
    
    public boolean refundString() {
        System.out.println("Refunding payment");
        return true; 
    }
    
    public void returnPayment(String transactionId, float amount) {
        System.out.println("Returning payment for transaction " + transactionId + ", amount: " + amount);
    }
    
    public void validateCard(String cardNumber, String expiryDate) {
        System.out.println("Validating card: " + maskCardNumber(cardNumber));
    }
 
    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) return "****";
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
    public String getApikey() {
        return apikey;
    }
    
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
    
    public String getGatewayid() {
        return gatewayid;
    }
    
    public void setGatewayid(String gatewayid) {
        this.gatewayid = gatewayid;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSecretkey() {
        return secretkey;
    }
    
    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
    
    public List<String> getSupportedcurrencies() {
        return supportedcurrencies;
    }
    
    public void setSupportedcurrencies(List<String> supportedcurrencies) {
        this.supportedcurrencies = supportedcurrencies;
    }
    
    public float getTransactionfree() {
        return transactionfree;
    }
    
    public void setTransactionfree(float transactionfree) {
        this.transactionfree = transactionfree;
    }
}
class PaymentResponse {
    private boolean success;
    private String message;
    private String transactionId;
    
    public PaymentResponse(boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
    }
    
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getTransactionId() { return transactionId; }
    
    @Override
    public String toString() {
        return "PaymentResponse{success=" + success + 
               ", message='" + message + '\'' + 
               ", transactionId='" + transactionId + '\'' + '}';
    }
}
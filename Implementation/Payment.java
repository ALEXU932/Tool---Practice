import java.util.*;

public class Payment {

    private String gatewayName;
    private PaymentGateway m_PaymentGateway;
    private Transaction m_Transaction;
    private String orderId;
    private String paymentId;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private String userId;

    // Constructor
    public Payment() {
        this.paymentId = "PAY-" + System.currentTimeMillis();
        this.m_PaymentGateway = new PaymentGateway();
    }

    // Process a payment
    public void processPayment(String orderId, String method) {
        this.orderId = orderId;
        this.paymentMethod = PaymentMethod.valueOf(method.toUpperCase());
        this.transactionId = "TXN-" + System.currentTimeMillis();

        float amount = 500.00f; // simulated order amount

        boolean success = m_PaymentGateway.charge(method, amount);

        m_Transaction = new Transaction(transactionId, amount);

        if (success) {
            m_Transaction.status = "SUCCESS";
            System.out.println("Payment successful. Transaction ID: " + transactionId);
        } else {
            m_Transaction.status = "FAILED";
            System.out.println("Payment failed.");
        }
    }

    // Verify transaction
    public void verifyTransaction(String transactionId) {
        if (m_Transaction != null && m_Transaction.transactionId.equals(transactionId)) {
            System.out.println("Transaction verified. Status: " + m_Transaction.status);
        } else {
            System.out.println("Transaction not found.");
        }
    }

    // Refund payment
    public void refundPayment(String transactionId, float amount) {
        boolean refunded = m_PaymentGateway.refund(transactionId, amount);

        if (refunded) {
            updateStatus("REFUNDED");
            System.out.println("Refund successful for transaction: " + transactionId);
        } else {
            System.out.println("Refund failed.");
        }
    }

    // Update payment status
    public void updateStatus(String status) {
        if (m_Transaction != null) {
            m_Transaction.status = status;
        }
    }

    // Get order details (simulated)
    public Order getOrderDetails() {
        return new Order(orderId, new HashMap<>(), 500.00f);
    }

    // Get payment history (simulated)
    public void getPaymentHistory(String userId) {
        System.out.println("Fetching payment history for user: " + userId);
    }

    // Finalize payment process
    public void finalize() {
        System.out.println("Payment finalized for payment ID: " + paymentId);
    }
    public class Main {
    public static void main(String[] args) {

        Payment payment = new Payment();

        payment.processPayment("ORD-12345", "credit_card");
        payment.verifyTransaction("TXN-123");
        payment.getPaymentHistory("USER-1");
        payment.finalize();
    }
}

}

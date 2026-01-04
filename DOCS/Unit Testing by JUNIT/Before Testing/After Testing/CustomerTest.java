import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Customer class
 * Tests customer operations including cart management and order placement
 */
class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId("CUST001");
        
        Address billingAddress = new Address();
        billingAddress.setCity("New York");
        customer.setBillingAddress(billingAddress);
        
        Address shippingAddress = new Address();
        shippingAddress.setCity("Los Angeles");
        customer.setShippingAddress(shippingAddress);
    }

    @Test
    @DisplayName("Test Customer Creation and Properties")
    void testCustomerCreation() {
        assertNotNull(customer, "Customer object should be created");
        assertEquals("CUST001", customer.getCustomerId(), "Customer ID should match");
        assertNotNull(customer.getBillingAddress(), "Billing address should not be null");
        assertNotNull(customer.getShippingAddress(), "Shipping address should not be null");
    }

    @Test
    @DisplayName("Test Customer ID Getter and Setter")
    void testCustomerId() {
        String newId = "CUST002";
        customer.setCustomerId(newId);
        assertEquals(newId, customer.getCustomerId(), "Customer ID should be updated");
    }

    @Test
    @DisplayName("Test Billing Address Management")
    void testBillingAddress() {
        Address newBillingAddress = new Address();
        newBillingAddress.setCity("Chicago");
        customer.setBillingAddress(newBillingAddress);
        
        assertEquals("Chicago", customer.getBillingAddress().getCity(),
            "Billing address city should be updated");
    }

    @Test
    @DisplayName("Test Shipping Address Management")
    void testShippingAddress() {
        Address newShippingAddress = new Address();
        newShippingAddress.setCity("Miami");
        customer.setShippingAddress(newShippingAddress);
        
        assertEquals("Miami", customer.getShippingAddress().getCity(),
            "Shipping address city should be updated");
    }

    @Test
    @DisplayName("Test Add To Cart Operation")
    void testAddToCart() {
        String productId = "PROD001";
        int quantity = 2;
        
        assertDoesNotThrow(() -> customer.addTocart(productId, quantity),
            "addTocart method should not throw exceptions");
    }

    @Test
    @DisplayName("Test Browse Product Operation")
    void testBrowseProduct() {
        String category = "Electronics";
        List<Product> products = customer.browseProduct(category);
        
        assertNull(products, "browseProduct should return null for default implementation");
    }

    @Test
    @DisplayName("Test Place Order Operation")
    void testPlaceOrder() {
        String cartId = "CART001";
        Order order = customer.placeOrder(cartId);
        
        assertNull(order, "placeOrder should return null for default implementation");
    }

    @Test
    @DisplayName("Test View Order History Operation")
    void testViewOrderHistory() {
        List<Order> orders = customer.viewOrderHistory("Item1", 100.0f, 2);
        
        assertNull(orders, "viewOrderHistory should return null for default implementation");
    }

    @Test
    @DisplayName("Test View Product Operation")
    void testViewProduct() {
        assertDoesNotThrow(() -> customer.viewProduct(),
            "viewProduct method should not throw exceptions");
    }

    @Test
    @DisplayName("Test Customer with All Operations")
    void testCustomerWithAllOperations() {
        assertAll("Customer Operations",
            () -> assertEquals("CUST001", customer.getCustomerId()),
            () -> assertNotNull(customer.getBillingAddress()),
            () -> assertNotNull(customer.getShippingAddress()),
            () -> assertDoesNotThrow(() -> customer.addTocart("PROD001", 1)),
            () -> assertDoesNotThrow(() -> customer.viewProduct())
        );
    }

    @Test
    @DisplayName("Test Customer with Different Addresses")
    void testCustomerDifferentAddresses() {
        Address billing = new Address();
        billing.setCity("Boston");
        billing.setCountry("USA");
        
        Address shipping = new Address();
        shipping.setCity("Seattle");
        shipping.setCountry("USA");
        
        customer.setBillingAddress(billing);
        customer.setShippingAddress(shipping);
        
        assertNotSame(customer.getBillingAddress(), customer.getShippingAddress(),
            "Billing and shipping addresses should be different objects");
        assertNotEquals(customer.getBillingAddress().getCity(), 
                       customer.getShippingAddress().getCity(),
                       "Billing and shipping cities should be different");
    }
}
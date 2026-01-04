import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CartItem class
 * Tests cart item functionality including subtotal calculation
 */
class CartItemTest {

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem();
        // Using reflection to set private fields for testing
        try {
            var priceField = CartItem.class.getDeclaredField("priceAtAddition");
            priceField.setAccessible(true);
            priceField.set(cartItem, 10.5f);
            
            var quantityField = CartItem.class.getDeclaredField("quantity");
            quantityField.setAccessible(true);
            quantityField.set(cartItem, 2);
        } catch (Exception e) {
            fail("Failed to set up CartItem test data: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test CartItem Creation")
    void testCartItemCreation() {
        assertNotNull(cartItem, "CartItem object should be created");
    }

    @Test
    @DisplayName("Test Get Subtotal Calculation")
    void testGetSubtotal() {
        float subtotal = cartItem.getSubtotal();
        assertEquals(21.0f, subtotal, 0.001f, "Subtotal should be price * quantity");
    }

    @Test
    @DisplayName("Test Update Quantity with Valid Value")
    void testUpdateQuantityValid() {
        int newQuantity = 5;
        cartItem.updateQuantity(newQuantity);
        
        // Verify quantity was updated by checking subtotal
        float subtotal = cartItem.getSubtotal();
        assertEquals(52.5f, subtotal, 0.001f, "Subtotal should reflect new quantity");
    }

    @Test
    @DisplayName("Test Update Quantity with Zero")
    void testUpdateQuantityZero() {
        cartItem.updateQuantity(0);
        float subtotal = cartItem.getSubtotal();
        assertEquals(0.0f, subtotal, 0.001f, "Subtotal should be zero for zero quantity");
    }

    @Test
    @DisplayName("Test Update Quantity with Negative Value")
    void testUpdateQuantityNegative() {
        cartItem.updateQuantity(-1);
        float subtotal = cartItem.getSubtotal();
        assertEquals(-10.5f, subtotal, 0.001f, "Subtotal should handle negative quantity");
    }

    @Test
    @DisplayName("Test Edge Cases for Subtotal")
    void testSubtotalEdgeCases() {
        CartItem edgeCaseItem = new CartItem();
        try {
            var priceField = CartItem.class.getDeclaredField("priceAtAddition");
            priceField.setAccessible(true);
            var quantityField = CartItem.class.getDeclaredField("quantity");
            quantityField.setAccessible(true);
            
            // Test with zero price
            priceField.set(edgeCaseItem, 0.0f);
            quantityField.set(edgeCaseItem, 5);
            assertEquals(0.0f, edgeCaseItem.getSubtotal(), 0.001f, 
                "Subtotal should be zero when price is zero");
            
            // Test with large numbers
            priceField.set(edgeCaseItem, 999999.99f);
            quantityField.set(edgeCaseItem, 1000);
            assertEquals(999999990.0f, edgeCaseItem.getSubtotal(), 0.001f,
                "Subtotal should handle large numbers correctly");
                
        } catch (Exception e) {
            fail("Failed to test edge cases: " + e.getMessage());
        }
    }
}
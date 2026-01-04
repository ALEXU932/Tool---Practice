import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User class (if it exists)
 * Since User is a parent class of Customer
 */
class UserTest {

    @Test
    @DisplayName("Test User Inheritance")
    void testUserInheritance() {
        Customer customer = new Customer();
        
        // Test that Customer is indeed a User
        assertTrue(customer instanceof User, 
            "Customer should be an instance of User");
        
        // Test inheritance chain
        assertEquals("User", customer.getClass().getSuperclass().getSimpleName(),
            "Customer should extend User class");
    }
}
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Address class
 * Tests address validation and getter/setter methods
 */
class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
    }

    @Test
    @DisplayName("Test Address Creation and Default Values")
    void testAddressCreation() {
        assertNotNull(address, "Address object should be created");
    }

    @Test
    @DisplayName("Test Address ID Getter and Setter")
    void testAddressId() {
        String expectedId = "ADDR001";
        address.setAddressId(expectedId);
        assertEquals(expectedId, address.getAddressId(), "Address ID should match");
    }

    @Test
    @DisplayName("Test City Getter and Setter")
    void testCity() {
        String expectedCity = "New York";
        address.setCity(expectedCity);
        assertEquals(expectedCity, address.getCity(), "City should match");
    }

    @Test
    @DisplayName("Test Country Getter and Setter")
    void testCountry() {
        String expectedCountry = "USA";
        address.setCountry(expectedCountry);
        assertEquals(expectedCountry, address.getCountry(), "Country should match");
    }

    @Test
    @DisplayName("Test ZipCode Getter and Setter")
    void testZipCode() {
        String expectedZip = "10001";
        address.setZipCode(expectedZip);
        assertEquals(expectedZip, address.getZipCode(), "Zip code should match");
    }

    @Test
    @DisplayName("Test Validate Method - No Exception Expected")
    void testValidate() {
        assertDoesNotThrow(() -> address.validate(), 
            "Validate method should not throw exceptions");
    }

    @Test
    @DisplayName("Test All Properties Together")
    void testCompleteAddress() {
        address.setAddressId("ADDR002");
        address.setCity("Los Angeles");
        address.setCountry("USA");
        address.setZipCode("90001");

        assertAll("Address Properties",
            () -> assertEquals("ADDR002", address.getAddressId()),
            () -> assertEquals("Los Angeles", address.getCity()),
            () -> assertEquals("USA", address.getCountry()),
            () -> assertEquals("90001", address.getZipCode())
        );
    }
}
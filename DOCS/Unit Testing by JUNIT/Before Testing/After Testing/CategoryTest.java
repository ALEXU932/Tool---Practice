import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Category class
 * Tests category management and product operations
 */
class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId("CAT001");
        category.setName("Electronics");
        category.setActive(true);
    }

    @Test
    @DisplayName("Test Category Creation and Properties")
    void testCategoryCreation() {
        assertNotNull(category, "Category object should be created");
        assertEquals("CAT001", category.getCategoryId(), "Category ID should match");
        assertEquals("Electronics", category.getName(), "Category name should match");
        assertTrue(category.isActive(), "Category should be active");
    }

    @Test
    @DisplayName("Test Category ID Getter and Setter")
    void testCategoryId() {
        String newId = "CAT002";
        category.setCategoryId(newId);
        assertEquals(newId, category.getCategoryId(), "Category ID should be updated");
    }

    @Test
    @DisplayName("Test Category Name Getter and Setter")
    void testCategoryName() {
        String newName = "Books";
        category.setName(newName);
        assertEquals(newName, category.getName(), "Category name should be updated");
    }

    @Test
    @DisplayName("Test Category Active Status")
    void testCategoryActiveStatus() {
        category.setActive(false);
        assertFalse(category.isActive(), "Category should be inactive");
        
        category.setActive(true);
        assertTrue(category.isActive(), "Category should be active");
    }

    @Test
    @DisplayName("Test Add Image Method")
    void testAddImage() {
        String imageUrl = "http://example.com/image.jpg";
        assertDoesNotThrow(() -> category.addImage(imageUrl),
            "addImage method should not throw exceptions");
    }

    @Test
    @DisplayName("Test Get Product Count")
    void testGetProductCount() {
        Product testProduct = new Product();
        int count = category.getProductCount(testProduct);
        assertEquals(0, count, "Product count should return 0 for default implementation");
    }

    @Test
    @DisplayName("Test Get Product Info")
    void testGetProductInfo() {
        Product product = category.getProductInfo();
        assertNull(product, "getProductInfo should return null for default implementation");
    }

    @Test
    @DisplayName("Test Get Subcategories")
    void testGetSubcategories() {
        List<Category> subcategories = category.getSubcategories();
        assertNull(subcategories, "getSubcategories should return null for default implementation");
    }

    @Test
    @DisplayName("Test Update Stock")
    void testUpdateStock() {
        int quantity = 10;
        assertDoesNotThrow(() -> category.updateStock(quantity),
            "updateStock method should not throw exceptions");
    }

    @Test
    @DisplayName("Test All Category Operations")
    void testAllOperations() {
        assertAll("All Category Operations",
            () -> assertNotNull(category.getCategoryId()),
            () -> assertNotNull(category.getName()),
            () -> assertTrue(category.isActive()),
            () -> assertDoesNotThrow(() -> category.addImage("test.jpg")),
            () -> assertDoesNotThrow(() -> category.updateStock(5))
        );
    }
}
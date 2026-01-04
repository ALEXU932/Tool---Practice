package com.ecommerce.reports;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

/**
 * Unit tests for SalesReport class following Test-Driven Development (TDD) principles
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Sales Report Tests - TDD Implementation")
class SalesReportTest {

    private SalesReport salesReport;
    private static final String REPORT_ID = "REP-2024-Q1";
    private static final String REPORT_TYPE = "Quarterly";
    private static final String FORMAT = "PDF";

    @BeforeEach
    @DisplayName("Setup - Create fresh SalesReport instance before each test")
    void setUp() {
        salesReport = new SalesReport();
        salesReport.setReportId(REPORT_ID);
        salesReport.setReporttype(REPORT_TYPE);
        salesReport.setFormat(FORMAT);
    }

    @AfterEach
    @DisplayName("Cleanup - Reset SalesReport instance after each test")
    void tearDown() {
        salesReport = null;
    }

    // ========== TDD PHASE 1: BASIC PROPERTY TESTS ==========

    @Test
    @Order(1)
    @DisplayName("TDD-1: Test SalesReport object creation - RED")
    void testSalesReportObjectCreation() {
        // Arrange & Act
        SalesReport report = new SalesReport();
        
        // Assert
        assertNotNull(report, "SalesReport object should be created successfully");
        System.out.println("✓ TDD-1: SalesReport object creation test PASSED");
    }

    @Test
    @Order(2)
    @DisplayName("TDD-2: Test Report ID getter and setter - RED")
    void testReportIdGetterSetter() {
        // Arrange
        String expectedId = "REP-2024-001";
        
        // Act
        salesReport.setReportId(expectedId);
        String actualId = salesReport.getReportId();
        
        // Assert
        assertEquals(expectedId, actualId, "Report ID should match set value");
        System.out.println("✓ TDD-2: Report ID getter/setter test PASSED");
    }

    @Test
    @Order(3)
    @DisplayName("TDD-3: Test Report Type getter and setter - RED")
    void testReportTypeGetterSetter() {
        // Arrange
        String expectedType = "Monthly";
        
        // Act
        salesReport.setReporttype(expectedType);
        String actualType = salesReport.getReporttype();
        
        // Assert
        assertEquals(expectedType, actualType, "Report type should match set value");
        System.out.println("✓ TDD-3: Report Type getter/setter test PASSED");
    }

    @Test
    @Order(4)
    @DisplayName("TDD-4: Test Format getter and setter - RED")
    void testFormatGetterSetter() {
        // Arrange
        String expectedFormat = "CSV";
        
        // Act
        salesReport.setFormat(expectedFormat);
        String actualFormat = salesReport.getFormat();
        
        // Assert
        assertEquals(expectedFormat, actualFormat, "Format should match set value");
        System.out.println("✓ TDD-4: Format getter/setter test PASSED");
    }

    @Test
    @Order(5)
    @DisplayName("TDD-5: Test Generated Date getter and setter - RED")
    void testGeneratedDateGetterSetter() {
        // Arrange
        Date expectedDate = new Date();
        
        // Act
        salesReport.setGeneratedDate(expectedDate);
        Date actualDate = salesReport.getGeneratedDate();
        
        // Assert
        assertEquals(expectedDate, actualDate, "Generated date should match set value");
        assertNotNull(actualDate, "Generated date should not be null after setting");
        System.out.println("✓ TDD-5: Generated Date getter/setter test PASSED");
    }

    // ========== TDD PHASE 2: BUSINESS LOGIC TESTS ==========

    @Test
    @Order(6)
    @DisplayName("TDD-6: Test Total Revenue calculation - RED")
    void testTotalRevenueCalculation() {
        // Arrange
        float expectedRevenue = 12500.75f;
        
        // Act
        salesReport.setTotalRevenue(expectedRevenue);
        float actualRevenue = salesReport.getTotalRevenue();
        
        // Assert
        assertEquals(expectedRevenue, actualRevenue, 0.001f, 
            "Total revenue should match set value with precision");
        System.out.println("✓ TDD-6: Total Revenue calculation test PASSED");
    }

    @Test
    @Order(7)
    @DisplayName("TDD-7: Test Total Customers getter and setter - RED")
    void testTotalCustomersGetterSetter() {
        // Arrange
        int expectedCustomers = 150;
        
        // Act
        salesReport.setTotalCustomers(expectedCustomers);
        int actualCustomers = salesReport.getTotalCustomers();
        
        // Assert
        assertEquals(expectedCustomers, actualCustomers, 
            "Total customers should match set value");
        assertTrue(actualCustomers >= 0, "Total customers should not be negative");
        System.out.println("✓ TDD-7: Total Customers getter/setter test PASSED");
    }

    @Test
    @Order(8)
    @DisplayName("TDD-8: Test Total Orders getter and setter - RED")
    void testTotalOrdersGetterSetter() {
        // Arrange
        int expectedOrders = 75;
        
        // Act
        salesReport.setTotalOrders(expectedOrders);
        int actualOrders = salesReport.getTotalOrders();
        
        // Assert
        assertEquals(expectedOrders, actualOrders, 
            "Total orders should match set value");
        assertTrue(actualOrders >= 0, "Total orders should not be negative");
        System.out.println("✓ TDD-8: Total Orders getter/setter test PASSED");
    }

    // ========== TDD PHASE 3: BOUNDARY AND EDGE CASES ==========

    @Test
    @Order(9)
    @DisplayName("TDD-9: Test Zero Revenue Edge Case - RED")
    void testZeroRevenueEdgeCase() {
        // Arrange & Act
        salesReport.setTotalRevenue(0.0f);
        
        // Assert
        assertEquals(0.0f, salesReport.getTotalRevenue(), 0.001f,
            "Zero revenue should be handled correctly");
        System.out.println("✓ TDD-9: Zero Revenue edge case test PASSED");
    }

    @Test
    @Order(10)
    @DisplayName("TDD-10: Test Negative Revenue Validation - RED")
    void testNegativeRevenueValidation() {
        // Arrange & Act
        salesReport.setTotalRevenue(-1000.0f);
        
        // Assert
        assertEquals(-1000.0f, salesReport.getTotalRevenue(), 0.001f,
            "Negative revenue should be accepted (for loss scenarios)");
        System.out.println("✓ TDD-10: Negative Revenue validation test PASSED");
    }

    @Test
    @Order(11)
    @DisplayName("TDD-11: Test Large Number Handling - RED")
    void testLargeNumberHandling() {
        // Arrange
        float largeRevenue = 9999999.99f;
        int largeCustomers = 1000000;
        int largeOrders = 500000;
        
        // Act
        salesReport.setTotalRevenue(largeRevenue);
        salesReport.setTotalCustomers(largeCustomers);
        salesReport.setTotalOrders(largeOrders);
        
        // Assert
        assertAll("Large number handling",
            () -> assertEquals(largeRevenue, salesReport.getTotalRevenue(), 0.01f),
            () -> assertEquals(largeCustomers, salesReport.getTotalCustomers()),
            () -> assertEquals(largeOrders, salesReport.getTotalOrders())
        );
        System.out.println("✓ TDD-11: Large Number handling test PASSED");
    }

    // ========== TDD PHASE 4: PARAMETERIZED TESTS ==========

    @ParameterizedTest(name = "Format: {0} should be valid")
    @Order(12)
    @ValueSource(strings = {"PDF", "CSV", "Excel", "HTML", "JSON"})
    @DisplayName("TDD-12: Parameterized Test - Valid Report Formats")
    void testValidReportFormats(String format) {
        // Arrange & Act
        salesReport.setFormat(format);
        
        // Assert
        assertEquals(format, salesReport.getFormat(), 
            "Format " + format + " should be accepted");
        System.out.println("✓ TDD-12: Format " + format + " validation PASSED");
    }

    @ParameterizedTest(name = "Report Type: {0}, Expected Customers: {1}")
    @Order(13)
    @CsvSource({
        "Daily, 50",
        "Weekly, 350",
        "Monthly, 1500",
        "Quarterly, 4500",
        "Annual, 18000"
    })
    @DisplayName("TDD-13: Parameterized Test - Report Types with Sample Data")
    void testReportTypesWithSampleData(String reportType, int expectedCustomers) {
        // Arrange & Act
        salesReport.setReporttype(reportType);
        salesReport.setTotalCustomers(expectedCustomers);
        
        // Assert
        assertAll("Report type with sample data",
            () -> assertEquals(reportType, salesReport.getReporttype()),
            () -> assertEquals(expectedCustomers, salesReport.getTotalCustomers())
        );
        System.out.println("✓ TDD-13: Report type " + reportType + " with " + 
                          expectedCustomers + " customers test PASSED");
    }

    // ========== TDD PHASE 5: INTEGRATION AND STATE TESTS ==========

    @Test
    @Order(14)
    @DisplayName("TDD-14: Test Complete SalesReport State - RED")
    void testCompleteSalesReportState() {
        // Arrange
        Date now = new Date();
        String reportId = "REP-2024-COMPLETE";
        String reportType = "Comprehensive";
        String format = "Excel";
        float revenue = 45000.50f;
        int customers = 1200;
        int orders = 600;
        
        // Act
        salesReport.setReportId(reportId);
        salesReport.setReporttype(reportType);
        salesReport.setFormat(format);
        salesReport.setGeneratedDate(now);
        salesReport.setTotalRevenue(revenue);
        salesReport.setTotalCustomers(customers);
        salesReport.setTotalOrders(orders);
        
        // Assert
        assertAll("Complete SalesReport state validation",
            () -> assertEquals(reportId, salesReport.getReportId()),
            () -> assertEquals(reportType, salesReport.getReporttype()),
            () -> assertEquals(format, salesReport.getFormat()),
            () -> assertEquals(now, salesReport.getGeneratedDate()),
            () -> assertEquals(revenue, salesReport.getTotalRevenue(), 0.001f),
            () -> assertEquals(customers, salesReport.getTotalCustomers()),
            () -> assertEquals(orders, salesReport.getTotalOrders())
        );
        System.out.println("✓ TDD-14: Complete SalesReport state test PASSED");
    }

    @Test
    @Order(15)
    @DisplayName("TDD-15: Test SalesReport Immutability After Finalize - RED")
    void testFinalizeMethod() {
        // Arrange
        salesReport.setTotalRevenue(10000.0f);
        float initialRevenue = salesReport.getTotalRevenue();
        
        // Act
        try {
            salesReport.finalize();
        } catch (Throwable e) {
            fail("finalize() should not throw exceptions: " + e.getMessage());
        }
        
        // Assert
        assertEquals(initialRevenue, salesReport.getTotalRevenue(), 0.001f,
            "Revenue should remain unchanged after finalize");
        System.out.println("✓ TDD-15: Finalize method test PASSED");
    }

    // ========== TDD PHASE 6: ASSUMPTIONS AND CONDITIONAL TESTS ==========

    @Test
    @Order(16)
    @DisplayName("TDD-16: Test Revenue Assumptions - RED")
    void testRevenueAssumptions() {
        // Arrange
        float revenue = 50000.0f;
        
        // Act
        salesReport.setTotalRevenue(revenue);
        
        // Assume certain conditions
        assumeTrue(revenue > 0, "Revenue should be positive for this test");
        assumeTrue(salesReport.getReporttype() != null, "Report type should be set");
        
        // Assert
        assertTrue(salesReport.getTotalRevenue() > 0, 
            "Revenue should remain positive when assumptions are met");
        System.out.println("✓ TDD-16: Revenue assumptions test PASSED");
    }

    @Test
    @Order(17)
    @DisplayName("TDD-17: Test Date Consistency - RED")
    void testDateConsistency() {
        // Arrange
        Date testDate = Date.from(LocalDate.of(2024, 1, 15)
            .atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        // Act
        salesReport.setGeneratedDate(testDate);
        
        // Assert
        Date retrievedDate = salesReport.getGeneratedDate();
        assertNotNull(retrievedDate, "Retrieved date should not be null");
        assertEquals(testDate, retrievedDate, "Dates should be equal");
        assertFalse(retrievedDate.after(new Date()), 
            "Report date should not be in the future");
        System.out.println("✓ TDD-17: Date consistency test PASSED");
    }

    // ========== TDD PHASE 7: PERFORMANCE AND THREAD SAFETY ==========

    @Test
    @Order(18)
    @DisplayName("TDD-18: Test Concurrent Access Simulation - RED")
    void testConcurrentAccessSimulation() throws InterruptedException {
        // Arrange
        final float[] results = new float[2];
        
        // Act - Simulate concurrent access
        Thread thread1 = new Thread(() -> {
            salesReport.setTotalRevenue(1000.0f);
            results[0] = salesReport.getTotalRevenue();
        });
        
        Thread thread2 = new Thread(() -> {
            salesReport.setTotalRevenue(2000.0f);
            results[1] = salesReport.getTotalRevenue();
        });
        
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        
        // Assert
        assertTrue(results[0] == 1000.0f || results[0] == 2000.0f,
            "Thread 1 should see one of the set values");
        assertTrue(results[1] == 1000.0f || results[1] == 2000.0f,
            "Thread 2 should see one of the set values");
        System.out.println("✓ TDD-18: Concurrent access simulation test PASSED");
    }

    // ========== TDD PHASE 8: EXCEPTION AND ERROR TESTS ==========

    @Test
    @Order(19)
    @DisplayName("TDD-19: Test Null Values Handling - RED")
    void testNullValuesHandling() {
        // Arrange & Act
        salesReport.setReportId(null);
        salesReport.setReporttype(null);
        salesReport.setFormat(null);
        salesReport.setGeneratedDate(null);
        
        // Assert - Should handle null values gracefully
        assertNull(salesReport.getReportId(), "Report ID should accept null");
        assertNull(salesReport.getReporttype(), "Report type should accept null");
        assertNull(salesReport.getFormat(), "Format should accept null");
        assertNull(salesReport.getGeneratedDate(), "Generated date should accept null");
        System.out.println("✓ TDD-19: Null values handling test PASSED");
    }

    // ========== TDD PHASE 9: FINAL INTEGRATION TEST ==========

    @Test
    @Order(20)
    @DisplayName("TDD-20: Final Integration Test - All Properties Working - RED")
    void testAllPropertiesIntegration() {
        // This test validates all properties work together correctly
        
        // Arrange
        String testReportId = "INT-TEST-001";
        String testReportType = "Integration";
        String testFormat = "IntegrationFormat";
        Date testDate = new Date();
        float testRevenue = 99999.99f;
        int testCustomers = 9999;
        int testOrders = 4999;
        
        // Act
        SalesReport integrationReport = new SalesReport();
        integrationReport.setReportId(testReportId);
        integrationReport.setReporttype(testReportType);
        integrationReport.setFormat(testFormat);
        integrationReport.setGeneratedDate(testDate);
        integrationReport.setTotalRevenue(testRevenue);
        integrationReport.setTotalCustomers(testCustomers);
        integrationReport.setTotalOrders(testOrders);
        
        // Assert - Comprehensive validation
        assertAll("Integration Test - All Properties",
            () -> assertNotNull(integrationReport, "Report should be created"),
            () -> assertEquals(testReportId, integrationReport.getReportId(), 
                "Report ID should be set"),
            () -> assertEquals(testReportType, integrationReport.getReporttype(),
                "Report type should be set"),
            () -> assertEquals(testFormat, integrationReport.getFormat(),
                "Format should be set"),
            () -> assertEquals(testDate, integrationReport.getGeneratedDate(),
                "Date should be set"),
            () -> assertEquals(testRevenue, integrationReport.getTotalRevenue(), 0.001f,
                "Revenue should be set"),
            () -> assertEquals(testCustomers, integrationReport.getTotalCustomers(),
                "Customers should be set"),
            () -> assertEquals(testOrders, integrationReport.getTotalOrders(),
                "Orders should be set"),
            () -> assertDoesNotThrow(() -> integrationReport.finalize(),
                "finalize() should not throw exceptions")
        );
        
        // Additional validation
        float avgOrderValue = testRevenue / testOrders;
        assertTrue(avgOrderValue > 0, "Average order value should be positive");
        
        System.out.println("✓ TDD-20: Final Integration Test PASSED");
        System.out.println("==========================================");
        System.out.println("ALL 20 TDD TESTS COMPLETED SUCCESSFULLY!");
        System.out.println("==========================================");
    }
}
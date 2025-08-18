package delivery.service.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.service.CalculateDeliveryTime;

class CalculateDeliveryTimeTest {

    private CalculateDeliveryTime calculateDeliveryTime;

    @BeforeEach
    void setUp() {
        calculateDeliveryTime = new CalculateDeliveryTime();
    }

    @Test
    void testCalculateDeliveryTime_MultiplePackagesAndVehicles() {
        List<PackageDetails> packages = Arrays.asList(
                new PackageDetails("PKG1", 50.0, 30.0, "OFR001"),
                new PackageDetails("PKG2", 75.0, 125.0, "OFR002"),
                new PackageDetails("PKG3", 175.0, 100.0, "OFR003")
        );

        Map<String, Double> result = calculateDeliveryTime.calculateDeliveryTime(packages, 2, 70.0, 200.0);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsKey("PKG1"));
        assertTrue(result.containsKey("PKG2"));
        assertTrue(result.containsKey("PKG3"));
    }

    @Test
    void testCalculateDeliveryTime_SingleVehicle() {
        List<PackageDetails> packages = Arrays.asList(
                new PackageDetails("PKG1", 20.0, 30.0, "OFR001"),
                new PackageDetails("PKG2", 30.0, 50.0, "OFR002")
        );

        Map<String, Double> result = calculateDeliveryTime.calculateDeliveryTime(packages, 1, 50.0, 100.0);

        assertEquals(2, result.size());
        assertTrue(result.get("PKG1") > 0);
        assertTrue(result.get("PKG2") > 0);
    }

    @Test
    void testCalculateDeliveryTime_NoPackages() {
        List<PackageDetails> packages = Collections.emptyList();

        Map<String, Double> result = calculateDeliveryTime.calculateDeliveryTime(packages, 2, 60.0, 200.0);

        assertTrue(result.isEmpty());
    }

    @Test
    void testCalculateDeliveryTime_PackageExceedsMaxWeight() {
        List<PackageDetails> packages = Arrays.asList(
                new PackageDetails("PKG1", 300.0, 100.0, "OFR001")
        );

        Map<String, Double> result = calculateDeliveryTime.calculateDeliveryTime(packages, 1, 60.0, 200.0);

        // Since package exceeds maxWeight, it should not be delivered
        assertTrue(result.isEmpty());
    }
}

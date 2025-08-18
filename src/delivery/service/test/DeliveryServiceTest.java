package delivery.service.test;

import delivery.service.main.DeliveryService;
import delivery.service.main.dto.PackageDetails;
import delivery.service.main.response.OutputDetails;
import delivery.service.main.service.CalculateDeliveryCharges;
import delivery.service.main.service.ICalculateDeliveryCharge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {

    @Test
    void testDiscountAppliedForOFR001() {
        ICalculateDeliveryCharge service = new CalculateDeliveryCharges();

        PackageDetails pkg = new PackageDetails("PKG1", 80.0, 100.0, "OFR001");
        Double discount = service.calculateDiscount(pkg);

        assertEquals(0.10, discount, "OFR001 should give 10% discount");
    }

    @Test
    void testDeliveryChargeCalculation() {
        ICalculateDeliveryCharge service = new CalculateDeliveryCharges();

        PackageDetails pkg = new PackageDetails("PKG1", 50.0, 30.0, "OFR002");
        Double discount = service.calculateDiscount(pkg);

        OutputDetails output = service.calculateDeliveryCharge(100.0, pkg, discount);

        assertNotNull(output);
        assertEquals("PKG1", output.getPackageId());
        assertTrue(output.getTotalCostOfDelivery() > 0, "Total cost should be positive");
    }

    @Test
    void testMainMethodWithValidArgs() {
        String[] args = {
                "100", "2",
                "PKG1:50:30:OFR002",
                "PKG2:75:125:OFR003"
        };

        // No exception should be thrown when running with valid args
        assertDoesNotThrow(() -> DeliveryService.main(args));
    }

    @Test
    void testMainMethodWithNoArgs() {
        String[] args = {};
        assertDoesNotThrow(() -> DeliveryService.main(args));
    }
}

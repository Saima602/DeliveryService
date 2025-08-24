package delivery.service.test.service;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.response.OutputDetails;
import delivery.service.main.service.CalculateDeliveryCharges;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateDeliveryChargesTest  {

   

   private final CalculateDeliveryCharges service = new CalculateDeliveryCharges();

    @Test
    void testCalculateDiscount_ValidOffer() {
        // Suppose OFR001 is valid with 10% discount
        PackageDetails pkg = new PackageDetails("PKG1", 150.0, 100.0, "OFR001");

        Double discount = service.calculateDiscount(pkg);

        assertTrue(discount > 0);
    }

    @Test
    void testCalculateDiscount_InvalidOffer() {
        PackageDetails pkg = new PackageDetails("PKG2", 50.0, 20.0, "INVALID");

        Double discount = service.calculateDiscount(pkg);

        assertEquals(0.0, discount);
    }

    @Test
    void testCalculateDeliveryCharge_WithDiscount() {
        PackageDetails pkg = new PackageDetails("PKG3", 10.0, 10.0, "OFR001");

        Double baseDeliveryCharge = 100.0;
        Double discount = 10.0; // 10%

        OutputDetails output = service.calculateDeliveryCharge(baseDeliveryCharge, pkg, discount);

        // deliveryCharge = 100 + (10 * 10) + (10 * 5) = 100 + 100 + 50 = 250
        // discount = 10% of 250 = 25
        // total = 225
        assertEquals("PKG3", output.getPackageId());
        assertEquals(25.0, output.getDiscount(), 0.01);
        assertEquals(225.0, output.getTotalCostOfDelivery(), 0.01);
    }

    @Test
    void testCalculateDeliveryCharge_NoDiscount() {
        PackageDetails pkg = new PackageDetails("PKG4", 5.0, 5.0, null);

        Double baseDeliveryCharge = 100.0;
        Double discount = 0.0;

        OutputDetails output = service.calculateDeliveryCharge(baseDeliveryCharge, pkg, discount);

        // deliveryCharge = 100 + (5 * 10) + (5 * 5) = 100 + 50 + 25 = 175
        // discount = 0
        // total = 175
        assertEquals(0.0, output.getDiscount(), 0.01);
        assertEquals(175.0, output.getTotalCostOfDelivery(), 0.01);
    }
}

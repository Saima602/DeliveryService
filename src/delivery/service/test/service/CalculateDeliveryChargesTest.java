package delivery.service.test.service;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.response.OutputDetails;
import delivery.service.main.service.CalculateDeliveryCharges;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateDeliveryChargesTest  {

    private final CalculateDeliveryCharges calc = new CalculateDeliveryCharges();

    @Test
    void testOFR001DiscountValid() {
        PackageDetails pkg = new PackageDetails("PKG1", 100.0, 100.0, "OFR001");
        Double discount = calc.calculateDiscount(pkg);

        assertEquals(0.10, discount, "OFR001 should give 10% discount");
    }

    @Test
    void testOFR002DiscountValid() {
        PackageDetails pkg = new PackageDetails("PKG2", 150.0, 100.0, "OFR002");
        Double discount = calc.calculateDiscount(pkg);

        assertEquals(0.07, discount, "OFR002 should give 7% discount");
    }

    @Test
    void testOFR003DiscountValid() {
        PackageDetails pkg = new PackageDetails("PKG3", 50.0, 200.0, "OFR003");
        Double discount = calc.calculateDiscount(pkg);

        assertEquals(0.05, discount, "OFR003 should give 5% discount");
    }

    @Test
    void testNoDiscountInvalidOfferCode() {
        PackageDetails pkg = new PackageDetails("PKG4", 20.0, 100.0, "INVALID");
        Double discount = calc.calculateDiscount(pkg);

        assertEquals(0.0, discount, "Invalid offer code should return 0% discount");
    }

    @Test
    void testCalculateDeliveryChargeWithDiscount() {
        PackageDetails pkg = new PackageDetails("PKG5", 10.0, 100.0, "OFR003");

        Double discount = calc.calculateDiscount(pkg);
        OutputDetails output = calc.calculateDeliveryCharge(100.0, pkg, discount);

        // DeliveryCharge = 100 + (10*10) + (100*5) = 100 + 100 + 500 = 700
        // Discount = 700 * 0.05 = 35.0
        // Total = 700 - 35 = 665.0
        assertEquals("PKG5", output.getPackageId());
        assertEquals(35.0, output.getDiscount());
        assertEquals(665.0, output.getTotalCostOfDelivery());
    }

    @Test
    void testCalculateDeliveryChargeNoDiscount() {
        PackageDetails pkg = new PackageDetails("PKG6", 5.0, 20.0, "NONE");

        Double discount = calc.calculateDiscount(pkg);
        OutputDetails output = calc.calculateDeliveryCharge(100.0, pkg, discount);

        // DeliveryCharge = 100 + (5*10) + (20*5) = 100 + 50 + 100 = 250
        // Discount = 0
        assertEquals(0.0, output.getDiscount());
        assertEquals(250.0, output.getTotalCostOfDelivery());
    }
}

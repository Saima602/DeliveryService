package delivery.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import delivery.service.main.DeliveryService;
import delivery.service.main.dto.PackageDetails;
import delivery.service.main.exception.DeliveryServiceException;
import delivery.service.main.response.OutputDetails;

class DeliveryServiceTest {

    @Test
    void testFetchPackagesList_validInput() throws DeliveryServiceException {
        String[] args = {
            "100",   // base cost
            "2",     // number of packages
            "PKG1:5:5:OFR001",
            "PKG2:10:100:OFR002"
        };

        List<PackageDetails> packages = DeliveryService.fetchPackagesList(args);

        assertEquals(2, packages.size());
        assertEquals("PKG1", packages.get(0).getPkgId());
        assertEquals(5, packages.get(0).getPkgWeigthInKg());
    }

    @Test
    void testFetchPackagesList_invalidPackageId() {
        String[] args = {
            "100", "1", " :5:5:OFR001" // invalid package id
        };

        assertThrows(DeliveryServiceException.class, () -> {
            DeliveryService.fetchPackagesList(args);
        });
    }

    @Test
    void testCalculateDeliveryCharge_valid() throws DeliveryServiceException {
        String[] args = {
            "100",   // base cost
            "1",     // number of packages
            "PKG1:5:5:OFR001"
        };

        List<OutputDetails> outputs = DeliveryService.calculateDeliveryCharge(args);

        assertEquals(1, outputs.size());
        assertEquals("PKG1", outputs.get(0).getPackageId());
        assertTrue(outputs.get(0).getTotalCostOfDelivery() > 0);
    }

    @Test
    void testCalculateDeliveryTime_valid() throws DeliveryServiceException {
        String[] args = {
            "100", "2",
            "PKG1:5:5:OFR001",
            "PKG2:10:100:OFR002",
            "2",   // number of vehicles
            "70",  // max speed
            "200"  // max weight
        };

        Map<String, Double> timeMap = DeliveryService.calculateDeliveryTime(args, 2);

        assertEquals(2, timeMap.size());
        assertTrue(timeMap.get("PKG1") >= 0);
    }
}

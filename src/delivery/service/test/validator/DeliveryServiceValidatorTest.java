package delivery.service.test.validator;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import delivery.service.main.constant.DeliveryServiceConstant;
import delivery.service.main.exception.DeliveryServiceException;
import delivery.service.main.validator.DeliveryServiceValidator;

class DeliveryServiceValidatorTest {

    // --- validateInputs tests ---

    @Test
    void testValidateInputs_validDeliveryCostApp() {
        String[] args = {"100", "2", "PKG1:5:10:OFR001", "PKG2:15:20:OFR002"};

        assertDoesNotThrow(() ->
            DeliveryServiceValidator.validateInputs(args, 2, DeliveryServiceConstant.DELIVERY_COST_APP)
        );
    }

    @Test
    void testValidateInputs_packageCountZero_throwsException() {
        String[] args = {"100", "0"};

        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validateInputs(args, 0, DeliveryServiceConstant.DELIVERY_COST_APP)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.PACKAGE_DETAILS_NOT_FOUND));
    }

    @Test
    void testValidateInputs_packageCountMismatch_throwsException() {
        String[] args = {"100", "3", "PKG1:5:10:OFR001"};

        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validateInputs(args, 1, DeliveryServiceConstant.DELIVERY_COST_APP)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.NUMBER_OF_PACKAGES_NOT_SAME_AS_COUNT));
    }

    @Test
    void testValidateInputs_invalidInputsForDeliveryTimeApp() {
        String[] args = {"100", "1", "PKG1:5:10:OFR001", "2", "70", "200", "EXTRA"};

        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validateInputs(args, 1, DeliveryServiceConstant.DELIVERY_TIME_APP)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.INVALID_INPUTS_DELIVERY_TIME));
    }

    // --- validatePackageWeightOrDistance tests ---

    @Test
    void testValidatePackageWeightOrDistance_validWeight() {
        assertDoesNotThrow(() ->
            DeliveryServiceValidator.validatePackageWeightOrDistance("10", DeliveryServiceConstant.WEIGHT, 1)
        );
    }

    @Test
    void testValidatePackageWeightOrDistance_invalidWeightEmpty() {
        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validatePackageWeightOrDistance(" ", DeliveryServiceConstant.WEIGHT, 1)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.INVALID_WEIGHT));
    }

    @Test
    void testValidatePackageWeightOrDistance_invalidWeightZero() {
        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validatePackageWeightOrDistance("0", DeliveryServiceConstant.WEIGHT, 1)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.INVALID_WEIGHT));
    }

    @Test
    void testValidatePackageWeightOrDistance_invalidDistanceEmpty() {
        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validatePackageWeightOrDistance("", DeliveryServiceConstant.DISTANCE, 1)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.INVALID_DISTANCE));
    }

    @Test
    void testValidatePackageWeightOrDistance_invalidDistanceZero() {
        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validatePackageWeightOrDistance("0", DeliveryServiceConstant.DISTANCE, 1)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.INVALID_DISTANCE));
    }

    // --- validatePackageId tests ---

    @Test
    void testValidatePackageId_valid() {
        assertDoesNotThrow(() ->
            DeliveryServiceValidator.validatePackageId("PKG1", 1)
        );
    }

    @Test
    void testValidatePackageId_nullOrEmpty_throwsException() {
        DeliveryServiceException ex = assertThrows(
            DeliveryServiceException.class,
            () -> DeliveryServiceValidator.validatePackageId(" ", 1)
        );

        assertTrue(ex.getMessage().contains(DeliveryServiceConstant.INVALID_PACKAGE_ID));
    }
}


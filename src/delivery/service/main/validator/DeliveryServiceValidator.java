package delivery.service.main.validator;

import delivery.service.main.constant.DeliveryServiceConstant;
import delivery.service.main.exception.DeliveryServiceException;

public class DeliveryServiceValidator {

	public static void validateInputs(String[] args, int numPackages, String app) throws DeliveryServiceException {

		Integer count = Integer.parseInt(args[1]);

		if (numPackages == 0) {
			throw new DeliveryServiceException(DeliveryServiceConstant.PACKAGE_DETAILS_NOT_FOUND);
		}

		if (numPackages != count) {
			throw new DeliveryServiceException(DeliveryServiceConstant.NUMBER_OF_PACKAGES_NOT_SAME_AS_COUNT);
		}
		if (DeliveryServiceConstant.DELIVERY_TIME_APP.equals(app)) {
			if (args.length > numPackages + 5) {

				throw new DeliveryServiceException(DeliveryServiceConstant.INVALID_INPUTS_DELIVERY_TIME);

			}
		}

	}
	
	
	public static void validatePackageWeightOrDistance(String weightOrDistanceValue, String weightOrDistance,
			int packageNumber) throws DeliveryServiceException {
		if (DeliveryServiceConstant.DISTANCE.equals(weightOrDistance)) {
			if (weightOrDistanceValue == null || weightOrDistanceValue.trim().isEmpty()) {
				throw new DeliveryServiceException(DeliveryServiceConstant.INVALID_DISTANCE + packageNumber);
			}
			Double packageWeightOrDistance = Double.parseDouble(weightOrDistanceValue);
			if (packageWeightOrDistance == 0.0) {
				throw new DeliveryServiceException(DeliveryServiceConstant.INVALID_DISTANCE + packageNumber);
			}
		}

		if (DeliveryServiceConstant.WEIGHT.equals(weightOrDistance)) {
			if (weightOrDistanceValue == null || weightOrDistanceValue.trim().isEmpty()) {
				throw new DeliveryServiceException(DeliveryServiceConstant.INVALID_WEIGHT + packageNumber);
			}
			Double packageWeightOrDistance = Double.parseDouble(weightOrDistanceValue);
			if (packageWeightOrDistance == 0.0) {
				throw new DeliveryServiceException(DeliveryServiceConstant.INVALID_WEIGHT + packageNumber);
			}
		}
	}

	public static void validatePackageId(String packageId, int packageNumber) throws DeliveryServiceException {
		if (packageId == null || packageId.trim().isEmpty()) {
			throw new DeliveryServiceException(DeliveryServiceConstant.INVALID_PACKAGE_ID + packageNumber);
		}
	}
}

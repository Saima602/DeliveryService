package delivery.service.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import delivery.service.main.constant.DeliveryServiceConstant;
import delivery.service.main.dto.PackageDetails;
import delivery.service.main.exception.DeliveryServiceException;
import delivery.service.main.response.DeliveryCostResponse;
import delivery.service.main.response.OutputDetails;
import delivery.service.main.service.CalculateDeliveryCharges;
import delivery.service.main.service.CalculateDeliveryTime;
import delivery.service.main.service.ICalculateDeliveryCharge;
import delivery.service.main.service.ICalculateDeliveryTime;
import delivery.service.main.validator.DeliveryServiceValidator;

public class DeliveryService {

	public static void main(String[] args) {
		String msg = "";
		List<OutputDetails> outputDetailsList = new ArrayList<>();
		try {

			List<PackageDetails> packages = fetchPackagesList(args);
			int numPackages = packages.size();

			if (args.length > numPackages + 2) {
				// Application Calculate Delivery Time begins

				outputDetailsList = calculateDeliveryCharge(args);
				Map<String, Double> packageMap = calculateDeliveryTime(args, numPackages);
				outputDetailsList.forEach(output -> {
					System.out.println(output.getPackageId() + " " + output.getDiscount() + " "
							+ output.getTotalCostOfDelivery() + " " + packageMap.get(output.getPackageId()));
				});
			} else if (args.length == numPackages + 2) {
				// Application Calculate Delivery Time begins
				outputDetailsList = calculateDeliveryCharge(args);
				outputDetailsList.forEach(output -> {
					System.out.println(
							output.getPackageId() + " " + output.getDiscount() + " " + output.getTotalCostOfDelivery());
				});
			} else if (args.length == numPackages || args.length == numPackages + 1) {
				throw new DeliveryServiceException(DeliveryServiceConstant.INVALID_INPUTS_DELIVERY_COST);
			}

		} catch (Exception e) {
			if (args.length == 0) {
				msg = DeliveryServiceConstant.NO_INPUTS_PROVIDED;

			} else if (args.length > 0) {

				msg = e.toString();
			}
			System.out.println(msg);
		}
	}

	

	/**
	 * For Delivery Cost
	 * 
	 * @throws DeliveryServiceException
	 */
	public static List<OutputDetails> calculateDeliveryCharge(String[] args) throws DeliveryServiceException {
		Double baseCost = Double.parseDouble(args[0]);
		int numPackages = Integer.parseInt(args[1]);
		DeliveryServiceValidator.validateInputs(args, numPackages, DeliveryServiceConstant.DELIVERY_COST_APP);
		List<PackageDetails> packages = new ArrayList<>();

		DeliveryCostResponse deliveryCostResponse = new DeliveryCostResponse();
		List<OutputDetails> outputDetailsList = new ArrayList<>();
		ICalculateDeliveryCharge calculateDeliveryCharge = new CalculateDeliveryCharges();
		packages = fetchPackagesList(args);
		packages.forEach(pkg -> {

			Double discount = calculateDeliveryCharge.calculateDiscount(pkg);
			OutputDetails outputDetails = calculateDeliveryCharge.calculateDeliveryCharge(baseCost, pkg, discount);
			outputDetailsList.add(outputDetails);
		});
		deliveryCostResponse.setOutputList(outputDetailsList);
		return outputDetailsList;
	}

	/**
	 * For Delivery Time
	 * 
	 * @throws DeliveryServiceException
	 */
	public static Map<String, Double> calculateDeliveryTime(String[] args, int numPackages)
			throws DeliveryServiceException {
		DeliveryServiceValidator.validateInputs(args, numPackages, DeliveryServiceConstant.DELIVERY_TIME_APP);
		int vehicleCount = Integer.parseInt(args[2 + numPackages]);
		Double maxSpeed = Double.parseDouble(args[3 + numPackages]);
		Double maxWeight = Double.parseDouble(args[4 + numPackages]);
		List<PackageDetails> packages = fetchPackagesList(args);
		ICalculateDeliveryTime calculateDeliveryTime = new CalculateDeliveryTime();
		Map<String, Double> packageMap = calculateDeliveryTime.calculateDeliveryTime(packages, vehicleCount, maxSpeed,
				maxWeight);

		return packageMap;
	}

	/***
	 * calculate number of packages
	 * 
	 * @throws DeliveryServiceException
	 */
	public static List<PackageDetails> fetchPackagesList(String[] args) throws DeliveryServiceException {
		List<PackageDetails> packages = new ArrayList<>();
		Integer count = Integer.parseInt(args[1]);
		for (int i = 0; i < count; i++) {
			String[] parts = args[i + 2].split(":");
			DeliveryServiceValidator.validatePackageId(parts[0], i + 1);
			DeliveryServiceValidator.validatePackageWeightOrDistance(parts[1], DeliveryServiceConstant.WEIGHT, i + 1);
			DeliveryServiceValidator.validatePackageWeightOrDistance(parts[2], DeliveryServiceConstant.DISTANCE, i + 1);
			packages.add(
					new PackageDetails(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), parts[3]));
		}
		return packages;
	}

	
}

package delivery.service.main.service;

import java.util.List;
import java.util.Map;

import delivery.service.main.dto.PackageDetails;


public interface ICalculateDeliveryTime {
        
	
	Map<String, Double> calculateDeliveryTime(List<PackageDetails> packages, int vehicleCount, Double maxSpeed, Double maxWeight);
}

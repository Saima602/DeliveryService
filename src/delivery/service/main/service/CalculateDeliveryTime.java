package delivery.service.main.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.dto.Vehicle;


public class CalculateDeliveryTime implements ICalculateDeliveryTime{

	static List<PackageDetails> bestCombo = new ArrayList<>();
    static Double bestWeight = 0.0;

	@Override
	public Map<String,Double> calculateDeliveryTime(List<PackageDetails> packages, int vehicleCount, Double maxSpeed, Double maxWeight) {
		Map<String,Double> resultMap = new HashMap<>();
		Map<Integer,Double> vehicleMaxTimeMap = new HashMap<>();
		List<PackageDetails> remainingPackages = new ArrayList<>(packages);
		List<Vehicle> vehicles = new ArrayList<>();
		for (int i = 1; i <= vehicleCount; i++) {
		    vehicles.add(new Vehicle(i));
		}
		remainingPackages = removeIfWeightIsGreaterThan200(remainingPackages);
		double maxTime = 0.0;
		while(remainingPackages.size() > 0) {
			
			for(int i=0;i<vehicleCount;i++) {	
				//check for i maxtime if it is greater than any of the other Maxtime ,iterate over to next Vehicle
				if(!(maxTimeIsShortest(vehicleMaxTimeMap,i))) {
					
					continue;
				}
				//to make sure no other Vehicle iteration is done once Remaining packages List is empty
				if(remainingPackages.size() ==0) {
					break;
				}
				maxTime =vehicleMaxTimeMap.getOrDefault(i,0.0) ;
				remainingPackages.sort(Comparator.comparingDouble(p -> p.getPkgWeigthInKg()));

				//get max time of delivering the package
				List<PackageDetails> packageDetaisToDeliver= findMaxWeight(remainingPackages, 0, new ArrayList<>(), 0.0, 200);
				
				bestWeight =0.0;
				
				for(PackageDetails pkg : packageDetaisToDeliver) {
				 
				   Double time = Math.round((pkg.getDistanceInKm()/maxSpeed)*100.00)/100.00;
				   if(maxTime < time) {
					   maxTime = time; 
				   }
				   resultMap.put(pkg.getPkgId(),vehicleMaxTimeMap.getOrDefault(i,0.0) + time);
				}
			 //System.out.println("Vehicle "+(i+1)+" will be avialable after "+maxTime*2);
			 vehicleMaxTimeMap.put(i,maxTime*2);
			 
			 remainingPackages.removeAll(packageDetaisToDeliver);
			}
		}	
		
	    return resultMap;
	}

	private List<PackageDetails> removeIfWeightIsGreaterThan200(List<PackageDetails> remainingPackages) {
		Optional<PackageDetails> packageWeightGreaterThan300 = remainingPackages.stream().filter(p -> p.getPkgWeigthInKg() >200).findAny();
		
		if(packageWeightGreaterThan300.isPresent()) {
			remainingPackages.remove(packageWeightGreaterThan300.get());
		}
		return remainingPackages;
	}

	private boolean maxTimeIsShortest(Map<Integer, Double> vehicleMaxTimeMap, int vehicle) {
		if(vehicleMaxTimeMap== null || vehicleMaxTimeMap.isEmpty() || vehicleMaxTimeMap.get(vehicle) ==null) {
			return true;
		}
		
		Integer currentKey = vehicle;
		Double currentValue = vehicleMaxTimeMap.get(currentKey);

		boolean isAnyLesserTimePresent = vehicleMaxTimeMap.entrySet().stream()
		    .anyMatch(e -> !e.getKey().equals(currentKey) && e.getValue() > currentValue);
		return isAnyLesserTimePresent;
	}

	

	public static List<PackageDetails> findMaxWeight(List<PackageDetails> packages, int index, List<PackageDetails> current, Double currentWeight, int maxWeight) {
        if (currentWeight > maxWeight) return bestCombo;

        if (currentWeight > bestWeight) {
            bestWeight = currentWeight;
            bestCombo = new ArrayList<>(current);
        }

        for (int i = index; i < packages.size(); i++) {
            current.add(packages.get(i));
            bestCombo = findMaxWeight(packages, i + 1, current, currentWeight + packages.get(i).getPkgWeigthInKg(), maxWeight);
            current.remove(current.size() - 1); // backtrack
        }
        return bestCombo;
    }
}


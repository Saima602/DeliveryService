package delivery.service.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.response.DeliveryCostResponse;
import delivery.service.main.response.OutputDetails;
import delivery.service.main.service.CalculateDeliveryCharges;
import delivery.service.main.service.CalculateDeliveryTime;
import delivery.service.main.service.ICalculateDeliveryCharge;
import delivery.service.main.service.ICalculateDeliveryTime;

public class DeliveryService {
	
	public static void main(String[] args) {
		String msg = "";
		
		try {
		    Double baseCost = Double.parseDouble(args[0]);
	        Integer count = Integer.parseInt(args[1]);

	        List<PackageDetails> packages = new ArrayList<>();
	        for (int i = 0; i < count; i++) {
	            String[] parts = args[i + 2].split(":");
	            packages.add(new PackageDetails(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), parts[3]));
	        }
            
            DeliveryCostResponse deliveryCostResponse = new DeliveryCostResponse();
            List<OutputDetails> outputDetailsList = new ArrayList<>();
            ICalculateDeliveryCharge calculateDeliveryCharge = new CalculateDeliveryCharges();
            ICalculateDeliveryTime calculateDeliveryTime = new CalculateDeliveryTime();
            packages.forEach(pkg ->{
            	
            	Double discount = calculateDeliveryCharge.calculateDiscount(pkg);
            	OutputDetails  outputDetails= calculateDeliveryCharge.calculateDeliveryCharge(baseCost,pkg,discount);
            	outputDetailsList.add(outputDetails);
            });
            deliveryCostResponse.setOutputList(outputDetailsList);
            
            
	        int numPackages = packages.size();
	        if(args.length > numPackages+2) {
		        // Application 2 begins	
	            int vehicleCount = Integer.parseInt(args[2 + numPackages]);
	            Double maxSpeed = Double.parseDouble(args[3 + numPackages]);
	            Double maxWeight = Double.parseDouble(args[4 + numPackages]);
                Map<String,Double> packageMap = calculateDeliveryTime.calculateDeliveryTime(packages,vehicleCount,maxSpeed,maxWeight);
                outputDetailsList.forEach(output -> {
	            	System.out.println(output.getPackageId() +" "+output.getDiscount()+" "+output.getTotalCostOfDelivery()+" "+packageMap.get(output.getPackageId()));
	            });
	        }else if(args.length == numPackages+2){
	        	outputDetailsList.forEach(output -> {
	            	System.out.println(output.getPackageId() +" "+output.getDiscount()+" "+output.getTotalCostOfDelivery());
	            });
	        }

	    }catch(Exception e) {
	    	if(args.length ==0) {
				msg = "Hey Seems you have missed on inputs !! Please provide Your input";
				
			}else if(args.length >0) {
				
				msg = e.toString();
			}
	    	System.out.println(msg);
	    }
	    }
	

}



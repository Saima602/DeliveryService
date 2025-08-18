package delivery.service.main.service;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.response.OutputDetails;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateDeliveryCharges implements ICalculateDeliveryCharge{

	@Override
	public Double calculateDiscount(PackageDetails packageDetails) {
		
		switch (packageDetails.getOfferCode()) {
        case "OFR001":
            if (packageDetails.getPkgWeigthInKg() >= 70 && packageDetails.getPkgWeigthInKg() <= 200 && packageDetails.getPkgWeigthInKg() <= 199)
                return 0.10;
            break;
        case "OFR002":
            if (packageDetails.getPkgWeigthInKg() >= 100 && packageDetails.getPkgWeigthInKg() <= 250 && packageDetails.getDistanceInKm() >= 50 && packageDetails.getDistanceInKm() <= 150)
                return 0.07;
            break;
        case "OFR003":
            if (packageDetails.getPkgWeigthInKg() >= 10 && packageDetails.getPkgWeigthInKg() <= 150 &&packageDetails.getDistanceInKm() >= 50 && packageDetails.getDistanceInKm() <= 250)
                return 0.05;
            break;
            
     }
    return 0.0;

	}

	

	@Override
	public OutputDetails calculateDeliveryCharge(Double basedeleiveryCharge,PackageDetails pkg, Double discount) {
		
		Double deliveryCharge  = basedeleiveryCharge+(pkg.getPkgWeigthInKg() * 10) + (pkg.getDistanceInKm() *5);
		Double discountAmount = deliveryCharge * discount;
		Double totalDeliveryCharge = deliveryCharge - discountAmount;
		 discountAmount = new BigDecimal(discountAmount)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
		return new OutputDetails(pkg.getPkgId(), discountAmount, totalDeliveryCharge,0.0);
	}

}

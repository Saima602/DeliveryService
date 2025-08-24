package delivery.service.main.service;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.response.OutputDetails;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateDeliveryCharges implements ICalculateDeliveryCharge{

	private static final int costPerKg=10;
	private static final int costPerKm=5;
	
	@Override
	public Double calculateDiscount(PackageDetails packageDetails) {
		
		
		  IOfferRule offer = DiscountInAction.getOffer(packageDetails.getOfferCode());

	        if (offer != null && offer.isApplicable(packageDetails)) {
	            double discount = offer.getDiscount();
	            return discount;
	        }
    return 0.0;

	}

	

	@Override
	public OutputDetails calculateDeliveryCharge(Double basedeleiveryCharge,PackageDetails pkg, Double discount) {
		
		Double deliveryCharge  = basedeleiveryCharge+(pkg.getPkgWeigthInKg() * costPerKg) + (pkg.getDistanceInKm() *costPerKm);
		Double discountAmount = deliveryCharge * (discount/100);
		Double totalDeliveryCharge = deliveryCharge - discountAmount;
		 discountAmount = new BigDecimal(discountAmount)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
		return new OutputDetails(pkg.getPkgId(), discountAmount, totalDeliveryCharge,0.0);
	}

}

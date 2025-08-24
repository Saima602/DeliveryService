package delivery.service.main.service;

import delivery.service.main.dto.PackageDetails;

public interface IOfferRule {
	
	    String getCode();
	    double getDiscount();
		boolean isApplicable(PackageDetails pkg);
	
}

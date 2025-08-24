package delivery.service.main.service;

import delivery.service.main.dto.PackageDetails;

public class OFR001 implements IOfferRule {
    @Override
	    public String getCode() { 
    	   return "OFR001"; 
    	}

	    @Override
	    public boolean isApplicable(PackageDetails pkg) {
	        return pkg.getPkgWeigthInKg() >= 70 && pkg.getPkgWeigthInKg() <= 200 && pkg.getDistanceInKm() <= 200;
	    }

	    @Override
	    public double getDiscount() { 
	    	return 10; 
	    }
	


}

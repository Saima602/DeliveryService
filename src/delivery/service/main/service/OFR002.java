package delivery.service.main.service;

import delivery.service.main.dto.PackageDetails;

public class OFR002 implements IOfferRule {

	@Override
	public String getCode() {
		return "OFR002";
	}

	@Override
	public boolean isApplicable(PackageDetails pkg) {
		return pkg.getPkgWeigthInKg() >= 100 && pkg.getPkgWeigthInKg() <= 250 && pkg.getDistanceInKm() >= 50 && pkg.getDistanceInKm() <= 150;
	}

	@Override
	public double getDiscount() {
		return 7; // 7% discount
	}

}

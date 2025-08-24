package delivery.service.main.service;

import delivery.service.main.dto.PackageDetails;

public class OFR003 implements IOfferRule {

	@Override
	public String getCode() {
		return "OFR003";
	}

	@Override
	public boolean isApplicable(PackageDetails pkg) {
		return (pkg.getPkgWeigthInKg() >= 10 && pkg.getPkgWeigthInKg() <= 150 && pkg.getDistanceInKm() >= 50 && pkg.getDistanceInKm() <= 250);
	}

	@Override
	public double getDiscount() {
		return 5; // 5% discount
	}

}

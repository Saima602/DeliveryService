package delivery.service.main.dto;

public class PackageDetails {
 
	public PackageDetails(String pkgId, Double pkgWeigthInKg, Double distanceInKm, String offerCode) {
		super();
		this.pkgId = pkgId;
		this.pkgWeigthInKg = pkgWeigthInKg;
		this.distanceInKm = distanceInKm;
		this.offerCode = offerCode;
	}
	private String pkgId;
	private Double pkgWeigthInKg;
	private Double distanceInKm;
	private String offerCode;
	public boolean scheduled;
	public String getPkgId() {
		return pkgId;
	}
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}
	public Double getPkgWeigthInKg() {
		return pkgWeigthInKg;
	}
	public void setPkgWeigthInKg(Double pkgWeigthInKg) {
		this.pkgWeigthInKg = pkgWeigthInKg;
	}
	public Double getDistanceInKm() {
		return distanceInKm;
	}
	public void setDistanceInKm(Double distanceInKm) {
		this.distanceInKm = distanceInKm;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	@Override
	public String toString() {
		return "PackageDetails [pkgId=" + pkgId + ", pkgWeigthInKg=" + pkgWeigthInKg + ", distanceInKm=" + distanceInKm
				+ ", offerCode=" + offerCode + "]";
	}
	
	
	
}

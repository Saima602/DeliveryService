package delivery.service.main.dto;

import java.util.List;

public class RequestDTO {

	private Double baseDeliveryCost;
	private Integer numberOfPackages;
	private List<PackageDetails> packageList;

	public Double getBaseDeliveryCost() {
		return baseDeliveryCost;
	}

	public void setBaseDeliveryCost(Double baseDeliveryCost) {
		this.baseDeliveryCost = baseDeliveryCost;
	}

	public Integer getNumberOfPackages() {
		return numberOfPackages;
	}

	public void setNumberOfPackages(Integer numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}

	public List<PackageDetails> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<PackageDetails> packageList) {
		this.packageList = packageList;
	}

	@Override
	public String toString() {
		return "RequestDTO [baseDeliveryCost=" + baseDeliveryCost + ", numberOfPackages=" + numberOfPackages
				+ ", packageList=" + packageList + "]";
	}

	public RequestDTO(Double baseDeliveryCost, Integer numberOfPackages, List<PackageDetails> packageList) {
		super();
		this.baseDeliveryCost = baseDeliveryCost;
		this.numberOfPackages = numberOfPackages;
		this.packageList = packageList;
	}
	
	

}

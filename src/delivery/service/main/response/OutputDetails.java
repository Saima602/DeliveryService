package delivery.service.main.response;

public class OutputDetails {

	private String packageId;
	private Double discount;
	private Double totalCostOfDelivery;
	private Double deliveryTimeInHours;

	public OutputDetails(String packageId, Double discount, Double totalCostOfDelivery,Double deliveryTimeInHours) {
		super();
		this.packageId = packageId;
		this.discount = discount;
		this.totalCostOfDelivery = totalCostOfDelivery;
		this.deliveryTimeInHours = deliveryTimeInHours;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotalCostOfDelivery() {
		return totalCostOfDelivery;
	}

	public void setTotalCostOfDelivery(Double totalCostOfDelivery) {
		this.totalCostOfDelivery = totalCostOfDelivery;
	}

	public Double getDeliveryTimeInHours() {
		return deliveryTimeInHours;
	}

	public void setDeliveryTimeInHours(Double deliveryTimeInHours) {
		this.deliveryTimeInHours = deliveryTimeInHours;
	}

}

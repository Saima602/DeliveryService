package delivery.service.main.service;

import delivery.service.main.dto.PackageDetails;
import delivery.service.main.response.DeliveryCostResponse;
import delivery.service.main.response.OutputDetails;

public interface ICalculateDeliveryCharge {
       
	    Double calculateDiscount(PackageDetails packageDetails);
	    OutputDetails calculateDeliveryCharge(Double baseCost, PackageDetails pkg, Double discount);
		
}

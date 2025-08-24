package delivery.service.main.constant;

public class DeliveryServiceConstant {
	
	public static final String DELIVERY_COST_APP = "DELIVERY_COST_APP";
	public static final String DELIVERY_TIME_APP = "DELIVERY_TIME_APP";
	public static final String NO_INPUTS_PROVIDED =  "Hey Seems you have missed on inputs !! Please provide Your input";
	
	public static final String NUMBER_OF_PACKAGES_NOT_SAME_AS_COUNT = "The Count given is nt equla to Number of packages";
	public static final String PACKAGE_DETAILS_NOT_FOUND = "No package Details Found.Please Provide the same.";
	public static final String INVALID_INPUTS_DELIVERY_COST="Invalid Inputs. Please Refere Below Sample Request : "
			+ "BaseCostOfPackageAsDoubleValue NumberOfPackages \r\n"
			+ "PACKAGE_ID:PKG_WEIGHT_IN_KG:DISTANCE_IN_KM,OFFERCODE"
			+ "PACKAGE_ID:PKG_WEIGHT_IN_KG:DISTANCE_IN_KM,OFFERCODE  (Keep On Adding Packages in this Format in new Line)";
	public static final String INVALID_INPUTS_DELIVERY_TIME = "Invalid Inputs. Please Refere Below Sample Request : "
			+ "BaseCostOfPackageAsDoubleValue NumberOfPackages \r\n"
			+ "PACKAGE_ID:PKG_WEIGHT_IN_KG:DISTANCE_IN_KM,OFFERCODE (Keep On Adding Packages in this Format in new Line)\r\n"
	        + "VehiclesCount MaxSpeed MaxWeight";
	public static final String INVALID_PACKAGE_ID = "Package Id should be a String.Invalid Package Id for Package ";
	public static final String WEIGHT = "WEIGHT";
	public static final String  DISTANCE= "Distance";
	public static final String INVALID_DISTANCE = "Invalid Distnace for package ";     
	public static final String INVALID_WEIGHT = "Invalid Weight for package ";  
}

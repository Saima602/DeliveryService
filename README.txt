🚚 Delivery Service Estimator

A Java-based delivery cost and time estimation system that calculates:
- Delivery charges for packages (considering weight, distance, and offers)
- Delivery time using multiple vehicles with speed & weight constraints

The project demonstrates Java 8+ features, clean coding practices, and test coverage with JUnit.

------------------------------------------------------------
✨ Features
------------------------------------------------------------
- Package Registration: Add packages with weight, distance, and offer codes
- Delivery Cost Calculation: Apply discounts and compute charges
- Delivery Time Estimation: Assign packages to available vehicles optimally
- Multiple Vehicle Scheduling: Choose vehicles based on earliest availability
- Unit Testing: JUnit 5 test cases with edge case handling

------------------------------------------------------------
🏗 Project Structure
------------------------------------------------------------
DeliveryService/
│── src/delivery/service/main
│   ├── dto/                # Data Transfer Objects (PackageDetails, Vehicle)
│   ├── service/            # Business Logic (Cost & Time calculation)
│   │   ├── CalculateDeliveryCharges.java
│   │   ├── CalculateDeliveryTime.java
│   │   ├── ICalculateDeliveryTime.java
│   │   ├── ICalculateDeliveryCharge.java
│   ├── response/            # Response DTO 
│   │   ├── OutputDetails.java (Contains the Required Output Format for Cost & Time calculation)
│   │   ├── DeliveryCostResponse.java (contains List of OutputDetails.java To Handle A single response)
│   ├── DeliveryService.java           # Entry point(contains Main Class)
│
│── src/delivery/service/test/
│   ├── service/
│   │   ├── CalculateDeliveryTimeTest.java   # JUnit test cases
│   │   ├── CalculateDeliveryChargesTest.java   # JUnit test cases
│   ├──DeliveryServiceTest.java           # JUnit test cases

-----------------------------------------------------------------------------------
Github Repo : https://github.com/Saima602/DeliveryService -> master branch
---------------------------------------------------------------------------------


------------------------------------------------------------
⚙️ Tech Stack
------------------------------------------------------------
- Java 8 / 17
- JUnit 5 for testing
- Java Project
------------------------------------------------------------
🚀 Getting Started
------------------------------------------------------------
1. Run the Application
  1. Unzip the zip folder
  2. Import the project in IDE as Java Project
  3. Once import is done click on Project and select Run As - Run Configuration
  4. Create Configuration to run the project (Calculating Delivery Charges): 
  4.1 Select the main class(DeliveryService.java) in Main tab
  4.2 Select Arguments Tab , copy following Input in 'Program Argument' for passing Input as command Line
               100 5
               PKG1:50:30:OFR001
               PKG2:75:125:OFR0008
               PKG3:175:100:OFR003
               PKG4:110:60:OFR002
               PKG5:155:95:NA
5. Create Configuration to run the project (Calculating Delivery Time): 
  5.1 Select the main class(DeliveryService.java) in Main tab
  5.2 Select Arguments Tab , copy following Input in 'Program Argument' for passing Input as command Line
                100 5
                PKG1:50:30:OFR001
                PKG2:75:125:OFFR0008
                PKG3:175:100:OFFR003
                PKG4:110:60:OFFR002
                PKG5:155:95:NA
                2 70 200

     
------------------------------------------------------------
🧪 Running Tests
------------------------------------------------------------
Right click on Project and click on Run As -> select Junit Project 

------------------------------------------------------------
📘 Example Usage
------------------------------------------------------------
For Calculating Delivery Charge :
Input:
Base Cost: 100
Number of Packages: 3
PACKAGE_ID:PKG_WEIGHT_IN_KG:DISTANCE_IN_KM,OFFERCODE
Example :
100 5
PKG1:50:30:OFR001
PKG2:75:125:OFR0008
PKG3:175:100:OFR003
PKG4:110:60:OFR002
PKG5:155:95:NA

Output :
PACKAGE_ID DISCOUNT_PRICE Delivery_Charge
PKG1 0.0 750.0
PKG2 0.0 1475.0
PKG3 0.0 2350.0
PKG4 105.0 1395.0
PKG5 0.0 2125.0
---------------------------------------------------

For Calculating Delivery Time :
Input:

Base Cost: 100
Number of Packages: 3
PACKAGE_ID:PKG_WEIGHT_IN_KG:DISTANCE_IN_KM,OFFERCODE
Vehicles: 2
Max Speed: 70
Max Weight: 200


Input :
100 5
PKG1:50:30:OFR001
PKG2:75:125:OFFR0008
PKG3:175:100:OFFR003
PKG4:110:60:OFFR002
PKG5:155:95:NA
2 70 200

Output :
PKG1 0.0 750.0 4.01
PKG2 0.0 1475.0 1.79
PKG3 0.0 2350.0 1.43
PKG4 0.0 1500.0 0.86
PKG5 0.0 2125.0 4.22

------------------------------------------------------------
📂 Test Coverage
------------------------------------------------------------
- Multiple packages + multiple vehicles
- Single vehicle scheduling
- No packages (empty input)
- Packages exceeding max weight
- 3 Offercodes to be Valid
-Invalid OfferCode
- Calculate Delivery charge with discount
-Calculate delivery charge without discount
-Main class test with Right Params
-Main class test with No Params



------------------------------------------------------------
🔮 Future Enhancements
------------------------------------------------------------
- Integrate Spring Boot REST API for real-time calculation
- Deploy on AWS / Azure
- Add database persistence for package history
- Add reporting dashboard

------------------------------------------------------------
👨‍💻 Author
------------------------------------------------------------
Saima Idris
Java Developer | Microservices | Spring Boot | System Design

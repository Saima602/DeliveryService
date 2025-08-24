package delivery.service.main.dto;

public class Vehicle implements Comparable<Vehicle> {
    private double availableAt;
    private int id;

    public Vehicle(int id) {
        this.id = id;
        this.availableAt = 0;
    }
    
    

    public double getAvailableAt() {
		return availableAt;
	}



	public void setAvailableAt(double availableAt) {
		this.availableAt = availableAt;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
    public int compareTo(Vehicle o) {
        return Double.compare(this.availableAt, o.availableAt);
    }
}

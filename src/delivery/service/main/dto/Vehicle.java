package delivery.service.main.dto;

public class Vehicle implements Comparable<Vehicle> {
    public double availableAt;
    public int id;

    public Vehicle(int id) {
        this.id = id;
        this.availableAt = 0;
    }

    @Override
    public int compareTo(Vehicle o) {
        return Double.compare(this.availableAt, o.availableAt);
    }
}

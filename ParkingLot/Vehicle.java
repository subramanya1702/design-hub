package ParkingLot;

public class Vehicle {
    private final String number;
    private final VehicleType type;
    private int parkingCharges = 0;

    public Vehicle(final String number, final VehicleType type) {
        this.number = number;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public int getParkingCharges() {
        return parkingCharges;
    }

    public void setParkingCharges(int parkingCharges) {
        this.parkingCharges = parkingCharges;
    }
}

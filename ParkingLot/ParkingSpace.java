package ParkingLot;

import java.time.Instant;

public class ParkingSpace {

    private final int id;
    private final int cost;
    private final VehicleType type;
    private Vehicle vehicle = null;
    private long parkedAt;

    public ParkingSpace(final int id, final int cost, final VehicleType type) {
        this.id = id;
        this.cost = cost;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleType getType() {
        return type;
    }

    public void setVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
        this.parkedAt = Instant.now().toEpochMilli();
    }

    public void removeVehicle() {
        int timeDifference = getTimeDifference();
        this.vehicle.setParkingCharges((cost * timeDifference));
        this.vehicle = null;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    private int getTimeDifference() {
        return (int) (Instant.now().toEpochMilli() - parkedAt) * 1000 * 60 * 60;
    }
}

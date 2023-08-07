package ParkingLot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {

    private final Map<VehicleType, List<ParkingSpace>> parkingSpaceMap = new HashMap<>();

    public ParkingLot(final List<ParkingSpace> parkingSpaces) {
        parkingSpaceMap.putAll(parkingSpaces
                .stream()
                .collect(Collectors.groupingBy(ParkingSpace::getType)));
    }

    public int parkVehicle(final Vehicle vehicle) {
        final ParkingSpace parkingSpace = getParkingSpaceByType(vehicle.getType());

        if (parkingSpace == null) {
            System.out.println("Unable to find parking space for the vehicle.");
            return -1;
        }

        parkingSpace.setVehicle(vehicle);

        return parkingSpace.getId();
    }

    public void removeVehicle(final int parkingSpaceId) {
        final ParkingSpace parkingSpace = getParkingSpaceById(parkingSpaceId);

        if (parkingSpace == null) {
            System.out.println("Parking space id is invalid.");
            return;
        }

        parkingSpace.removeVehicle();
    }

    private ParkingSpace getParkingSpaceByType(final VehicleType type) {
        return parkingSpaceMap.getOrDefault(type, Collections.emptyList())
                .stream()
                .filter(parkingSpace -> parkingSpace.isEmpty())
                .findFirst().orElse(null);
    }

    private ParkingSpace getParkingSpaceById(final int parkingSpaceId) {
        for (VehicleType type : parkingSpaceMap.keySet()) {
            for (ParkingSpace parkingSpace : parkingSpaceMap.get(type)) {
                if (parkingSpace.getId() == parkingSpaceId) {
                    return parkingSpace;
                }
            }
        }

        return null;
    }
}

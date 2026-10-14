package org.example.parkinglot.model.parking;

import org.example.parkinglot.model.account.common.Address;
import org.example.parkinglot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;
import org.example.parkinglot.model.parking.strategy.AssignmentStrategy;
import org.example.parkinglot.model.parking.strategy.NearestAssignmentStrategy;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.example.parkinglot.model.parking.ParkingFloor.getSpotTypeForVehicle;

@Getter
@Setter
public class ParkingLot {
    private String parkingLotId;
    private Address address;

    private List<ParkingFloor> parkingFloors;
    private List<EntrancePanel> entrancePanels;
    private List<ExitPanel> exitPanels;
    private AssignmentStrategy assignmentStrategy = new NearestAssignmentStrategy();

    public static ParkingLot INSTANCE = new ParkingLot();

    private ParkingLot() {
        this.parkingLotId = UUID.randomUUID().toString();
        parkingFloors = new ArrayList<>();
        entrancePanels = new ArrayList<>();
        exitPanels = new ArrayList<>();
    }

    public boolean isFull() {
        int index = 0;
        BitSet bitSet = new BitSet();
        for (ParkingFloor parkingFloor : parkingFloors) {
            bitSet.set(index++, parkingFloor.isFloorFull());
        }
        return bitSet.cardinality() == bitSet.size();
    }

    public boolean canPark(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.canPark(getSpotTypeForVehicle(vehicleType)))
                return true;
        }
        return false;
    }

    public ParkingSpot getParkingSpot(VehicleType vehicleType) {
        return assignmentStrategy.assignSpot(parkingFloors, getSpotTypeForVehicle(vehicleType));
    }

    public void setAssignmentStrategy(AssignmentStrategy assignmentStrategy) {
        this.assignmentStrategy = Objects.requireNonNull(assignmentStrategy, "assignmentStrategy");
    }

    public ParkingSpot vacateParkingSpot(String parkingSpotId) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            ParkingSpot parkingSpot = parkingFloor.vacateSpot(parkingSpotId);
            if (parkingSpot != null)
                return parkingSpot;
        }
        return null;
    }
}

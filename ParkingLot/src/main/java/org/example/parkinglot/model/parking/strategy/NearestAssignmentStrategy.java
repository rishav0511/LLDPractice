package org.example.parkinglot.model.parking.strategy;

import org.example.parkinglot.model.parking.ParkingFloor;
import org.example.parkinglot.model.parking.ParkingSpot;
import org.example.parkinglot.model.parking.ParkingSpotType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NearestAssignmentStrategy implements AssignmentStrategy {
    @Override
    public ParkingSpot assignSpot(List<ParkingFloor> parkingFloors, ParkingSpotType parkingSpotType) {
        List<SpotCandidate> candidates = new ArrayList<>();
        for (ParkingFloor floor : parkingFloors) {
            for (ParkingSpot spot : floor.getAvailableSpots(parkingSpotType)) {
                candidates.add(new SpotCandidate(floor, spot));
            }
        }

        candidates.sort(Comparator.comparingDouble(candidate -> candidate.spot().getDistanceFromEntrance()));
        for (SpotCandidate candidate : candidates) {
            if (candidate.floor().assignSpot(candidate.spot())) {
                return candidate.spot();
            }
        }
        return null;
    }

    private record SpotCandidate(ParkingFloor floor, ParkingSpot spot) {
    }
}

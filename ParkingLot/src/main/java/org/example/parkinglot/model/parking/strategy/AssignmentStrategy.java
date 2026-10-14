package org.example.parkinglot.model.parking.strategy;

import org.example.parkinglot.model.parking.ParkingFloor;
import org.example.parkinglot.model.parking.ParkingSpot;
import org.example.parkinglot.model.parking.ParkingSpotType;

import java.util.List;

public interface AssignmentStrategy {
    ParkingSpot assignSpot(List<ParkingFloor> parkingFloors, ParkingSpotType parkingSpotType);
}

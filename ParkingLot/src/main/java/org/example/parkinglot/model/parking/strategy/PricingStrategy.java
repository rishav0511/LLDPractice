package org.example.parkinglot.model.parking.strategy;

import org.example.parkinglot.model.parking.ParkingSpotType;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateCost(ParkingSpotType parkingSpotType, LocalDateTime parkedAt, LocalDateTime vacatedAt);
}

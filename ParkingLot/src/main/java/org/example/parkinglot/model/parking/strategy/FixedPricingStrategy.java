package org.example.parkinglot.model.parking.strategy;

import org.example.parkinglot.model.parking.ParkingSpotType;

import java.time.LocalDateTime;

public class FixedPricingStrategy implements PricingStrategy {
    private final double fixedAmount;

    public FixedPricingStrategy() {
        this(50.0);
    }

    public FixedPricingStrategy(double fixedAmount) {
        if (!Double.isFinite(fixedAmount) || fixedAmount < 0) {
            throw new IllegalArgumentException("fixedAmount must be a finite, non-negative value");
        }
        this.fixedAmount = fixedAmount;
    }

    @Override
    public double calculateCost(ParkingSpotType parkingSpotType, LocalDateTime parkedAt, LocalDateTime vacatedAt) {
        return fixedAmount;
    }
}

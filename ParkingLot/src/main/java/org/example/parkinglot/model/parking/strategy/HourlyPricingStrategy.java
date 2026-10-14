package org.example.parkinglot.model.parking.strategy;

import org.example.parkinglot.model.parking.HourlyCost;
import org.example.parkinglot.model.parking.ParkingSpotType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class HourlyPricingStrategy implements PricingStrategy {
    private final HourlyCost hourlyCost;

    public HourlyPricingStrategy() {
        this(new HourlyCost());
    }

    public HourlyPricingStrategy(HourlyCost hourlyCost) {
        this.hourlyCost = Objects.requireNonNull(hourlyCost, "hourlyCost");
    }

    @Override
    public double calculateCost(ParkingSpotType parkingSpotType, LocalDateTime parkedAt, LocalDateTime vacatedAt) {
        long hours = Math.max(1, Duration.between(parkedAt, vacatedAt).toHours());
        return hours * hourlyCost.getCost(parkingSpotType);
    }
}

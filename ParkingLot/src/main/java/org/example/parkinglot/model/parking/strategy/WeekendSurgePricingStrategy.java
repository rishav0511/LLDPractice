package org.example.parkinglot.model.parking.strategy;

import org.example.parkinglot.model.parking.ParkingSpotType;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

public class WeekendSurgePricingStrategy implements PricingStrategy {
    private final PricingStrategy baseStrategy;
    private final double surgeMultiplier;

    public WeekendSurgePricingStrategy() {
        this(new HourlyPricingStrategy(), 1.5);
    }

    public WeekendSurgePricingStrategy(PricingStrategy baseStrategy, double surgeMultiplier) {
        if (!Double.isFinite(surgeMultiplier) || surgeMultiplier < 1) {
            throw new IllegalArgumentException("surgeMultiplier must be finite and at least 1");
        }
        this.baseStrategy = Objects.requireNonNull(baseStrategy, "baseStrategy");
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public double calculateCost(ParkingSpotType parkingSpotType, LocalDateTime parkedAt, LocalDateTime vacatedAt) {
        double baseCost = baseStrategy.calculateCost(parkingSpotType, parkedAt, vacatedAt);
        DayOfWeek checkoutDay = vacatedAt.getDayOfWeek();
        return checkoutDay == DayOfWeek.SATURDAY || checkoutDay == DayOfWeek.SUNDAY
                ? baseCost * surgeMultiplier
                : baseCost;
    }
}

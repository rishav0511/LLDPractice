package org.example.parkinglot.model.parking;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

import org.example.parkinglot.model.parking.strategy.HourlyPricingStrategy;
import org.example.parkinglot.model.parking.strategy.PricingStrategy;

@Getter
public class ExitPanel {
    private String id;
    private final PricingStrategy pricingStrategy;

    public ExitPanel(String id) {
        this(id, new HourlyPricingStrategy());
    }

    public ExitPanel(String id, PricingStrategy pricingStrategy) {
        this.id = id;
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "pricingStrategy");
    }

    public ParkingTicket scanAndVacate(ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot =
                ParkingLot.INSTANCE.vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        LocalDateTime vacatedAt = LocalDateTime.now();
        parkingTicket.setVacatedAt(vacatedAt);
        parkingTicket.setCharges(pricingStrategy.calculateCost(
                parkingSpot.getParkingSpotType(), parkingTicket.getIssuedAt(), vacatedAt));
        return parkingTicket;
    }
}

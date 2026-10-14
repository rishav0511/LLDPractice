package org.example;

public class FixedAmountDiscount implements Discount {
    private final double discountAmount;

    public FixedAmountDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyDiscount(double originalAmount) {
        // Ensure the price doesn't drop below 0
        return Math.max(0, originalAmount - discountAmount);
    }
}

package org.example.decorator;

import org.example.Cart;
import org.example.calculator.PriceCalculator;

public class PercentageDiscountPriceDecorator extends PriceDecorator {
    private double percentage;

    public PercentageDiscountPriceDecorator(PriceCalculator priceCalculator, double percentage) {
        super(priceCalculator);
        this.percentage = percentage;
    }

    @Override
    public double calculateTotalPrice(Cart cart) {
        double price = priceCalculator.calculateTotalPrice(cart);
        return price - (price * percentage / 100);
    }
}

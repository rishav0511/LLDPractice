package org.example.decorator;

import org.example.Cart;
import org.example.calculator.PriceCalculator;

public class FlatDiscountPriceDecorator extends PriceDecorator {
    private double discountAmount;

    public FlatDiscountPriceDecorator(PriceCalculator priceCalculator, double discountAmount) {
        super(priceCalculator);
        this.discountAmount = discountAmount;
    }

    @Override
    public double calculateTotalPrice(Cart cart) {
        double price = priceCalculator.calculateTotalPrice(cart);
        return Math.max(0, price - discountAmount);
    }
}

package org.example.decorator;

import org.example.Cart;
import org.example.calculator.PriceCalculator;

public class TexDecorator extends PriceDecorator {
    private double taxRate;

    public TexDecorator(PriceCalculator priceCalculator, double taxRate) {
        super(priceCalculator);
        this.taxRate = taxRate;
    }

    @Override
    public double calculateTotalPrice(Cart cart) {
        double price = priceCalculator.calculateTotalPrice(cart);
        return price + (price * taxRate / 100);
    }
}

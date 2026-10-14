package org.example.calculator;

import org.example.Cart;
import org.example.Item;

public class BasePriceCalculator implements PriceCalculator {
    @Override
    public double calculateTotalPrice(Cart cart) {
        double totalPrice = 0.0;
        for (Item item : cart.getItems()) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}

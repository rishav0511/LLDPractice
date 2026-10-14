package org.example.calculator;

import org.example.Cart;

public interface PriceCalculator {
    double calculateTotalPrice(Cart cart);
}

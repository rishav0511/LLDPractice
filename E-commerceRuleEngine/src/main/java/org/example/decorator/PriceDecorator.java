package org.example.decorator;

import org.example.calculator.PriceCalculator;

abstract class PriceDecorator implements PriceCalculator {
    protected PriceCalculator priceCalculator;

    public PriceDecorator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }
}

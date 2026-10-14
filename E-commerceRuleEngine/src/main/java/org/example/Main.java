package org.example;

import org.example.calculator.BasePriceCalculator;
import org.example.calculator.PriceCalculator;
import org.example.decorator.FlatDiscountPriceDecorator;
import org.example.decorator.PercentageDiscountPriceDecorator;
import org.example.decorator.TexDecorator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create a new SnakeAndLadder game
        Cart cart = new Cart();
        cart.addItem(new Item("Laptop", 1000.00));
        cart.addItem(new Item("Headphones", 200.00));

        // 1. Calculate Base Price ($1200.00)
        PriceCalculator calculator = new BasePriceCalculator();

        // 2. Apply 10% store-wide sale ($1200 - $120 = $1080.00)
        calculator = new PercentageDiscountPriceDecorator(calculator, 10.0);

        // 3. Apply $50 Loyalty Coupon ($1080 - $50 = $1030.00)
        calculator = new FlatDiscountPriceDecorator(calculator, 50.0);

        // 4. Apply 8% State Tax ($1030 + $82.40 = $1112.40)
        calculator = new TexDecorator(calculator, 8.0);

        double finalTotal = calculator.calculateTotalPrice(cart);
        System.out.println("Final Order Total: $" + String.format("%.2f", finalTotal));
    }
}
package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product laptop = new Product("P101", "MacBook Pro", 2000.00);
        Product mouse = new Product("P102", "Wireless Mouse", 50.00);

        ShoppingCart cart = new ShoppingCart();

        // Add items to cart
        cart.addProduct(laptop, 1);
        cart.addProduct(mouse, 2); // Base total for mice: $100

        // Apply a flat $200 discount on the laptop
        cart.applyDiscountToProduct("P101", new FixedAmountDiscount(200.00));

        // Apply a 10% discount on the mice
        cart.applyDiscountToProduct("P102", new PercentageDiscount(10.0));

        // Output the total
        System.out.println("Total Cart Price: $" + cart.calculateCartTotal());
        // Expected: (2000 - 200) + (100 - 10) = 1800 + 90 = $1890.00
    }
}
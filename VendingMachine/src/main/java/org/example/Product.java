package org.example;

public class Product {
    private final String name;
    private final int price;       // in paise/cents — never use double for money

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        return name + " (₹" + price / 100.0 + ")";
    }
}

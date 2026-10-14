package org.example;

public class Product {
    private final String productId;
    private final String name;
    private final double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public double getPrice() { return price; }
}


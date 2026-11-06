package org.example;

public class Slot {
    private final Product product;
    private int quantity;

    public Slot(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return quantity>0;
    }

    public void refill (int count) {
        quantity+=count;
    }

    public void dispenseOne() {
        if (quantity <= 0)
            throw new IllegalStateException("Slot empty: " + product.getName());
        quantity--;
    }
}

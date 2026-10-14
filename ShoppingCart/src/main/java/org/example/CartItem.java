package org.example;

public class CartItem {
    private final Product product;
    private int quantity;
    private Discount discount; // Discount applied specifically to this item

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double calculateItemTotal() {
        double baseTotal = product.getPrice() * quantity;
        if (discount != null) {
            return discount.applyDiscount(baseTotal);
        }
        return baseTotal;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}

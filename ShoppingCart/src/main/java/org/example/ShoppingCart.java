package org.example;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // Maps productId to CartItem for easy lookup and quantity updates
    private final Map<String, CartItem> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        if (items.containsKey(product.getProductId())) {
            CartItem existingItem = items.get(product.getProductId());
            // Update quantity if item already exists
            items.put(product.getProductId(),
                    new CartItem(product, existingItem.getQuantity() + quantity));
        } else {
            items.put(product.getProductId(), new CartItem(product, quantity));
        }
    }

    public void applyDiscountToProduct(String productId, Discount discount) {
        CartItem item = items.get(productId);
        if (item != null) {
            item.setDiscount(discount);
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public double calculateCartTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.calculateItemTotal();
        }
        return total;
    }
}
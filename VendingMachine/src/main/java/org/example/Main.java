package org.example;

import org.example.machine.VendingMachine;

/**
 <a href=https://systemcraft.in/lld/VendingMachine/>Documentation</a>
 */
public class Main {
    public static void main(String[] args) {
        VendingMachine m = new VendingMachine();
        // Prices in paise: ₹25.00 = 2500
        m.addSlot("A1", new Product("Coke", 2500), 2);
        m.addSlot("A2", new Product("Water", 2000), 0);   // out of stock
        m.addSlot("B1", new Product("Chips", 3000), 5);
        m.addCoins(500, 5);   // ₹5 x5
        m.addCoins(1000, 5);  // ₹10 x5

        System.out.println("=== Happy path: buy Coke with exact-ish money ===");
        m.selectProduct("A1");        // → HAS_SELECTION
        m.insertMoney(1000);          // ₹10
        m.insertMoney(1000);          // ₹20
        m.insertMoney(1000);          // ₹30 → HAS_MONEY
        m.dispense();                 // dispense Coke, return ₹5 change → IDLE

        System.out.println("\n=== Out of stock ===");
        m.selectProduct("A2");        // rejected, stays IDLE

        System.out.println("\n=== Cancel and refund ===");
        m.selectProduct("B1");
        m.insertMoney(1000);
        m.cancel();                   // refund ₹10 → IDLE

        System.out.println("\n=== State after transactions ===");
        System.out.println("Current state: " + m.currentStateName());

        System.out.println("\n=== Cannot make change (drain the float) ===");
        // Empty the float of small coins, then overpay so change is impossible.
        VendingMachine m2 = new VendingMachine();
        m2.addSlot("C1", new Product("Gum", 1500), 1); // ₹15
        m2.addCoins(1000, 0); // no coins at all
        m2.selectProduct("C1");
        m2.insertMoney(1000);
        m2.insertMoney(1000); // ₹20 in, owes ₹5 change but float is empty
        m2.dispense();        // cannot make change → refund, stays consistent
    }
}
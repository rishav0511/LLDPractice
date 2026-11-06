package org.example;

import org.example.strategy.EqualSplit;
import org.example.strategy.ExactSplit;
import org.example.strategy.PercentageSplit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 <a href=https://systemcraft.in/lld/Splitwise/#split-strategy-interface/>Documentation</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("══════ SPLITWISE DEMO ══════\n");

        ExpenseManager manager = new ExpenseManager();
        manager.addObserver(new BalanceObserver() {
            public void onExpenseAdded(Expense e) {
                System.out.println("  📝 Expense added: " + e);
            }

            public void onBalanceChanged(String uid, double bal) {
            }
        });

        // Create users
        User alice = new User("u1", "Alice", "alice@email.com");
        User bob = new User("u2", "Bob", "bob@email.com");
        User charlie = new User("u3", "Charlie", "charlie@email.com");
        User diana = new User("u4", "Diana", "diana@email.com");

        manager.addUser(alice);
        manager.addUser(bob);
        manager.addUser(charlie);
        manager.addUser(diana);

        // Create a trip group
        manager.createGroup("g1", "Goa Trip",
                Arrays.asList(alice, bob, charlie, diana));
        System.out.println("Group created: Goa Trip\n");

        // --- EQUAL SPLIT ---
        System.out.println("--- Equal Split: Dinner $240 paid by Alice ---");
        manager.addExpense("e1", 240.0, alice, "Dinner",
                new EqualSplit(),
                Arrays.asList(alice, bob, charlie, diana), "g1");

        // --- EXACT SPLIT ---
        System.out.println("\n--- Exact Split: Shopping $500 paid by Bob ---");
        Map<User, Double> exactAmounts = new HashMap<>();
        exactAmounts.put(alice, 100.0);
        exactAmounts.put(bob, 200.0);
        exactAmounts.put(charlie, 50.0);
        exactAmounts.put(diana, 150.0);
        manager.addExpense("e2", 500.0, bob, "Shopping",
                new ExactSplit(exactAmounts),
                Arrays.asList(alice, bob, charlie, diana), "g1");

        // --- PERCENTAGE SPLIT ---
        System.out.println("\n--- Percentage Split: Hotel $1000 paid by Charlie ---");
        Map<User, Double> percentages = new HashMap<>();
        percentages.put(alice, 30.0);
        percentages.put(bob, 30.0);
        percentages.put(charlie, 20.0);
        percentages.put(diana, 20.0);
        manager.addExpense("e3", 1000.0, charlie, "Hotel",
                new PercentageSplit(percentages),
                Arrays.asList(alice, bob, charlie, diana), "g1");

        // --- BALANCES ---
        System.out.println("\n--- Balances ---");
        for (User u : Arrays.asList(alice, bob, charlie, diana)) {
            Map<String, Double> bal = manager.getBalance(u.getId());
            System.out.println("  " + u.getName() + ":");
            for (Map.Entry<String, Double> e : bal.entrySet()) {
                String label = e.getValue() > 0 ? "is owed" : "owes";
                System.out.printf("    %s $%.2f (user %s)%n", label,
                        Math.abs(e.getValue()), e.getKey());
            }
        }

        // --- SIMPLIFY DEBTS ---
        System.out.println("\n--- Simplified Debts (Minimum Transactions) ---");
        List<Transaction> simplified = manager.simplifyDebts();
        for (Transaction t : simplified) {
            System.out.println("  " + t);
        }
        System.out.println("  Total transactions: " + simplified.size());

        System.out.println("\n══════ DONE ══════");
    }
}
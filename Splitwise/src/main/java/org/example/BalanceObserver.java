package org.example;

public interface BalanceObserver {
    void onExpenseAdded(Expense expense);
    void onBalanceChanged(String userId, double newNetBalance);
}

package org.example;

import org.example.strategy.Split;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ExpenseManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Group> groups = new HashMap<>();
    private final List<Expense> expenses = new ArrayList<>();
    private final BalanceSheet balanceSheet = new BalanceSheet();
    private final ReentrantLock lock = new ReentrantLock();
    private final List<BalanceObserver> observers = new CopyOnWriteArrayList<>();

    public void addUser(User user) {
        lock.lock();
        try {
            users.put(user.getId(), user);
        } finally { lock.unlock(); }
    }

    public void createGroup(String groupId, String name, List<User> members) {
        lock.lock();
        try {
            Group group = new Group(groupId, name, members);
            groups.put(groupId, group);
            for (User u : members) users.putIfAbsent(u.getId(), u);
        } finally { lock.unlock(); }
    }

    public void addExpense(String expenseId, double amount, User payer,
                           String description, Split splitStrategy,
                           List<User> participants, String groupId) {
        lock.lock();
        try {
            if (!splitStrategy.validate(amount, participants)) {
                throw new IllegalArgumentException("Invalid split: amounts do not match total");
            }

            Expense expense = new Expense(expenseId, amount, payer, description,
                    splitStrategy, participants);
            expenses.add(expense);

            if (groupId != null && groups.containsKey(groupId)) {
                groups.get(groupId).addExpense(expense);
            }

            // Compute shares and record debts
            Map<User, Double> shares = splitStrategy.computeShares(amount, participants);
            for (Map.Entry<User, Double> entry : shares.entrySet()) {
                User participant = entry.getKey();
                double share = entry.getValue();
                if (!participant.equals(payer) && share > 0) {
                    balanceSheet.recordDebt(participant, payer, share);
                }
            }

            // Notify observers
            for (BalanceObserver obs : observers) {
                obs.onExpenseAdded(expense);
            }
        } finally { lock.unlock(); }
    }

    public Map<String, Double> getBalance(String userId) {
        lock.lock();
        try {
            return balanceSheet.getBalanceForUser(userId);
        } finally { lock.unlock(); }
    }

    public List<Transaction> simplifyDebts() {
        lock.lock();
        try {
            return balanceSheet.simplifyDebts();
        } finally { lock.unlock(); }
    }

    public void addObserver(BalanceObserver observer) {
        observers.add(observer);
    }

    public Group getGroup(String groupId) {
        return groups.get(groupId);
    }

    public List<Expense> getAllExpenses() {
        return Collections.unmodifiableList(expenses);
    }
}

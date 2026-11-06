package org.example;

import org.example.strategy.Split;

import java.util.Collections;
import java.util.List;

public class Expense {
    private final String id;
    private final double amount;
    private final User payer;
    private final String description;
    private final Split splitStrategy;
    private final List<User> participants;

    public Expense(String id, double amount, User payer, String description,
                   Split splitStrategy, List<User> participants) {
        this.id = id;
        this.amount = amount;
        this.payer = payer;
        this.description = description;
        this.splitStrategy = splitStrategy;
        this.participants = Collections.unmodifiableList(participants);
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public User getPayer() { return payer; }
    public String getDescription() { return description; }
    public Split getSplitStrategy() { return splitStrategy; }
    public List<User> getParticipants() { return participants; }

    @Override
    public String toString() {
        return description + " - $" + String.format("%.2f", amount) + " paid by " + payer.getName();
    }
}

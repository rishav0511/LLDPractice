package org.example.strategy;

import org.example.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentageSplit implements Split {
    private final Map<User, Double> percentages;

    public PercentageSplit(Map<User, Double> percentages) {
        this.percentages = percentages;
    }

    @Override
    public boolean validate(double totalAmount, List<User> participants) {
        if (percentages == null || percentages.isEmpty())
            return false;
        double sum = percentages.values().stream().mapToDouble(Double::doubleValue).sum();
        return Math.abs(sum - 100.0) < 0.01 && totalAmount > 0;
    }

    @Override
    public Map<User, Double> computeShares(double totalAmount, List<User> participants) {
        Map<User, Double> shares = new HashMap<>();
        for (Map.Entry<User, Double> entry : percentages.entrySet()) {
            shares.put(entry.getKey(), totalAmount * entry.getValue() / 100.0);
        }
        return shares;
    }
}

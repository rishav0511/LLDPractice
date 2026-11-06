package org.example.strategy;

import org.example.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactSplit implements Split {
    private final Map<User, Double> exactAmounts;

    public ExactSplit(Map<User, Double> exactAmounts) {
        this.exactAmounts = exactAmounts;
    }

    @Override
    public boolean validate(double totalAmount, List<User> participants) {
        if (exactAmounts == null || exactAmounts.isEmpty())
            return false;
        double sum = exactAmounts.values().stream().mapToDouble(Double::doubleValue).sum();
        return Math.abs(sum - totalAmount) < 0.01;
    }

    @Override
    public Map<User, Double> computeShares(double totalAmount, List<User> participants) {
        return new HashMap<>(exactAmounts);
    }
}
